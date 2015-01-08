#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "libhash.h"

int comparator(void *loaded_command, void *command){
  return strcmp(((command_nt*)loaded_command)->relative_path, (char *)command) == 0;
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
    return args;
}

int main() {
    char *path=getenv("PATH"), *raw_command, **prepared_command;

    list_t *history = malloc(sizeof(list_t));
    list_t *commands = malloc(sizeof(list_t));
    command_nt *result = malloc(sizeof(command_nt));

    history = load_history();
    //commands = load_commands(path);

    // read the command
    while(1){
        raw_command = get_command(history);
        prepared_command = get_arguments(raw_command);
        result = find(commands, prepared_command[0], comparator);

        if(result != NULL){
            execute_command(((command_nt*)result)->absolute_path, prepared_command + 1);
        }else{
            //printf("Command %s is invalid", prepared_command[0]);
        }
        free(prepared_command);
    }
    return 0;
}
