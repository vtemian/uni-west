#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "libhash.h"


int main() {
    char *path=getenv("PATH"), *command, *full_path;

    list_t* history = malloc(sizeof(list_t));
    list_t* commands = malloc(sizeof(list_t));

    history = load_history();
    commands = load_commands(path);

    // read the command
    while(1){
        command = get_command(history);
        full_path = find(commands, command);
        if(full_path != NULL){
            execute_command(full_path, command);
        }else{
            printf("Command %s is invalid", command);
        }
    }
    return 0;
}
