#include <stdio.h>
#include <stdlib.h>
#include "sys/wait.h"
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define LINE_LEN 1000

char *get_env(char *key, char **env) {
    int index;
    char *value;

    printf("sugi pula%s", key);
    printf("%s", key);
    fflush(stdout);
    index=0;
    while(env[index]) {
        if(strstr(env[index], key) != NULL) {
            value = malloc(strlen(env[index] + strlen(key)) + 1);
            strcpy(value, env[index] + strlen(key) + 1);
            return value;
        }
        index++;
    }
    return NULL;
}

void set_env(char *key, char *value, char *path){
    int env_fd, read_nr, index=0, i;
    char line[LINE_LEN], aux_line[LINE_LEN], *command, remains[LINE_LEN * 2];
    char **environ = malloc(sizeof(char**));
    char new_value[strlen(key) + strlen(value) + 1];
    char lookup[strlen(key) + 2];

    strcpy(new_value, key);
    strcat(new_value, "=");
    strcat(new_value, value);

    strcpy(lookup, key);
    strcat(lookup, "=");

    aux_line[0] = '\0';

    env_fd = open(path, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);
    do{
        read_nr = read(env_fd, &line, sizeof(line));
        printf("shit %s\n ", line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            if(strstr(command, lookup) != NULL && lookup[0] == command[0]) {
                environ[index] = malloc(strlen(new_value));
                strcpy(environ[index], new_value);
                printf("new value %s\n", environ[index]);
                fflush(stdout);
            }else{
                environ[index] = malloc(strlen(command));
                strcpy(environ[index], command);
            }

            strcpy(remains, command);
            command = strtok(NULL, "\n");
            index++;
        }

        strcpy(aux_line, remains);

    } while(read_nr == LINE_LEN);

    lseek(env_fd, 0, SEEK_SET);
    for(i=0; i<index; i++){
        strcpy(line, environ[i]);
        strcat(line, "\n");
        printf("to write: %s", line);
        fflush(stdout);
        write(env_fd, line, strlen(line));
    }

    close(env_fd);
}

void copy_env(char *path, char **env){
    int index=0, env_fd;
    char *line;

    env_fd = open(path, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);
    lseek(env_fd, 0, SEEK_SET);

    while(env[index]) {
        line = malloc(strlen(env[index]) + 1);
        strcpy(line, env[index]);
        strcat(line, "\n");
        write(env_fd, line, strlen(line) + 1);
        index++;
    }

    close(env_fd);
}

void load_env(char *path) {
    int env_fd, read_nr, index=0;
    char line[LINE_LEN], aux_line[LINE_LEN], *command, remains[LINE_LEN * 100];

    env_fd = open(path, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);

    aux_line[0] = '\0';

    do{
        read_nr = read(env_fd, &line, sizeof line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            if(command[0] >= '0' && command[0] <= '9') {
                command = NULL;
                continue;
            }
            putenv(command);
            strcpy(remains, command);
            command = strtok(NULL, "\n");
            index++;
        }

    } while(read_nr == LINE_LEN);
    close(env_fd);
}
