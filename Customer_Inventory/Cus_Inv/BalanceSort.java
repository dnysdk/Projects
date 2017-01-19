
import java.util.Comparator;

public class BalanceSort implements Comparator<Customer> {
	public int compare(Customer c1,Customer c2) {
		// TODO Auto-generated method stub
		if(c1.balance==c2.balance)  
			return 0;  
			else if(c1.balance<c2.balance)  
			return 1;  
			else  
			return -1;  
		
	}
}
