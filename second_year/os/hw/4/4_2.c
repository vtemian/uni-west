/*
    Write a program that creates N child processes, where N is taken from the
    command line, and also receives a filename. The program takes the input
    file and splits it so that each child process with the index I will show
    the exact Ith part of the file.

    * Example:
    Input file is 100 bytes in size and N=5. Child 0 will display bytes from 0
    to 19, child 1 from 20 to 39, child 2 from 40 to 59 and so on.

    * Desired input:
    ./app N input_file

    * Example call:
    ./app 5 input.txt

 */

#define _XOPEN_SOURCE 500

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>

char* usage  = "\n\
Usage: ./4_1 <N> <filename>\n\n\
   N        int          number of child processes\n\
   filename string       filename to lookup\n\
";

#define USAGE printf("%s", usage)

void read_file(int fd, int index, int size) {
    char buff[size];
    size_t nbytes = size;
    off_t offset = index * nbytes;
    int error;

    error = pread(fd, &buff, nbytes, offset);
    if(error < 0)
        printf("ERROR: %s\n", strerror(errno));
    else{
        printf("%s", buff);
    }
}

int main(int argc, char **argv){
    pid_t wpid;
    struct stat stats;
    int n, i, pid, file, size, status;

    if(argc != 3){
        printf("Wrong number of arguments\n");
        USAGE;
        exit(1);
    }

    n = atoi(argv[1]);
    if(!n){
        printf("Wrong argument type for <N>\n");
        USAGE;
        exit(1);
    }

    if(access(argv[2], F_OK ) == -1){
        printf("Filename %s doesn't exists", argv[2]);
        USAGE;
    }

    file = open(argv[2], O_RDONLY);
    if(file < 1) {
        printf("We can't open the file: %s ", argv[2]);
        USAGE;
    }

   if(fstat(file, &stats) < 0){
        printf("We can't get the stats for file: %s ", argv[2]);
        USAGE;
   }
   size = stats.st_size;

   for(i=0; i<n; i++){
        // create processes
        pid = fork();
        if(pid < 0){
            printf("We had an erorr when we tried to fork");
            perror("fork");
            exit(0);
        }else if(pid == 0){ // child
            read_file(file, i, (size / n));
            exit(0);
        }
    }

     while ((wpid = wait(&status)) > 0);

    return 0;
}
