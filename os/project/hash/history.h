#ifndef HISTORY
#define HISTORY

#include <fcntl.h>

#include "errors.h"
#include "linked_list.h"

#define ASH_HISTORY getenv("ASH_HISTORY")
#define LINE_LEN 1024
#define COMMANDS_NR 1000

int offset=0, history_fd;

char* go_up(list_t *history);
char* go_down(list_t *history);
void add_command(list_t *history_list, char *command);
void reset_history();
list_t* load_history();

list_t* load_history(){
    int read_nr=0;
    char *history_file="/tmp/ash_history", remains[LINE_LEN * 2],
         aux_line[LINE_LEN * 2], *command;
    char line[LINE_LEN];

    list_t *commands_list = malloc(sizeof(list_t));
    init_list(commands_list, COMMANDS_NR);

    aux_line[0] = '\0';

    if(ASH_HISTORY){
        history_file = malloc(strlen(ASH_HISTORY));
        strcpy(history_file, ASH_HISTORY);
    }

    history_fd = open(history_file, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);
    if(history_fd == -1)
        file_error(history_file);

    do{
        read_nr = read(history_fd, &line, sizeof line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            if(command[0] == 127) //DELETE char
                break;

            add_command(commands_list, command);
            strcpy(remains, command);
            command = strtok(NULL, "\n");
        }

        strcpy(aux_line, remains);

    } while(read_nr == LINE_LEN);

    return commands_list;
}

void add_command(list_t *commands_list, char* command) {
    if(strcmp(command, "") != 0){
        add_node(commands_list, command);
        reset_history();
    }
}

char* go_up(list_t *history) {
    int index = 0;
    node_t *current_node = history->head;

    // go to offset
    while(index < offset && current_node != NULL) {
        current_node = current_node->prev;
        index++;
    }

    if(current_node != NULL){
        offset++;
        return current_node->command;
    } else {
        return "";
    }
}

char* go_down(list_t *history) {
    int index = 0;
    node_t *current_node = history->head;

    if(offset - 1 > 0){
        // go to offset
        while(index < offset - 2 && current_node != NULL) {
            current_node = current_node->prev;
            index++;
        }
    } else {
        return "";
    }

    if(current_node != NULL){
        offset--;
        return current_node->command;
    } else {
        return "";
    }
}

void reset_history(){
    offset = 0;
}

#endif
