#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "linked_list.h"

void init_list(list_t *self, int max_size) {
    self->current_size = 0;
    self->head = NULL;
    self->tail = NULL;
    self->max_size = max_size;
}

void show_all(list_t *self) {
    node_t* current_node = self->tail;
    int index=1;

    while(index <= self->current_size) {
        fprintf(stdout, "%s\n", current_node->command);
        current_node = current_node->next;
        index++;
    }
}

void add_node(list_t *self, char *command){
    node_t* new_node = malloc(sizeof(node_t));
    new_node->prev = new_node->next = NULL;

    strcpy(new_node->command, command);

    if(self->head == NULL){
        self->head = self->tail = new_node;
    }else{
        self->head->next = new_node;
        new_node->prev = self->head;
        self->head = new_node;
    }

    self->current_size++;

    if(self->current_size > self->max_size) {
        self->tail->next->prev = NULL;
        self->tail = self->tail->next;
        self->current_size--;
    }

}

char *find(list_t *self, char *entry) {
    char *command;
    node_t* current_node = self->head;

    while(current_node != NULL) {
        if(strstr(entry, current_node->command) != NULL){
            command = malloc(sizeof(current_node->command));
            strcpy(command, current_node->command);
            return command;
        }
    }

    return NULL;
}
