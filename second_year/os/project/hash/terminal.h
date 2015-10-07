#ifndef TERMINAL
#define TERMINAL

#include <termios.h>
#include <errno.h>

int check_terminal(struct termios *to_check, int term_fd);
int prepare_terminal(int term_fd, struct termios *p);

#endif
