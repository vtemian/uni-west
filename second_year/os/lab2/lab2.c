#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int main() {
    int fd;
    char* s="awesome";
    fd = open("a.txt", O_CREAT|O_WRONLY|O_TRUNC, S_IRUSR);
    write(fd, s, strlen(s));
    close(fd);
    return 0;
}
