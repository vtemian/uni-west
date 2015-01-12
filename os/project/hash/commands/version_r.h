#ifndef VERSION_COMMAND_1
#define VERSION_COMMAND_1

#include <stdio.h>
#include <stdlib.h>

#include "../command.h"
#include "../ver.h"

int version_r(int argc, char *argv[]){
    write(STDOUT_FILENO, __VERSION, strlen(__VERSION));
};

#endif
