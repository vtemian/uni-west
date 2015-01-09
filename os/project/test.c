#include <unistd.h>
#include <stdio.h>
#include <errno.h>

int main(int argc, char **argv) {
    //char *code= {"cat", "-b", "-v", "-t", "ash.c", (char*) 0};
    char* code[] = {"ls", "-lah", "."};
    execv("/bin/ls", code);
    perror("hello");
    return 0;
}
