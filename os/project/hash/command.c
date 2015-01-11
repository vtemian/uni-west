#include "sys/wait.h"

#include "command.h"
#include "terminal.h"
#include "history.h"
#include "errors.h"
#include "keyboard.h"

int comparator(void *loaded_command, void *command){
  return strcmp(((command_nt*)loaded_command)->relative_path, (char *)command) == 0;
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

                         index = strlen(in_command) + 1;
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

list_t *load_commands(char *path) {
    DIR *dir;
    struct dirent *ent;

    char *dir_path, *command_path, *aux_command_path;

    list_t *commands_list = malloc(sizeof(list_t));
    init_list(commands_list, COMMANDS_NR, sizeof(command_nt));

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
                    node->relative_path = malloc(sizeof(ent->d_name));

                    strcpy(node->absolute_path, command_path);
                    strcat(node->absolute_path, "/");
                    strcat(node->absolute_path, ent->d_name);
                    strcpy(node->relative_path, ent->d_name);

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

int execute(char *raw_command, list_t *loaded_commands) {
    pid_t pid;
    int commands_nr=0, status;
    char ***parsed_commands=malloc(sizeof(char***));

    parsed_commands = parse_command(raw_command, &commands_nr, loaded_commands);

    pid = fork();
    if(pid == 0){
        run(parsed_commands, commands_nr, STDIN_FILENO);
    }else{
        while((pid = wait(&status)) > 0);
    }

    return 1;
}

char ***parse_command(char *command, int *commands_nr, list_t* loaded_commands){
    command_nt *result = malloc(sizeof(command_nt));

    int index=0,i;
    char *aux_command;
    char **prepared_command;

    char **aux_commands = malloc(sizeof(char***));
    char ***commands = malloc(sizeof(char***));

    aux_command = strtok(command, "|");
    while(aux_command != NULL){
        aux_commands[index] = malloc(sizeof(char*));
        aux_commands[index] = aux_command;
        *commands_nr += 1;
        index += 1;
        aux_command = strtok(NULL, "|");
    }

    for(i=0; i<index; i++) {
        prepared_command = get_arguments(aux_commands[i]);

        result = find(loaded_commands, prepared_command[0], comparator);
        if(result != NULL) {
            commands[i] = malloc(sizeof(char***));
            prepared_command[0] = ((command_nt*)result)->absolute_path;
            commands[i] = prepared_command;
        }else {
            //TODO: report error
        }
    }

    return commands;
}

char **get_arguments(char *command) {
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
    return args;
}

void run(char ***commands, int commands_nr, int in_fd) {
    pid_t pid;
    int pipes[2], status;

    if(commands_nr == 0){
        exit(1);
    }
    if(commands_nr == 1){
        if(in_fd != STDIN_FILENO){
            if(dup2(in_fd, STDIN_FILENO) != -1)
               close(in_fd);
        }
        execv(commands[0][0], commands[0]);
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
                execv(commands[0][0], commands[0]);
            }
        }
        close(pipes[1]);   /* parent executes the rest of commands */
        close(in_fd);
        run(commands + 1, commands_nr - 1, pipes[0]);
    }
}
