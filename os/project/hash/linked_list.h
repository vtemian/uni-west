#ifndef LINKED_LIST
#define LINKED_LIST

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
#endif
