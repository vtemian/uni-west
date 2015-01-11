#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
void do_command(char ***commands, int commands_nr, int in_fd) {
    pid_t pid;
    int pipes[2];

    if(commands_nr == 0){
        exit(1);
    }
    if(commands_nr == 1){
        if(in_fd != STDIN_FILENO){
            if(dup2(in_fd, STDIN_FILENO) != -1)
               close(in_fd);
        }
        execv(commands[0][0], commands[0]);
        exit(1);
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
        do_command(commands + 1, commands_nr - 1, pipes[0]);
    }
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

char ***parse_command(char *command, int *commands_nr){
    int index=0,i;
    char *aux_command;
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
        commands[i] = malloc(sizeof(char***));
        commands[i] = get_arguments(aux_commands[i]);
    }

    return commands;
}

int main() {
    int commands_nr=0;

    //char command[] = {"/bin/cat ash.c | /bin/grep int | /bin/grep 0 | /bin/grep ="};
    char command[] = {"/bin/cat ash.c"};
    char ***commands = parse_command(command, &commands_nr);

    do_command(commands, commands_nr, STDIN_FILENO);

    return 0;
}
