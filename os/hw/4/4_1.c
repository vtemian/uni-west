/*
   Write a program that creates N child processes, where N is taken from the
   command line, and makes each child process with index I compute the total
   number of divisors of the number I*10 and return them. The parent process
   will read each result returned by the children and show them on screen.

   * Example:
    N=5, child 0 will compute the number of divisors of 0, child 1 will compute
    the number of divisors of 10, child 2 the number of divisors of 20 and so
    on.  Parent returns: "for child 0, I found 4 divisors", "for child 1, I
    found 6 divisors", etc.

   * Desired input:
    ./app N

   * Example:
    ./app 5
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

char* usage  = "\n\
Usage: ./4_1 N\n\n\
   N int       number of child processes\
";

#define USAGE printf("%s", usage)

void compute_divisor(int n, int *pipes){
    int i, divisors=0;

    close(pipes[0]);
    for(i=1; i<=n * 10; i++){
        if((n * 10) % i)
            divisors += 1;
    }

    write(pipes[1], &divisors, sizeof(divisors));
    close(pipes[1]);
}

void read_divisor(int i, int *pipes) {
    int divisors;
    close(pipes[1]);
    read(pipes[0], &divisors, sizeof(divisors));
    printf("Child %d found %d divisors\n", i, divisors);
    close(pipes[0]);
}

int main(int argc, char **argv){
    int n, i, pid, **pipes, len=200, status, j, read=0;
    pid_t wpid;

    if(argc != 2){
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

    pipes = (int**)malloc(n * sizeof(int*));
    for(i=0; i<n; i++){
        // create pipe
        pipes[i] = (int*)malloc(2 * sizeof(int));
        if(pipe(pipes[i]) < 0){
            printf("We had an error when we tried to create the pipes %d", i);
            perror("pipe");
            exit(0);
        }

        // create processes
        pid = fork();
        if(pid < 0){
            printf("We had an erorr when we tried to fork");
            exit(0);
        }else if(pid == 0){ // we are in child
            compute_divisor(i, pipes[i]);
            exit(0);
        }else{
            // we have a limit of 1024 pipes on this machine
            if(i % 500 == 0 && i > 0){
                // free the pipes after we used it
                for(j=i - 500; j<i; j++){
                   read_divisor(j, pipes[j]);
                   read += 1;
                }
            }
        }
    }

    // read and close remaing open pipes
    for(i=read; i<n; i++){
       read_divisor(i, pipes[i]);
    }

    return 0;
}
