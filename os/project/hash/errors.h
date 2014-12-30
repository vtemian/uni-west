#ifndef ERRORS
#define ERRORS

void term_error() {
     fprintf(stderr, "An error occured\n");
     perror("");
     exit(1);
}

#endif
