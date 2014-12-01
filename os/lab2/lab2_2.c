#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    int fd, i, n;
    char* buff;

    if(argc != 3){
        printf("Usage: %s filename filesize\n", argv[0]);
        return 1;
    }

    fd = open(argv[1], O_CREAT|O_WRONLY|O_TRUNC, S_IRUSR);
    if(fd < 0) {
        printf("Cannot open the file!");
        return 1;
    }
    n = atoi(argv[2]);
    buff = (char *) malloc(n);
    for(i=0; i<n; i++){
        buff[i] = 'a' + rand() % ('z' - 'a');
    }
    write(fd, buff, n);
    close(fd);
    return 0;
}
