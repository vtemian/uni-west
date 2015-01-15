#ifndef UNIQ_COMMAND_1
#define UNIQ_COMMAND_1

#include "sys/wait.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include "../ver.h"

#define CONTENT 1000

int uniq_r(int argc, char *argv[], char **environ){
    int read_nr, index, line_cnt, line_index=0, i=1, d=0, dup_index=0, dup_size=0, already_dup, uniq, second_index, uniq_size=0, uniq_index=0, u=0, simple_size=0, simple_index=0;;
    char content[CONTENT], *line;
    char **lines=malloc(sizeof(char**));
    char **duplicate=malloc(sizeof(char**));
    char **uniqs=malloc(sizeof(char**));
    char **simple=malloc(sizeof(char**));

    lines[0] = malloc(sizeof(char*));
    duplicate[0] = malloc(sizeof(char*));
    uniqs[0] = malloc(sizeof(char*));
    simple[0] = malloc(sizeof(char*));

    if(argc >= 1){
        if(strcmp(argv[1], "-i") == 0) {
            i = 1;
        }
        if(strcmp(argv[1], "-u") == 0) {
            u = 1;
        }
        if(strcmp(argv[1], "-d") == 0) {
            d = 1;
        }
    }

    do{
        read_nr = read(STDIN_FILENO, &content, CONTENT);
        if(!read_nr)
            break;

        index=0;
        while(index < read_nr){
            line = malloc(CONTENT);
            line[0] = '\0';
            line_cnt = 0;
            while(content[index] != '\n' && index < read_nr){
                line[line_cnt++] = content[index++];
                line[line_cnt] = '\0';
            }

            lines[line_index] = malloc(sizeof(line));
            lines[line_index++] = line;

            index++;
        }
    } while(read_nr != 0);
    for(index=0; index < line_index; index++){
        uniq = 1;
        already_dup = 0;

        for(second_index=0; second_index < line_index; second_index++){
            if(second_index != index && strcmp(lines[index], lines[second_index]) == 0){
                for(dup_index=0; dup_index < dup_size; dup_index++){
                    if(strcmp(duplicate[dup_index], lines[index]) == 0){
                        already_dup = 1;
                        uniq = 0;
                        break;
                    }
                }

                if(!already_dup){
                    duplicate[dup_size] = malloc(sizeof(lines[index]));
                    duplicate[dup_size++] = lines[index];
                }

                uniq = 0;
                break;
            }
        }
        if(uniq) {
            uniqs[uniq_size] = malloc(sizeof(lines[index]));
            uniqs[uniq_size++] = lines[index];
        }
        if(!already_dup || uniq) {
            simple[simple_size] = malloc(sizeof(lines[index]));
            simple[simple_size++] = lines[index];
        }
    }
    if(d){
        for(index=0; index < dup_size; index++) {
            printf("%s\n", duplicate[index]);
        }
    }else if(u){
        for(index=0; index < uniq_size; index++) {
            printf("%s\n", uniqs[index]);
        }
    }else if(i){
        for(index=0; index < simple_size; index++) {
            printf("%s\n", simple[index]);
        }
    }
    fflush(stdout);
    exit(1);
};

#endif
