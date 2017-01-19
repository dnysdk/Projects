import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class CustomerDAO {

	DbConnection dc = new DbConnection();
	Connection con = dc.setConnection();
	
	
	public boolean addCustomer(Customer c){
		int id = c.getCustomer_id();
		String name = c.getCustomer_name();
		String date = c.getDob();
		double amount = c.getBalance();
		int flag=0;
		Statement stmt;
		try {
			stmt = con.createStatement();
			
			String insertQuery = "insert into Customer values("+id+",'"+name+"',"+"STR_TO_DATE('"+date+"', '%d-%m-%Y')" +","+ amount+")";
			//System.out.println(insertQuery);
			int nor  = stmt.executeUpdate(insertQuery);
			if(nor>0){
				flag=1;
			}else{
				flag=0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Please enter valid information.");
			//e.printStackTrace();
		}
		
		
		
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public Customer findCustomer(int customerId){
		Statement stmt;
		Customer c = null;
		try {
			stmt = con.createStatement();
			
			String searchQuery = "select * from Customer where Customer_id="+customerId;
			ResultSet rs  = stmt.executeQuery(searchQuery);
			while(rs.next()){
				String dateString = null;
				 SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
				 try{
						dateString = sdfr.format( rs.getDate(3) );
					   }catch (Exception ex ){
						System.out.println(ex);
					   }
				c = new Customer(rs.getInt(1),rs.getString(2),dateString,rs.getDouble(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Please enter valid information.");
			//e.printStackTrace();
		}
		
		return c;
	}
	
	public boolean updateCustomer(int customerId, Customer C){
		Statement stmt;
		Customer c = null;
		try {
			stmt = con.createStatement();
			
			String searchQuery = "select * from Customer where Customer_id="+customerId;
			ResultSet rs  = stmt.executeQuery(searchQuery);
			while(rs.next()){
				String dateString = null;
				 SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
				 try{
						dateString = sdfr.format( rs.getDate(3) );
					   }catch (Exception ex ){
						System.out.println(ex);
					   }
				
				c = new Customer(rs.getInt(1),rs.getString(2),dateString,rs.getDouble(4));
				System.out.println("Records before update operation\n"+ c);
				
		
				String name = C.getCustomer_name();
				String date = C.getDob();
				double amount = C.getBalance();
				int flag=0;
				Statement stmt1;
				try {
					stmt1 = con.createStatement();
					
					String updateQuery = "update Customer set Customer_name='"+name+"',Date_of_birth=STR_TO_DATE('"+date+"', '%d-%m-%Y')" +",Balance="+ amount+" where Customer_id="+customerId;
					//System.out.println(insertQuery);
					int nor  = stmt1.executeUpdate(updateQuery);
					if(nor>0){
						flag=1;
					}else{
						flag=0;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Please enter correct details.");
					//e.printStackTrace();
				}
				
				if(flag==1){
					System.out.println("Records after update operation \n"+C);
					return true;
				}else{
					return false;
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Please enter correct details.");
		}
		
		return true;
	}
	
	
	public List<Customer> getAllCustomers(){
		List<Customer> l1 = new ArrayList<Customer>();
		
		Statement stmt;
		Customer c = null;
		try {
			stmt = con.createStatement();
			
			String listQuery = "select * from Customer";
			ResultSet rs  = stmt.executeQuery(listQuery);
			while(rs.next()){
				String dateString = null;
				 SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
				 try{
						dateString = sdfr.format( rs.getDate(3) );
					   }catch (Exception ex ){
						System.out.println(ex);
					   }
				c = new Customer(rs.getInt(1),rs.getString(2),dateString,rs.getDouble(4));
				l1.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading database. Try agian!!");
			//e.printStackTrace();
		}
		
		
		
		
		return l1;
		
	}
	
	public void close(){
		dc.closeConnection(con);
	}

	public boolean removeCustomer(int deleteId) {
		// TODO Auto-generated method stub
		CallableStatement stmt = null;
		boolean flag = true;
		Customer ck= findCustomer(deleteId);
		if(ck==null){
			System.out.println("Cutomer ID not present in inventory.");
			return false;
		}
			
		else
		{
			String query = "{call deleteCustomer(?)}";
			try {
				stmt = con.prepareCall(query);
				stmt.setInt(1, deleteId);
				flag=stmt.execute();
				//System.out.println(flag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in database operation. Try agian!!");
				//e.printStackTrace();
			}
			
			return flag;
			
		}
		
	}
public boolean isDBEmpty()
{
	try {
		
		Statement stmt = con.createStatement();
		String isEmptyQuery = "Select count(*) from Customer";
		ResultSet rs= stmt.executeQuery(isEmptyQuery);
		if(rs.next())
		{
			int count=rs.getInt(1);
			if (count==0)
			return true;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Error in database operation. Try agian!!");
		//e.printStackTrace();
	}
	
	return false;
	
}

	public int addMultipleCustomers(TreeSet<Customer> list) {
		// TODO Auto-generated method stub
		int length = 0;
		try {
			Statement stmt = con.createStatement();
			//con.setAutoCommit(false);
			for(Customer c:list){
				int id = c.getCustomer_id();
				String name = c.getCustomer_name();
				String date = c.getDob();
				double amount = c.getBalance();
				String insertQuery = "insert into Customer values("+id+",'"+name+"',"+"STR_TO_DATE('"+date+"', '%d-%m-%Y')" +","+ amount+")";
				//System.out.println(insertQuery);
				stmt.addBatch(insertQuery);	
				//System.out.println(insertQuery);
				}
			int[] count = stmt.executeBatch();
			//con.commit();
			length = count.length;
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("Error in database operation. Try agian!!");
				
			//e.printStackTrace();
			}
			
				
		
		return length;
	}
	
	
}
