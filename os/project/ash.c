#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "hash/command.h"
#include "hash/history.h"
#include "hash/linked_list.h"

int main() {
    list_t* history = malloc(sizeof(list_t));

    history = load_history();

    // read the command
    while(1){
        get_command(history);
    }
    return 0;
}
