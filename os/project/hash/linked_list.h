#ifndef LINKED_LIST_H
#define LINKED_LIST_H

typedef struct node{
    struct node* next;
    struct node* prev;
    char command[100];
} node_t;

typedef struct list{
    int max_size;
    node_t *head;
    node_t *tail;
    int current_size;
} list_t;

void show_all(list_t *self);
char* find(list_t *self, char *entry);
void init_list(list_t *self, int max_size);
void add_node(list_t *self, char *command);

#endif
