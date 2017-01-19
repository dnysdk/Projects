
import java.util.Comparator;


public class IdSort implements Comparator<Customer>
{


	public int compare(Customer c1,Customer c2) {
		// TODO Auto-generated method stub
		if(c1.customer_id==c2.customer_id)  
			return 0;  
			else if(c1.customer_id>c2.customer_id)  
			return 1;  
			else  
			return -1;  
		
	}
	

}
