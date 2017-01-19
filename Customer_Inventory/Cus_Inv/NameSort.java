
import java.util.Comparator;

public class NameSort implements Comparator<Customer> {

	public int compare(Customer c1,Customer c2) {
		// TODO Auto-generated method stub
		return c1.customer_name.compareTo(c2.customer_name);
	
	}
}
