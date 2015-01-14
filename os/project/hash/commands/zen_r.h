#ifndef ZEN_COMMAND_1
#define ZEN_COMMAND_1

#include <stdio.h>
#include <stdlib.h>

int zen_r(int argc, char *argv[]){
    char zen[] = {"Debugging is twice as hard as writing the code \n"
                  "in the first place. Therefore, if you write the \n"
                  "code as cleverly as possible, you are, by definition,\n"
                  "not smart enough to debug it.\n"};
    write(STDOUT_FILENO, zen, strlen(zen));
    exit(1);
};

#endif
