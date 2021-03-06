#ifndef HISTORY
#define HISTORY

#include <fcntl.h>

#define ASH_HISTORY getenv("ASH_HISTORY")
#define LINE_LEN 1024
#define COMMANDS_NR 1000

#include "linked_list.h"

typedef struct history_node {
    char command[100];
} history_nt;

void* go_up(list_t *history);
void* go_down(list_t *history);
void add_command(list_t *history_list, char *command);
void reset_history();
list_t* load_history();

#endif
