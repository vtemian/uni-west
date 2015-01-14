#include "sys/wait.h"
#include <sys/ipc.h>
#include <sys/shm.h>

#include "command.h"
#include "terminal.h"
#include "history.h"
#include "errors.h"
#include "keyboard.h"
#include "commands/init.h"

int comparator(void *loaded_command, void *command){
  return strcmp(((command_nt*)loaded_command)->name, (char *)command) == 0;
}

char* get_command(list_t *history) { unsigned char in_char;
    char* in_command = malloc(COMMAND_LEN);
    int index=0, read_nr=0, term_fd=fileno(stdin), reading=1;
    char a[]="\33[K\r> \33[K";
    void *command;

    struct termios  attr;
    struct termios *current_terminal=&attr;
    struct timespec tsp={SECONDS_SLEEP, NANOS_SLEEP};  // sleep 500 usec

    in_command[index] = '\0';

    // flush stdout
    fprintf(stdout, "> ");
    fflush(stdout);

    // set new terminal attrs
    prepare_terminal(term_fd, current_terminal);

    while(reading){
        nanosleep(&tsp, NULL);

        // read one char from terminal
        read_nr = read(term_fd, &in_char, sizeof (in_char));

        switch(read_nr) {
            case 0:
            default:
                break;
            case -1:
                 fprintf(stdout, "Read error %s", strerror(errno));
                 exit(1);
                 break;
            case 1:
                 switch(in_char) {
                     default:
                         in_command[index++] = in_char;
                         write(STDOUT_FILENO, &in_char, 1);
                         break;
                     case BACKSPACE:
                         index = index > 0 ? index - 1: 0;
                         in_command[index] = '\0';
                         write(STDOUT_FILENO, &a, strlen(a));
                         write(STDOUT_FILENO, in_command, strlen(in_command));
                         break;
                     case ENTER:
                         in_command[index] = '\0';
                         write(STDOUT_FILENO, &in_char, 1);
                         add_command(history, in_command);
                         reading = 0;
                         break;
                     case ARROW_UP:
                         command = go_up(history);
                         if(command == NULL){
                            strcpy(in_command, "");
                         }
                         else{
                            strcpy(in_command, ((history_nt*)command)->command);
                         }

                         index = strlen(in_command);
                         if(index == 1)
                             index = 0;

                         write(STDOUT_FILENO, &a, strlen(a));
                         write(STDOUT_FILENO, in_command, strlen(in_command));
                         break;
                     case ARROW_DOWN:
                         command = go_down(history);
                         if(command == NULL){
                            strcpy(in_command, "");
                         }
                         else{
                            strcpy(in_command, ((history_nt*)command)->command);
                         }

                         index = strlen(in_command) + 1; if(index == 1)
                             index = 0;

                         write(STDOUT_FILENO, &a, strlen(a));
                         write(STDOUT_FILENO, in_command, strlen(in_command));
                         break;
                 }
                 break;
        }
    }

    if(tcsetattr(term_fd, TCSADRAIN, current_terminal) == -1 &&
       tcsetattr(term_fd, TCSADRAIN, current_terminal) == -1 )
      term_error();

    return in_command;
}

list_t *_load_from_path(list_t *commands_list, char *path){
    DIR *dir;
    struct dirent *ent;

    char *dir_path, *command_path, *aux_command_path;

    // split the path by :
    dir_path = strtok(path, ":");
    while(dir_path != NULL) {
        // check if in dir_path we find some executable files
        if ((dir=opendir(dir_path)) != NULL) {
            command_path = malloc(sizeof(dir_path));
            strcpy(command_path, dir_path);

            while ((ent=readdir(dir)) != NULL) {
                if(strcmp(".", ent->d_name) != 0 && strcmp("..", ent->d_name) != 0){
                    command_nt *node = malloc(sizeof(command_nt));

                    node->absolute_path = malloc(sizeof(command_path) + sizeof(ent->d_name) + 1);
                    node->name = malloc(sizeof(ent->d_name));
                    node->impl = NULL;
                    node->arguments = NULL;
                    node->arguments_size = 0;

                    strcpy(node->absolute_path, command_path);
                    strcat(node->absolute_path, "/");
                    strcat(node->absolute_path, ent->d_name);
                    strcpy(node->name, ent->d_name);

                    add_node(commands_list, node);
                }
            }
            free(command_path);
            closedir(dir);
        }
        dir_path = strtok(NULL, ":");
    }
    return commands_list;
}

list_t *_load_custom_commands(list_t *commands_list){
    int size=0, index=0;
    command_nt *result = malloc(sizeof(command_nt));
    command_nt **commands = malloc(sizeof(command_nt**));
    commands = get_custom_commands(&size);

    for(index=0; index<size; index++){
        result = find(commands_list, commands[index]->name, comparator);
        if(result != NULL) {
            printf("uhhuuu");
        }else{
            add_node(commands_list, commands[index]);
        }
    }

    return commands_list;
}

list_t *load_commands(char *path) {
    list_t *commands_list = malloc(sizeof(list_t));
    init_list(commands_list, COMMANDS_NR, sizeof(command_nt));

    commands_list = _load_from_path(commands_list, path);
    commands_list = _load_custom_commands(commands_list);

    return commands_list;
}

int execute(char *raw_command, list_t *loaded_commands, char **environ) {
    pid_t pid;
    int commands_nr=0, status, shmid, index, in=0, std_in=STDIN_FILENO, out=0, std_out=STDOUT_FILENO, redirect_fd;
    command_nt **parsed_commands=malloc(sizeof(command_nt**));
    char *env="/tmp/env", *std_path;
    char **child_environ, **env_cpy=malloc(sizeof(char**));

    in = strstr(raw_command, "<") != NULL ? 1:0;
    out = strstr(raw_command, ">") != NULL ? 1:0;
    if(in || out) {
        raw_command = strtok(raw_command, "><");
        std_path = strtok(NULL, "><");
        redirect_fd = open(std_path, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP);

        std_in = in ? redirect_fd : STDIN_FILENO;
        std_out = out ? redirect_fd : STDOUT_FILENO;
    }

    parsed_commands = parse_command(raw_command, &commands_nr, loaded_commands);
    shmid = shmget(IPC_PRIVATE, sizeof(env_cpy), 0660 | IPC_CREAT);

    char **parent_environ = (char **)shmat(shmid,NULL,0);

    index=0;
    while(environ[index]) {
        memcpy(&parent_environ[index], &environ[index], sizeof(environ[index]));
        index++;
    }

    pid = fork();
    if(pid == 0){
        char **child_environ = (char **)shmat(shmid,NULL,0);
        run(parsed_commands, commands_nr, std_in, std_out, child_environ);
        exit(1);
    }else{
        while((pid = wait(&status)) > 0);
        char **parent_environ = (char **)shmat(shmid,NULL,0);
        status=0;
        while(parent_environ[status]){
            putenv(parent_environ[status++]);
        }
    }

    return 1;
}

command_nt **parse_command(char *command, int *commands_nr, list_t* loaded_commands){
    command_nt *result = malloc(sizeof(command_nt));

    int index=0, i, nr=0;
    char *aux_command;
    char **prepared_command;

    char **aux_commands = malloc(sizeof(char***));
    command_nt **commands = malloc(sizeof(command_nt**));

    aux_command = strtok(command, "|");
    while(aux_command != NULL){
        aux_commands[index] = malloc(sizeof(char*));
        aux_commands[index] = aux_command;
        *commands_nr += 1;
        index += 1;
        aux_command = strtok(NULL, "|");
    }

    for(i=0; i<index; i++) {
        nr = 0;
        prepared_command = get_arguments(aux_commands[i], &nr);

        result = find(loaded_commands, prepared_command[0], comparator);
        if(result != NULL) {
            commands[i] = malloc(sizeof(command_nt*));
            prepared_command[0] = ((command_nt*)result)->absolute_path;
            commands[i] = ((command_nt*)result);
            commands[i]->arguments = malloc(sizeof(prepared_command));
            commands[i]->arguments = prepared_command;
            commands[i]->arguments_size = nr;
        }else {
            //TODO: report error
        }
    }

    return commands;
}

char **get_arguments(char *command, int *size) {
    int index=0;
    char *argument;
    char **args = malloc(100 * sizeof(char*));
    args[index] = malloc(sizeof(char*));
    args[0][0] = '\0';

    argument = strtok(command, " ");
    while(argument != NULL){
        args[index] = malloc(sizeof(char*));
        strcpy(args[index], argument);
        index++;
        argument = strtok(NULL, " ");
    }
    args[index] = NULL;
    *size = index - 1;
    return args;
}

void run(command_nt **commands, int commands_nr, int in_fd, int out_fd, char **environ) {
    pid_t pid;
    int pipes[2], status;

    if(commands_nr == 1){
        if(in_fd != STDIN_FILENO){
            if(dup2(in_fd, STDIN_FILENO) != -1)
               close(in_fd);
        }
        if(out_fd != STDOUT_FILENO){
            if(dup2(out_fd, STDOUT_FILENO) != -1)
               close(out_fd);
        }
        if(commands[0]->impl == NULL)
            execv(commands[0]->absolute_path, commands[0]->arguments);
        else{
            commands[0]->impl(commands[0]->arguments_size, commands[0]->arguments, environ);
        }
    }else{
        if ((pipe(pipes) == -1) || ((pid = fork()) == -1)) {
            perror("error when we initialized pipes");
        } if (pid == 0){ /* child executes current command */
            close(pipes[0]);
            if (dup2(in_fd, STDIN_FILENO) == -1) /*read from in_fd */
                perror("Failed to redirect stdin");
            if (dup2(pipes[1], STDOUT_FILENO) == -1)   /*write to fd[1]*/
                perror("Failed to redirect stdout");
            else if ((close(pipes[1]) == -1))
                perror("Failed to close extra pipe descriptors");
            else {
                if(commands[0]->impl == NULL)
                    execv(commands[0]->absolute_path, commands[0]->arguments);
                else
                    commands[0]->impl(commands[0]->arguments_size, commands[0]->arguments, environ);
            }
        }
        close(pipes[1]);   /* parent executes the rest of commands */
        close(in_fd);
        run(commands + 1, commands_nr - 1, pipes[0], out_fd, environ);
    }
}
