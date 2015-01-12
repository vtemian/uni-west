#ifndef INIT_COMMAND
#define INIT_COMMAND

#define MAX_CUSTOM_COMMANDS 10

#include "../command.h"
#include "version_r.h"

int list_size=0;
command_nt *commands[MAX_CUSTOM_COMMANDS];

void add_custom_command(char *name, implementation fn){
    command_nt *command = malloc(sizeof(command_nt*));

    command->absolute_path = NULL;
    command->name = malloc(sizeof(name));
    strcpy(command->name, name);
    command->impl = fn;

    commands[list_size] = command;
    list_size += 1;
}

command_nt **get_custom_commands(int *size){
    add_custom_command("version", &version_r);

    *size = list_size;
    return commands;
}

#endif
