#include <stdio.h>
#include <stdlib.h>
#include "sys/wait.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

extern char **environ;

int main() {
    pid_t pid;
    int index=0, shmid, wpi, status;
    char array[200][200];
    char a[]="ana";
    char b[]="blana";

    shmid = shmget(IPC_PRIVATE, sizeof(array), 0660 | IPC_CREAT);

    char **environ = (char **)shmat(shmid,NULL,0);

    for(index=0;index<4;index++){
        memcpy(&environ[index], &a, sizeof(a));
    }

    pid = fork();
    if(pid == 0){
        char **environ = (char **)shmat(shmid,NULL,0);
        memcpy(&environ[0], &b, sizeof(b));
    } else {
        while((wpi=wait(&status)) > 0);
        printf("%s", environ);
        printf("%s", environ + 1);
    }
    return 1;
}
