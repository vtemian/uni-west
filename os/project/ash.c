#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "hash/command.h"

int main() {
    // read the command
    while(1){
        printf("%s:%s $ ", getenv("USER"), getcwd(NULL, 1024));
        printf("Command is: %s\n", get_command());
    }
    return 0;
}
