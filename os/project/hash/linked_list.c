#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "linked_list.h"
#include "history.h"

void init_list(list_t *self, int max_size, int element_size) {
    self->current_size = 0;
    self->head = NULL;
    self->tail = NULL;
    self->max_size = max_size;
    self->element_size = element_size;
}

void show_all(list_t *self) {
    node_t* current_node = self->tail;
    int index=1;

    while(index <= self->current_size) {
        fprintf(stdout, "%s\n", (char *)current_node->data);
        current_node = current_node->next;
        index++;
    }
}

void* add_node(list_t *self, void *entry){
    node_t* new_node = (node_t*)malloc(sizeof(node_t));
    new_node->prev = new_node->next = NULL;
    new_node->data = entry;

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

    return new_node->data;
}

void *find(list_t *self, void *entry, int(*comparator)(void*, void*)) {
    node_t* current_node = self->head;

    while(current_node != NULL) {
        if(comparator((void*)current_node->data, (void*)entry)){
            return current_node->data;
        }
        current_node = current_node->prev;
    }

    return NULL;
}
