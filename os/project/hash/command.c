#include "sys/wait.h"

#include "command.h"
#include "terminal.h"
#include "history.h"
#include "errors.h"

char* get_command(list_t *history) {
    unsigned char in_char;
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
                     case 127:
                         index = index > 0 ? index - 1: 0;
                         in_command[index] = '\0';
                         write(STDOUT_FILENO, &a, strlen(a));
                         write(STDOUT_FILENO, in_command, strlen(in_command));
                         break;
                     case '\n':
                         in_command[index] = '\0';
                         write(STDOUT_FILENO, &in_char, 1);
                         add_command(history, in_command);
                         reading = 0;
                         break;
                     case 65:
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
                     case 66:
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
            closedir (dir);
        }
        dir_path = strtok(NULL, ":");
    }
    return commands_list;
}

int execute_command(char *command, char **args) {
    int status;
    pid_t pid, wpid;

    if ((pid = fork()) == -1)
        perror("fork error");
    else if (pid == 0) {
        execv(command, args);
        printf("Return not expected. Must be an execv error.n");
    } else {
        while((wpid = wait(&status)) > 0);
    }

    return 1;
}
