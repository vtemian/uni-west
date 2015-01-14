#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "libhash.h"

extern char **environ;

int main() {
    char *path=getenv("PATH"), *raw_command;

    list_t *history = malloc(sizeof(list_t));
    list_t *commands = malloc(sizeof(list_t));

    history = load_history();
    commands = load_commands(path);

    // read the command
    while(1){
        raw_command = get_command(history);
        if(strlen(raw_command) == 0) continue;

        execute(raw_command, commands, environ);
    }

    return 0;
}
