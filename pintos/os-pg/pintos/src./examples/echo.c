#include <stdio.h>
#include <syscall.h>

int
main (int argc, char **argv)
{
  int i;
 printf("Hello Args : %d \n", argc);
 
  for (i = 0; i < argc; i++)
  {
    printf ("%s \n", argv[i]);
  }
  printf ("\n");

  return EXIT_SUCCESS;
}
