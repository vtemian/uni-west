#include <sys/types.h>

#include <stdio.h>
#include <errno.h>
#include <sys/vfs.h>            /* statfs() */
#include <sys/uio.h>
#include <sys/stat.h>
#include <fcntl.h>

#ifndef O_PATH
#define O_PATH      010000000
#endif

#define AT_EMPTY_PATH       0x1000

#define _GNU_SOURCE

int main() {
    int fd = open("/tmp/b", O_PATH);
    //unlink("/tmp/a");

    printf("%d", fd);

    //int err = linkat(fd, "", AT_FDCWD, "/tmp/a", AT_EMPTY_PATH);
    //perror("error");
    return 0;
}
