/*

1.Dnyaneshwar D S (1401CS43)

*/


/*

we implemented stack using double link list

-2 for showing node is free
-1 for showing node is not free
any other values are in node data are positions

every node has status 0 if node is allocated and 1 if node is free

*/

import java.util.*;
class Node{  		// class for node in stack
	int status;
	int count;
	int data;
	Node next;			// pointer to next node
	Node prev;			// pointer to previous node
	Node(int status){
		this.status = status;
		this.data = -2;  		// -2 for showing node is free
		this.next = null;
		this.prev=null;
	}
}

class stack{
	int size = 0;
		
	Node current_top = null;
	Node top = null;		
 	int count = 0;
 	void create(){						// creating free node
 		Node newnode = new Node(1);
 		Node temp;
 		if(top==null){
 			//System.out.println("before");
 			top = newnode;
 			current_top = top;
 		}else{
 			//System.out.println("akd" + newnode.data);
 			temp = top;
 			newnode.next = temp;
 			temp.prev = newnode;
 			top = newnode;
 			current_top = top;
 			//System.out.println("after" + tail.data);
 		}
 		size++;
 		count++;
 	}

 	int get_top(){			// getting top data that is number of free nodes in stack
 		return count;
 	}
 	int push(int n){
 		if(count<n+1){ 		// if we want to allocate n nodes then we need n+1 nodes so checking availability 
 			System.out.println("***********No free space in stack*****************");
 			return -1;
 		}else{
 			int t = count;
 			Node temp=current_top;
 			count = count - n-1;
 			while(n!=0){
 				//temp = current_top;			// this function is for allocating memory
 				temp.status = 0;
 				temp.data = -1;
 				temp = temp.next;
 				n--;
 			}
 			temp.status = 0;
 			temp.data = t;
 			current_top = temp.next;
 		}
 		return count + 1;
 	}

 	int pop(){								// pop function free blocks in memory
 		Node node = current_top.prev;
 		if(node!=null && node.status==1){
 			System.out.println("------- Stack is already empty --------");
 			return count+1;
 		}else if(node==null){
 			System.out.println("------- Stack is already empty --------");
 			return count+1;
 		}
 		int t = node.data;
 		while(count!=t && node!=null){
 			node.status = 1;
 			node.data = -2;
 			node = node.prev;
 			count++;
 		}
 		if(node!=null)
 			current_top = node.next;
 		else
 			current_top = top;
 		return count+1;
 	}

 	void print(){
 									// printing stack
 		if(top==null){
 			System.out.println("no item in link list");
 		}else{
 			Node node = top;
 			while(node!= null){
 				System.out.print(node.data);
 				//System.out.print(node.status);
 			
 				if(node != null){
 					System.out.print(" --> ");
 				}
 				
 				node = node.next;
 		 	}

 		
 	}
 	System.out.println();
 	
}

}
class list{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		stack st = new stack();
		int top;
		System.out.print("enter the size of stack :- ");
		int total = in.nextInt();
		for(int i=0;i<total;i++){
			st.create();				// creating stack of given size
		}
		int k,x,y;
		while(1>0)
		{
			System.out.println("\nenter 1 for push\nenter 2 for pop\nenter 3 for exit");
			k=in.nextInt();
			if(k==1)
			{
				System.out.print("enter the number of blocks : ");
				System.out.println();
				x=in.nextInt();

				y=st.push(x);
				if(y!=-1)
					System.out.println("return address : "+ y);
				System.out.println();
				System.out.println("stack will be ");
				st.print();
			}
			if(k==2)
			{
				int l = st.pop();
				System.out.println("return address : "+ l);
				System.out.println();
				st.print();
		}
			if(k==3)
				break;
		}

	}
}
