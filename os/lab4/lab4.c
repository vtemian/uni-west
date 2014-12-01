#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <time.h>
#include <unistd.h>

#define CHECK 1500000
#define WORKERS 3

int isPrime(n) {
    int i;
    if(n == 0 || n==1 || n==2 || n==3) return 1;
    for (i=2; i<=n/2; i++) {
        if (n % i == 0) return 0;
    }
    return 1;
}

void secvential() {
    int i;

    for(i=0; i<=CHECK; i++) {
        if(isPrime(i)){
            //printf("secvential: %d is prim\n", i);
        }
    }

}
int parallel() {
    int i, j, pid;

    for(i=1; i<=WORKERS; i++){
        pid = fork();
        if( pid == 0) {
            for(j=(i-1) * (CHECK / WORKERS) + 1; j<=i*(CHECK / WORKERS); j++) {
                if(isPrime(j)){
                    //printf("parallel: Child %d found prime %d\n", i, j);
                }
            }
            exit(0);
        }
    }

    for(i=1; i<=WORKERS; i++) {
        wait(&i);
    }
    return 0;
}

int main(int argc, char **argv) {
    long int start, end, pid;
    double cpu_time_used;

    if(fork() == 0){
        start = (unsigned)time(NULL);
        parallel();
        end = (unsigned)time(NULL);
        printf("paralel time: %d seconds\n", end - start);
    } else if(fork() == 0){
        start = (unsigned)time(NULL);
        secvential();
        end = (unsigned)time(NULL);
        printf("secvential time: %d seconds\n", end - start);
    }else {
        printf("wait for kids");
        wait(&start);
        wait(&start);
    }

    return 0;
}
