public class Customer implements Comparable<Customer>{
	int customer_id;
	String customer_name;
	String dob;
	double balance;

	Customer(int customer_id,String customer_name,String dob,double balance){
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.dob = dob;
		this.balance = balance;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", dob=" + dob + ", balance=" + balance + "]";
	}

	public int compareTo(Customer c) {
		
		if(customer_id==c.customer_id)  
			return 0;  
			else if(customer_id>c.customer_id)  
			return 1;  
			else  
			return -1;  
		
		
	}	

}



