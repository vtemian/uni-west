#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    int fd, buff_size=1024;
    char *buff, *word;

    if(argc != 3){
        printf("Usage: %s filename word\n", argv[0]);
        return 1;
    }

    fd = open(argv[1], O_RDONLY);
    if(fd < 0) {
        printf("Cannot open the file!");
        return 1;
    }

    buff = (char *)malloc(buff_size);
    while(read(fd, buff, buff_size) == buff_size) {
    }
    close(fd);
    return 0;
}
