#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(){
    pid_t pid;
    int ret;
    int pipe1[2];
    int pipe2[2];

    char buffer[128];
    char *cat[] = {"/bin/cat", "cat", "/home/wok/university/os/project/ash.c"};
    char *grep[] = {"/bin/grep", "grep", "int"};

    pipe(pipe1);

    pid = fork();
    if(pid < 0){
        perror("error when we forked");
        exit(1);
    }

    if(pid == 0) {
        close(pipe1[0]);
        dup2(pipe1[1], 1);
        close(pipe1[1]);

        execv(cat[0], cat + 1);
        exit(1);
    }

    pipe(pipe2);
    pid = fork();
    if(pid < 0){
        perror("error when we forked");
        exit(1);
    }

    if(pid == 0) {
        close(pipe1[1]);
        dup2(pipe1[0], 0);
        close(pipe1[0]);

        close(pipe2[0]);
        dup2(pipe2[1], 1);
        close(pipe2[1]);

        execv(grep[0], grep + 1);
        exit(1);
    }

    close(pipe1[0]);
    close(pipe1[1])
    close(pipe2[1]);

    ret = read(pipe2[0], buffer, 128);
    if (ret <= 0) {
         printf("Reading problem!\n");
         return 0;
    }

    buffer[ret] = '\0';
    printf("Buffer: %s\n", buffer);

    return 0;
}
