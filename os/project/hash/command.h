#ifndef COMMAND
#define COMMAND

#include <sys/types.h>
#include <sys/time.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <string.h>

#include "errors.h"
#include "terminal.h"

#define NANOS_SLEEP 500
#define SECONDS_SLEEP 0
#define COMMAND_LEN 1024

char* get_command(){
    unsigned char in_char;
    char* in_command = malloc(COMMAND_LEN);
    int index=0, read_nr=0, term_fd=fileno(stdin), reading=1;

    struct termios  attr;
    struct termios *current_terminal=&attr;
    struct timespec tsp={SECONDS_SLEEP, NANOS_SLEEP};  // sleep 500 usec

    in_command[index] = '\0';

    // flush stdout
    fflush(stdout);

    // set new terminal attrs
    prepare_terminal(term_fd, current_terminal);

    while(reading){
        nanosleep(&tsp, NULL);

        // read one char from terminal
        read_nr = read(term_fd, &in_char, sizeof (in_char));

        switch(read_nr) {
            case 0:
            default:
                break;
            case -1:
                 fprintf(stdout, "Read error %s", strerror(errno));
                 exit(1);
                 break;
            case 1:
                 if(in_char != '\n'){
                     in_command[index++] = in_char;
                 }else{
                     in_command[index] = '\0';
                     reading = 0;
                 }
                 break;
        }
    }

    if(tcsetattr(term_fd, TCSADRAIN, current_terminal) == -1 &&
       tcsetattr(term_fd, TCSADRAIN, current_terminal) == -1 )
      term_error();

    return in_command;
}

#endif
