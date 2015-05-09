/*
    Write a program that creates N child processes, where N is taken from the
    command line, and also receives a filename and an input string. The program
    takes the input file and splits it so that each child process with the
    index I will process exactly the Ith part of the file, as follows: each
    child will determine if its allocated data chunk contains the input string
    or not, and all positions where matches occur. If matches are found, it
    will write to a pipe the locations of all occurrences.

    Requirements:
    a) Use a SINGLE PIPE for communication between the parent and all children.
    b) BONUS points will be awarded if you can figure out a way to avoid misses
    if a string has a partial match in two different child processes (e.g. for
    "testing this", "testing " is partially matched by the first process and
    "this" by the second).  c) You can use strstr() to determine occurrences in
    a child process if you'd like, although that's not the optimal approach.

    * Example call:
    ./app 5 input.txt "Something to search for"
    Found occurrence at position 123
    Found occurrence at position 567
  */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>

char *usage = "Usage:\n\
    show|trim <filename>\n\
        show        get the number of word from the file\n\
        trim        show the last 10 line from the file\n\n";

#define MAX_LEN 200
#define USAGE printf("%s", usage);

int main(int argc, char **argv){
    int n, i, pid, pipes[2];

    if(pipe(pipes) < 0){
        printf("We had an error when we tried to create the pipes %d", i);
        perror("pipe");
        exit(0);
    }

    // create processes
    pid = fork();
    if(pid < 0){
        printf("We had an erorr when we tried to fork");
        exit(1);
    }else if(pid == 0){ // we are in child
        child_process(pipes[0], pipes[1]);
        exit(0);
    }else{
        parent_process(pipes[0], pipes[1]);
    }

    return 0;
}
