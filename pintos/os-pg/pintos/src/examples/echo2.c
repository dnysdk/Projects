#include <stdio.h>
#include <syscall.h>

int
main (int argc, char **argv)
{
  printf("hello world %d \n",argc);
  printf("%s\n",argv[1]);

  return EXIT_SUCCESS;
}
