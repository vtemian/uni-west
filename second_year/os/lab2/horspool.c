#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int pattern_alph[26];

void create_alphabet(char *pattern) {
    int i, len=strlen(pattern), shift;
    for(i=0; i<len; i++) {
        shift = len - i - 1;
        pattern_alph[pattern[i] - 'a'] = shift ? shift : 1;
    }
}

int main(int argc, char **argv) {
    int index, *alph, found=0, shift_by=0, len_pattern;
    int fd, buff_size=1024;
    char *text, *pattern, *plus, *buff;

    // check for right arguments
    if(argc != 3){
        printf("Usage: %s filename word\n", argv[0]);
        return 1;
    }

    // try to open the file
    fd = open(argv[1], O_RDONLY);
    if(fd < 0) {
        printf("Cannot open the file!");
        return 1;
    }

    // create the alphabet using Boyer-Moore table
    pattern = argv[2];
    len_pattern = strlen(pattern);
    create_alphabet(pattern);

    for(index=0; index<27; index++){
        if(pattern_alph[index] == 0)
            pattern_alph[index] = len_pattern - 1;
    }


    // allocate enough memory
    text = (char *)malloc(buff_size + len_pattern + 2);
    buff = (char *)malloc(buff_size);
    plus = (char *)malloc(len_pattern);
    plus = "";

    // read chuncks of data from a big file
    while(read(fd, buff, buff_size) != 0 && !found) {
        index = strlen(pattern) - 1;

        // because we have chunks, each iteration bring back some chars and
        // append them to the actual string from buffer
        if(strcmp("", plus) == 0){
            strcpy(text, buff);
        }else{
            strcpy(text, plus);
            strcat(text, buff);
        }

        // apply horspool algorithm
        while(index < strlen(text)){
            shift_by = 0;

            // check last letter, if it match, go back and try to match the
            // pattern
            while((shift_by < len_pattern) && (pattern[len_pattern - 1 - shift_by] == text[index - shift_by]))
                shift_by++;

            // we found the word if we shift until the start of it
            if(shift_by==len_pattern){
                found = 1;
                break;
            }else{
                // if not, check if there is a character from our alphabet. if
                // not, we shift by the length of the pattern, otherwise, check
                // the table and see how much we shift
                if(text[index] - 'a' < 0 || text[index] - 'a' > 26)
                    index += len_pattern;
                else
                    index += pattern_alph[text[index]- 'a'];
            }
        }

        // we want to append some text to the buffer next time
        plus = text + (strlen(text) - len_pattern);
    }

    if(found){
        printf("I FOUND %s in %s", pattern, argv[1]);
    } else {
        printf("I DIDN'T FOUND %s in %s", pattern, argv[1]);
    }

    close(fd);
    return 0;
}
