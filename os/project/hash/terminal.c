#include "terminal.h"

int check_terminal(struct termios *to_check, int term_fd) {
    struct termios current_term;

    return (
        tcgetattr(term_fd, &current_term) == 0 &&             // get terminal attrs
        (to_check->c_lflag == current_term.c_lflag) &&        // check lflag struct
        (to_check->c_cc[VMIN] == current_term.c_cc[VMIN]) &&  // check min chars (0)
        (to_check->c_cc[VTIME] == current_term.c_cc[VTIME])   // check timeout (0)
    );
}

int prepare_terminal(int term_fd, struct termios *p) {
    struct termios term;
    errno = 0;

    // get current terminal's settings
    tcgetattr(term_fd, p);

    // set new terminal settings
    term = *p;
    term.c_lflag &= ~(ICANON | ECHO | ECHOE); // set canonical mode + echo chars on screen
    term.c_cc[VMIN] = 0;              // minimum chars
    term.c_cc[VTIME] = 0;             // timeout for noncanonical read

    return (
        tcgetattr(term_fd, p) == 0 &&                   // get terminal attrs
        tcsetattr(term_fd, TCSAFLUSH, &term) == 0 &&    // set terminal attrs
        check_terminal(&term, term_fd) != 0             // check terminal attrs
    );
}


