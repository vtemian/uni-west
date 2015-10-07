/*
    Write a program that creates a child process and waits for an input command
    (it reads it from the standard input).
         a) If the command is "show filename.txt" (no quotes), then the parent
    process will read the file filename.txt entirely and send it to the child
    process; the child process will send back to the parent the number of total
    words in that file, and the parent will show that number on screen.
        b) If the command is "trim filename.txt" (no quotes), the parent will no
    longer read the file, instead the child process will send to the parent
    the last 10 lines from the file filename.txt and the parent will show
    those lines on screen.
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

#define PIPES_NUMBER 2
#define MAX_LEN 200
#define LINES 10
#define USAGE printf("%s", usage);

char *strrev(char *str){
    char *p1, *p2;

    if (! str || ! *str)
        return str;

    for (p1 = str, p2 = str + strlen(str) - 1; p2 > p1; ++p1, --p2){
        *p1 ^= *p2;
        *p2 ^= *p1;
        *p1 ^= *p2;
    }

    return str;
}

int get_words(char* line){
    int counted = 0, inword = 0;

    do switch(*line) {
        case '\0':
        case ' ': case '\t': case '\n': case '\r':
            if (inword) { inword = 0; counted++; }
            break;
        default: inword = 1;
    } while(*line++);

    return counted;
}

void child_process(int *out, int *in) {
    int words=0, fd, bytes_read=0, current_line, position;
    char command[MAX_LEN], filename[MAX_LEN], line[MAX_LEN], c;

    close(in[1]);
    close(out[0]);

    read(in[0], command, MAX_LEN);

    if(!strcmp(command, "show")) {
        do{
            read(in[0], &command, MAX_LEN);
            words += get_words(command);
        }while(!strcmp(command, "EOF"));
        words -= 1;

        write(out[1], &words, sizeof(words));
    }else if(!strcmp(command, "trim")){
        read(in[0], &filename, MAX_LEN);
        printf("file: %s", filename);

        fd = open(filename, O_RDONLY);
        lseek(fd, 0, SEEK_END);

        while(current_line < LINES){
            position = 0;

            do{
                lseek(fd, -2, SEEK_CUR);
                read(fd, &c, 1);
                line[position] = c;
                position += 1;
            }while(c != '\n');

            line[position] = '\0';
            current_line += 1;

            strrev(line);

            write(out[1], line, MAX_LEN);
        }
    }
}

void parent_process(int *in, int *out) {
    char command[MAX_LEN], filename[MAX_LEN], line[MAX_LEN], words, i;
    int fd, bytes_read=0;

    close(in[1]);
    close(out[0]);

    printf("> ");
    scanf("%s %s", command, filename);

    write(out[1], command, sizeof(command));

    if(!strcmp(command, "show")) {
        fd = open(filename, O_RDONLY);

        do {
            bytes_read = read(fd, line, MAX_LEN);
            write(out[1], line, MAX_LEN);
        }while(bytes_read);

        read(in[0], &words, sizeof(words));
    } else if(!strcmp(command, "trim")) {

        write(out[1], filename, sizeof(command));

        for(i=0; i<LINES; i++){
            read(in[0], line, MAX_LEN);
            printf("%s", line);
        }

    } else {
        printf("I don't understand you! Try again!\n\n");
        USAGE;
        parent_process(in, out);
    }
}

int main(int argc, char **argv){
    int n, i, pid, pipes[PIPES_NUMBER][2];

    for(i=0; i<PIPES_NUMBER; i++)
        if(pipe(pipes[i]) < 0){
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
