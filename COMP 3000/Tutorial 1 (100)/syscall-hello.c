#include <unistd.h>
#include <sys/syscall.h>

char *buf = "Hello world!\n";

int main(int argc, char *argv) {
        size_t result;

        /* "man 2 write" to see arguments to write syscall */
        result = syscall(SYS_write, 1, buf, 13);

        return (int) result;
}
