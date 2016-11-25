/*
1.Dnyaneshwar D S (1401CS43)

*/


/* 
For heap we are taking two dimentational array 
here heap[i][0] is data in heap and heap[i][1] is 0 or 1. 0 if that block is free and 1 if that block is allocated

heap data contains -2 if block is allocated or 0 if block is empty or some positive data showing number of allocated space 
or number of free space above that blcok.


*/

import java.util.*;
class memory{  					// class for heap 
	int size;					// heap is implemented using array
	int heap[][];				// size is heap size - user input
	memory(int size){			// initializing array
		this.size = size;			
		heap = new int[size][2];
		heap[0][0] = size;
		heap[1][0] = -1;
	}

	int first_fit_allocate(int set){   		// function for allocating memory for first fit
		int flag=0;
		int k=0;
		for(int i=0;i<size;i++){
			int count=0;
			if(heap[i][1] == 0 && heap[i][0]!=-2){
				if(heap[i][0]>=set+1){
					heap[i][0] = set+1;
					heap[i][1] = 1;
					i++;
					k = i;
					while(set!=0){
						heap[i][0] = -2;
						heap[i][1] = 1;
						i++;
						set--;
					}
					int j = i;

					for(;j<size;j++){
						if(heap[j][1]==0){
							count++;
						}else{
							break;
						}
					}
					if(count>0){
						heap[i][0] = count;
						i++;
					}
					
					for(int u=size-1;u>=0;u--){
						//System.out.println("efui");
						if(heap[u][1]==0 && heap[u][0]!=0 && u+1<size){
							//System.out.println("sdiuh" + u);
							heap[u+1][0] = -1;
							break;
						}
					}
					flag=1;
					return k; 
					//break;
				}
			}
		}
		if(flag==0){  				// checking for free space
			System.out.println("*********************** NO FREE SPACE *****************");
			return -3;
		}
		return 0;
	}

	int best_fit_allocate(int set){  		// function for allocating memory for best fit
		int flag=0;
		int k=0;
		int pos = -1;
		int count=0;
		int min=100000;
		int l=0;
		for(int i=0;i<size;i++){
			count=0;
			if(heap[i][1]==0){
				l=i;
				while(i<size){
			
					if(heap[i][1]==0){
						count++;
						
					}else{
						break;
					}	
					i++;
				}
				if(count>=set+1 && count<min){
					min = count;
					pos = l;
				}
				i--;	
			}
		}
	
		if(pos!=-1){
			flag = 1;
		
			heap[pos][0] = set+1;
			heap[pos][1] = 1;
			pos++;
			int g = pos;
			while(set!=0){
				heap[pos][1] = 1;
				heap[pos][0] = -2;
				pos++;
				set--;
			}
			int j = pos;
			count=0;
			for(;j<size;j++){
				if(heap[j][1]==0){
					count++;
				}else{
					break;
				}
			}
			if(count>0){
				heap[pos][0] = count;
				pos++;
			}
			
			for(int u=size-1;u>=0;u--){
						
						if(heap[u][1]==0 && heap[u][0]!=0 && u+1<size){
							
							heap[u+1][0] = -1;
							break;
						}
					}
			return g;
		}
		if(flag==0){
			System.out.println("*********************** NO FREE SPACE *****************");
			return -3;
		}
		return 0;
	}

	int worst_fit_allocate(int set){
		int flag=0;							//function for allocating memory for worst fit
		int k=0;
		int pos = -1;
		int count=0;
		int max=-13;
		int l=0;
		for(int i=0;i<size;i++){
			count=0;
			if(heap[i][1]==0){
				l=i;
				while(i<size){
					
					if(heap[i][1]==0){
						count++;
						
					}else{
						break;
					}	
					i++;
				}
				if(count>=set+1 && count>max){
					max = count;
					pos = l;
				}
				i--;	
			}
		}
		
		if(pos!=-1){
			flag = 1;
		
			heap[pos][0] = set+1;
			heap[pos][1] = 1;
			pos++;
			int g = pos;
			while(set!=0){
				heap[pos][1] = 1;
				heap[pos][0] = -2;
				pos++;
				set--;
			}
			int j = pos;
			count=0;
			for(;j<size;j++){
				if(heap[j][1]==0){
					count++;
				}else{
					break;
				}
			}
			if(count>0){
				heap[pos][0] = count;
				pos++;
			}
			for(int u=size-1;u>=0;u--){
					
					if(heap[u][1]==0 && heap[u][0]!=0 && u+1<size){
						
						heap[u+1][0] = -1;
						break;
						}
					}
			return g;
		}
		if(flag==0){
			System.out.println("*********************** NO FREE SPACE *****************");
			return -3;
		}
		return 0;
	}

	

	void deallocate(int set){
		int temp = set;				//function for deallocation of memory it will be same for first fit,best fit and worst fit
		set--;
		heap[set][1] = 0;
		int k = heap[set][0];
		k--;
		while(k!=0){
			heap[temp][0] = 0;
			heap[temp][1] = 0;
			temp++;
			k--;
		}


	}

	int get_freeStart(){
		for(int i=0;i<size;i++){		// for getting freeStart position i.e. posotion of first free block in heap
			if(heap[i][1]==0){
				return i;
			}
		}
		return -1;
	}
	
	void print(){					// function for printing heap
		System.out.println("----------------------------");
		for(int i=size-1;i>=0;i--){
			System.out.print(heap[i][0]);
			System.out.println("   " + heap[i][1]);
		}
		System.out.println();
		if(get_freeStart()>=0){
			System.out.println("freeStart : " + get_freeStart());	
		}else{
			System.out.println("freeStart : -1 ");
		}
		
		System.out.println("----------------------------");
	}

	void defragmentation(){
		int count=0;					// function for defragmentation that is all unallocated 
										//blocks will combine togather above the all allocated blocks
		int r[] = new int[100];
		int j=0;
		for(int i=0;i<size;i++){
			if(heap[i][1]== 1){
				r[j] = heap[i][0];
				j++;
				i = i+heap[i][0] -1;
			}
			
			count++;
		}
		j=0;
		int sum=0;
		for(int i=0;i<100;i++){
			sum += r[i];
		}
		for(int i=0;i<size;i++){
			if(r[j]!=0){
				heap[i][0] = r[j];
				heap[i][1] = 1;
				i++;
				while(r[j]!=1){
					heap[i][0] = -2;
					heap[i][1] = 1;
					i++;
					r[j]--;
				}
				j++;
				i--;	
			}else{
				heap[i][0] = 0;
				heap[i][1] = 0;
			}

		}


		heap[sum][0] = size - sum;
		heap[sum+1][0] = -1; 


	}



}
class question_2{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("enter the size of the heap : ");
		int size = in.nextInt();
		System.out.println();
		System.out.println("enter 1 for first fit");
		System.out.println("enter 2 for best fit");
		System.out.println("enter 3 for worst fit");
		//System.out.println("enter 4 for defragmentation");		
		int freeStart = 0;			// freeStart is position of first free block.
		int method = in.nextInt();
		if(method==1){
			int records [] = new int[100]; // this records is for storing the start position of allocated block 
			int var=1;
			System.out.println("initials heap : ");
			memory first_fit = new memory(size);
			first_fit.print();
			int s=1;
			int block_size=0;		
			while(s!=4){
				System.out.println("enter 1 for allocate");
				System.out.println("enter 2 for deallocate");
				System.out.println("enter 3 for defragmentation");
				System.out.println("enter 4 for exit");		
				s = in.nextInt();
				if(s==1){
					System.out.print("enter the block size : ");
					block_size = in.nextInt();
					records[var] = first_fit.first_fit_allocate(block_size);
					var++;
					System.out.println("heap after allocatation");
					first_fit.print();
				}else if(s==2){
					int j;
					System.out.print("enter block number which u want to deallocate : ");
					j = in.nextInt();
					first_fit.deallocate(records[j]);
					System.out.println("heap after deallocation ");
					first_fit.print();
				}else if(s==3){
					first_fit.defragmentation();
					first_fit.print();	
				}else if(s==4){
					break;
				}
			}
			
		}

		if(method==2){
			int records [] = new int[100];
			int var=1;
			System.out.println("initials heap : ");
			memory best_fit = new memory(size);
			best_fit.print();
			int s=1;
			int block_size=0;		
			while(s!=4){
				System.out.println("enter 1 for allocate");
				System.out.println("enter 2 for deallocate");
				System.out.println("enter 3 for defragmentation");
				System.out.println("enter 4 for exit");		
				s = in.nextInt();
				if(s==1){
					System.out.print("enter the block size : ");
					block_size = in.nextInt();
					records[var] = best_fit.best_fit_allocate(block_size);
					var++;
					System.out.println("heap after allocatation");
					best_fit.print();
				}else if(s==2){
					int j;
					System.out.print("enter block number which u want to deallocate : ");
					j = in.nextInt();
					best_fit.deallocate(records[j]);
					System.out.println("heap after deallocation ");
					best_fit.print();
				}else if(s==3){
					best_fit.defragmentation();
					best_fit.print();
					
				}else if(s==4)	{
					break;
				}	
			}
		}
		
		if(method==3){
			int records [] = new int[100];
			int var=1;
			System.out.println("initials heap : ");
			memory worst_fit = new memory(size);
			worst_fit.print();
			int s=1;
			int block_size=0;		
			while(s!=4){
				System.out.println("enter 1 for allocate");
				System.out.println("enter 2 for deallocate");
				System.out.println("enter 3 for defragmentation");
				System.out.println("enter 4 for exit");		
				s = in.nextInt();
				if(s==1){
					System.out.print("enter the block size : ");
					block_size = in.nextInt();
					records[var] = worst_fit.worst_fit_allocate(block_size);
					var++;
					System.out.println("heap after allocatation");
					worst_fit.print();
				}else if(s==2){
					int j;
					System.out.print("enter block number which u want to deallocate : ");
					j = in.nextInt();
					worst_fit.deallocate(records[j]);
					System.out.println("heap after deallocation ");
					worst_fit.print();
				}else if(s==3){
					worst_fit.defragmentation();
					worst_fit.print();
					
				}else if(s==4)	{
					break;
				}	
			}
		}
			
		
	}
}
