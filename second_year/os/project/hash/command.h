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

typedef void (*implementation)(int argc, char **argv, char **environ);

typedef struct command_node {
    char *name;          // name of the command

    char *absolute_path; // absolute path to executable, used for execv
    implementation impl; // if it's a custom command, we have our own implementation
    char **arguments;    // arguments of this commands
    int arguments_size;
} command_nt;

char* get_command(list_t *history);
list_t *load_commands(char *path);
int execute(char *command, list_t *commands, char **environ);
command_nt **parse_command(char *command, int *commands_nr, list_t *loaded_commands);
char **get_arguments(char *command, int *nr);
void run(command_nt **commands, int commands_nr, int in_fd, int out_fd, char **environ);

#endif
