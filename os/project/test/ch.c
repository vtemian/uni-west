#include "string.h"
#include "stdio.h"
#include "stdlib.h"

int main(){
    char a[20];
    char *b=malloc(20);

    strcpy(a, "ab");
    memcpy(b, a, 20);

    printf("%s\n", b);
    return 0;
}
