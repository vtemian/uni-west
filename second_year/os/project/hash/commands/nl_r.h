#ifndef NL_COMMAND_1
#define NL_COMMAND_1

#include "sys/wait.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include "../ver.h"

#define CONTENT 1000

int nl_r(int argc, char *argv[], char **environ){
    char content[LINE_LEN], *line, aux_line[CONTENT * 2], remains[CONTENT * 2], *tmp_char;
    int read_nr, line_nr=0, index, sep=0;
    char **lines = malloc(sizeof(char**)), *delimiter=NULL, *separator=NULL;

    if(argc > 1){
        if(strcmp(argv[1], "-s") == 0) {
            delimiter = malloc(sizeof(argv[2]));
            strcpy(delimiter, argv[2]);
        }
        if(strcmp(argv[1], "-d") == 0) {
            separator = malloc(sizeof(argv[2]));
            strcpy(separator, argv[2]);
        }
    }
    if (argc > 3) {
        if(strcmp(argv[3], "-s") == 0) {
            delimiter = malloc(sizeof(argv[4]));
            strcpy(delimiter, argv[4]);
        }
        if(strcmp(argv[3], "-d") == 0) {
            separator = malloc(sizeof(argv[4]));
            strcpy(separator, argv[4]);
        }
    }
    aux_line[0] = '\0';
    do {
        read_nr = read(STDIN_FILENO, &content, CONTENT);
        if(!read_nr)
            break;

        strcat(aux_line, content);
        line = strtok(aux_line, "\n");
        while(line != NULL) {
            if(line[0] == 127) //DELETE char
                break;

            if(separator != NULL){
                if(strcmp(separator, line) == 0) {
                    printf("\n");
                    sep = 1;
                }else if(!sep){
                    if(delimiter != NULL){
                        printf("\t %d%s %s\n", line_nr+1, delimiter, line);
                    }else{
                        printf("\t %d %s\n", line_nr+1, line);
                    }
                }else{
                    printf("\t\t %s\n", line);
                }
            }else{
                if(delimiter != NULL){
                    printf("\t %d%s %s\n", line_nr+1, delimiter, line);
                }else{
                    printf("\t %d %s\n", line_nr+1, line);
                }
            }

            strcpy(remains, line);
            line = strtok(NULL, "\n");
            line_nr++;
        }

        strcpy(aux_line, remains);

    } while(read_nr != 0);

    exit(1);
};

#endif
