#include <syscall.h>
#include <stdio.h>
int main (int argc, char** argv)
{
int i;
/*
 * for (i = 0; i < argc; i++)
 * 
printf ("%s ", argv[i]);
 * printf ("\n");
 */
printf("In Echo");
putchar('\n');
//fork();
//wait_();
return EXIT_SUCCESS;
}