#include <stdio.h>
#include <stdlib.h>
#include "sys/wait.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define LINE_LEN 5000

extern char **environ;

char *get_env(char *key, char *path) {
    int env_fd, read_nr, index=0, i;
    char line[LINE_LEN], aux_line[LINE_LEN], *command, remains[LINE_LEN * 2], *value, *aux;

    env_fd = open(path, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);
    lseek(env_fd, 0, SEEK_SET);

    do{
        read_nr = read(env_fd, &line, sizeof line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            //printf("read command %s", command);
            if(strstr(command, key) != NULL && key[0] == command[0]) {
                aux = command + strlen(key) + 1;
                value = malloc(strlen(aux));
                value = strcpy(value, aux);
                return value;
            }

            strcpy(remains, command);
            command = strtok(NULL, "\n");
            index++;

        }
    } while(read_nr == LINE_LEN);
    close(env_fd);

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
    lseek(env_fd, 0, SEEK_SET);

    do{
        read_nr = read(env_fd, &line, sizeof line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            if(strstr(command, lookup) != NULL && lookup[0] == command[0]) {
                environ[index] = malloc(strlen(new_value));
                environ[index][0] = '\0';
                strcpy(environ[index], new_value);
            }else{
                environ[index] = malloc(sizeof(char*));
                environ[index] = command;
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
        line = malloc(sizeof(env[index]) + 1);
        strcpy(line, env[index]);
        strcat(line, "\n");
        write(env_fd, line, strlen(line));
        index++;
    }

    close(env_fd);
}

char **load_env(char *path, int *size) {
    int env_fd, read_nr, index=0;
    char line[LINE_LEN], aux_line[LINE_LEN], *command, remains[LINE_LEN * 4];
    char **vars = malloc(sizeof(char***));

    env_fd = open(path, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG);
    lseek(env_fd, 0, SEEK_SET);

    aux_line[0] = '\0';

    do{
        read_nr = read(env_fd, &line, sizeof line);
        if(!read_nr)
            break;

        strcat(aux_line, line);

        command = strtok(aux_line, "\n");
        while(command != NULL) {
            vars[index] = malloc(sizeof(char*));
            vars[index] = command;
            printf("command %s\n", command);

            strcpy(remains, command);
            command = strtok(NULL, "\n");
            index++;
        }

    } while(read_nr == LINE_LEN);
    close(env_fd);

    *size = index;
    return vars;
}

int main() {
    pid_t pid;
    int wpid, status, size;
    char *env = "/tmp/env";
    char **vars;

    copy_env(env, environ);
    pid = fork();

    if(pid == -1){
        perror("fork error");
        exit(1);
    }

    if(pid == 0){
        set_env("PWD", "/home", env);
        set_env("OLDPWD", "/home/university/os", env);
        chdir("/home");
    } else {
        while((wpid=wait(&status)) > 0);

        size = 0;
        vars = load_env(env, &size);
        printf("%d", size);
        status=0;
        while(status < size){
            printf("%s\n", vars[status]);
            status++;
        }
    }

    return 0;
}
