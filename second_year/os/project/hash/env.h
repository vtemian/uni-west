char *get_env(char *key, char **env);
void set_env(char *key, char *value, char *path);
void copy_env(char *path, char **env);
void load_env(char *path);
