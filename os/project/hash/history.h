#ifndef HISTORY
#define HISTORY

int offset=0;

void load_history();
void add_command();
char* go_up(int current_index);
char* go_down(int current_index);

#endif
