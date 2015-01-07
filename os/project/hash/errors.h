#ifndef ERRORS
#define ERRORS

void term_error() {
    fprintf(stderr, "An error occured\n");
    perror("");
    exit(1);
}

void file_error(char* filename) {
    fprintf(stderr, "An error occured when we tried to access the file %s\n",
                    filename);
    perror("");
    exit(1);
}

#endif
