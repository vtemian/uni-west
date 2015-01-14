#ifndef CD_COMMAND_1
#define CD_COMMAND_1

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include "../ver.h"


void str_cpy(char *dest, char src[9000]) {
    char a='a';
    int size=strlen(src), index=0;
    printf("%d\n", &dest[0]);
    for(index=0; index<size; index++){
        dest[index] = 'a';
    }
    dest[index] = '\0';
}

void set_env(char *key, char *value, char **environ) {
    int index=0;
    char *new_value = malloc(strlen(key) + strlen(value) + 2);

    strcpy(new_value, key);
    strcat(new_value, "=");
    strcat(new_value, value);

    while(environ[index]){
        if(strstr(environ[index], key) != NULL && environ[index][0] == key[0]) {
            memcpy(&environ[index], &new_value, strlen(new_value));
            break;
        }
        index++;
    }
}

char *get_env(char *key, char **environ){
    int index=0;
    char *value, *aux;

    while(environ[index]){
        if(strstr(environ[index], key) != NULL && key[0] == environ[index][0]) {
            aux = environ[index] + strlen(key) + 1;
            value = malloc(strlen(aux));
            value = strcpy(value, aux);
            return value;
        }
        index++;
    }
    return NULL;
}

int cd_r(int argc, char *argv[], char **environ){
    struct stat stat_t;
    int err;

    if(argc != 1){
        printf("Usage: cd <new_directory>\n");
        fflush(stdout);
    }

    err = stat(argv[1], &stat_t);
    if(err == -1) {
        if(ENOENT == errno) {
            printf("%s is not a directory\n", argv[1]);
            fflush(stdout);
        } else {
            perror("stat");
            fflush(stdout);
        }
    } else {
        if(S_ISDIR(stat_t.st_mode)) {
            err = chdir(argv[1]);
            if(err == -1) {
                perror("chdir");
                fflush(stdout);
            } else {
                set_env("OLDPWD", get_env("PWD", environ), environ);
                set_env("PWD", argv[1], environ);
            }
        } else {
            printf("%s is not a directory\n", argv[1]);
            fflush(stdout);
        }
    }
    exit(1);
};

#endif
