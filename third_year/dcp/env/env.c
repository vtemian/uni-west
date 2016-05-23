#include <stdio.h>

int main(int argc, char *argv[], char* envp[]) {
    int i=0;

    if (argc > 0) {
        for (i=0; i < argc; i++) {
            printf("%s\n", argv[i]);
        }
    }

    while(*envp != NULL) {
        printf("%s\n", *envp);
        *envp++;
    }

    return 0;
}
