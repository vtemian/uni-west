#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#ifdef READLINE_LIBRARY
#  include "history.h"
#else
#  include <readline/history.h>
#endif

#include <readline/readline.h>

int main() {
    char* input, shell_prompt[100];

    // autocomplete
    rl_bind_key('\t', rl_complete);

    while(1) {
        snprintf(shell_prompt, sizeof(shell_prompt), "%s:%s $ ",
                 getenv("USER"), getcwd(NULL, 1024));

        input = readline(shell_prompt);
        if (!input)
            break;

        if(!strcmp(input, "exit"))
            break;

        add_history(input);
        free(input);
    }

    return 0;
}
