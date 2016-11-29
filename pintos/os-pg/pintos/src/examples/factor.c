// 1401CS43
// Dnyaneshwar digambar shendurwadkar
#include <stdio.h>
#include <stdlib.h>
#include <syscall.h>
int is_prime(int factor);
int
main (int argc, char **argv)
{
  int i;
  int n = atoi(argv[1]);
  //printf("%d\n", n);
  int factors[100];
  for(int i=0;i<100;i++){
    factors[i]=0;
  }
  int c=0;
  int count=0;
  for(int i=2;i<=n;i++){
  	if(n%i==0){
  		int t = is_prime(i);
  		if(t==1){
  			factors[c] = i;                   // factors is array to store prime factors
        count++;
  			c++;
  			n=n/i;
  			i=1;
  		}
  	}
  }

  for(int i=0;i<count;i++){
    //printf("%d\n", factors[i]);
    int p=1;
    int q = factors[i];
    i++;
    while(q==factors[i]){
      p++;
      i++;
    }
    i--;
    if(i!=count-1)
      printf("%d^%d*",q,p);
    else
      printf("%d^%d",q,p);
  }
  printf("\n");




  return EXIT_SUCCESS;
}

int is_prime(int factor){
	int flag=0;
	for(int i=2;i<factor;i++){
		if(factor%i==0){
			flag = 1;
			return 0;
		}
	}
	if(flag==0){
		return 1;
	}
	return 1;
}
