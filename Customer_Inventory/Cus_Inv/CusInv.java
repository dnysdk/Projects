import java.util.*;

import javax.swing.JOptionPane;

public class CusInv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CustomerDAO cd = new CustomerDAO();
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		int input;
		System.out.println("\n\n++++++++++++++++Welcome to Customer Inventory++++++++++++\n\n choose from following options");	
		
		while(1>0){
			try{
				
				System.out.println("\n\n\t\t1. List all Customers\n\t\t2. List customer based on id\n\t\t3. Add Customer\n\t\t4. Update Customer\n\t\t5. Add Multiple Customers" +
					"\n\t\t6. Remove Customer\n\t\t7. Exit");
			//input = in.nextInt();
		     try {
		          String s = in1.nextLine();
		          input = Integer.parseInt(s);
		         
		      }
		      catch (Exception e)
		      {
		         System.out.println("Give valid input");
		    	  continue;
		      }
			if(input>7 && input<1){
				System.out.println("Give valid numerical input( 1 to 7)");
				continue;
			}
			switch(input){
			case 1:
				
				boolean b= cd.isDBEmpty();
				if(b)
				{
					System.out.println("Sorry! List is empty");
				    break;
				}

				List<Customer> list = cd.getAllCustomers();
				Collections.sort(list,new IdSort());
				System.out.println("\n\n");
				System.out.println("List of all customers sorted by Customer ID is as below : ");
				for(Customer c:list){
					System.out.println(c);
				}
				Collections.sort(list,new NameSort());
				System.out.println();
				System.out.println("List of all customers sorted by Customer name is as below : ");
				for(Customer c:list){
					
					System.out.println(c);
				}
				Collections.sort(list,new BalanceSort());
				System.out.println();
				System.out.println("List of all customers sorted by Customer balance(Descending) is as below : ");
				for(Customer c:list ){
					System.out.println(c);
				}
				break;
			case 2:
				
				boolean b2= cd.isDBEmpty();
				if(b2)
				{
					System.out.println("Sorry! List is empty. Can't perform this operation!!");
				    break;
				}
				System.out.print("Enter customer id for searching record: ");
				int customerId = in.nextInt();
				Customer c1 = cd.findCustomer(customerId);
				if(c1==null)
				{
					System.out.println("Customer Id is not present in inventory. Try again!!");
				}
				else
					System.out.println(c1);
				break;
			case 3:
				
				customerInput ci=new customerInput();
				Customer c = new Customer(ci.getId(),ci.getName(),ci.getDob(),ci.getAmount());
				boolean status = cd.addCustomer(c);
				String message;
				if(status){
					message = "Entry has been logged successfully into database: Details as below \nname : "+ci.getName()+"\nid : "+ci.getId()+"\ndate of birth : "+ci.getDob()+"\nbalance: "+ci.getAmount();
				}else{
					message = "Try again";
				}
				JOptionPane.showMessageDialog(null,message);
				break;
				
			case 4:
				boolean b4= cd.isDBEmpty();
				if(b4)
				{
					System.out.println("Sorry! List is empty. Can't perform this operation!!");
				    break;
				}
				customerInput ci1=new customerInput();
				Customer nc = new Customer(ci1.getId(),ci1.getName(),ci1.getDob(),ci1.getAmount());
				boolean result = cd.updateCustomer(ci1.getId(),nc);
				if(result){
					System.out.println("Success");
				}else{
					System.out.println("Try Again!");
				}
				break;
			case 5:
				System.out.println("Enter the number of records you want to insert");
				int num_of_records = in.nextInt();
				//in.nextLine();
				TreeSet<Customer> ts = new TreeSet<Customer>();
				for(int i=1;i<=num_of_records;i++){
					customerInput ci2=new customerInput();
					Customer new_customer = new Customer(ci2.getId(),ci2.getName(),ci2.getDob(),ci2.getAmount());
					ts.add(new_customer);
				
				}
				int batches = cd.addMultipleCustomers(ts);
				System.out.println("batch opeartion on "+batches +" records is sucessful!");
				break;
			case 6:
				
				boolean b6= cd.isDBEmpty();
				if(b6)
				{
					System.out.println("Sorry! List is empty. Can't perform this operation!!");
				    break;
				}
				System.out.print("Enter Customer id to delete record : ");
				int deleteId = in.nextInt();
				boolean deleteStatus = cd.removeCustomer(deleteId);
				if(!deleteStatus)
					System.out.println("Now List again");
				break;
			case 7:
				cd.close();
				in.close();
				in1.close();
				System.exit(0);
				
			}
			
			}catch(Exception e){
				System.out.println("Give valid input");
				//continue;
			}
			
		}
		
		
		
	}

}
