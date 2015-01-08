#ifndef COMMAND
#define COMMAND

#include <sys/types.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <termios.h>
#include <dirent.h>

#define NANOS_SLEEP 500
#define SECONDS_SLEEP 0
#define COMMAND_LEN 1024

#include "linked_list.h"

char* get_command(list_t *history);
list_t *load_commands(char *path);
int execute_command(char *command, char *argv);

#endif
