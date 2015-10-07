#ifndef LINKED_LIST_H
#define LINKED_LIST_H

typedef struct node{
    struct node* next;
    struct node* prev;
    void *data;
} node_t;

typedef struct list{
    int max_size;
    node_t *head;
    node_t *tail;
    int current_size;
    int element_size;
} list_t;

void show_all(list_t *self);
void* find(list_t *self, void *entry, int(*comparator)(void*, void*));
void init_list(list_t *self, int max_size, int element_size);
void* add_node(list_t *self, void *entry);

#endif
