import java.sql.*;
public class DbConnection {
	static final String address = "jdbc:mysql://localhost:3306/Customer_Inventory";
	static final String user = "root";
	static final String password = "crt@123";
	static final String driver_name = "com.mysql.jdbc.Driver";
	
	
	public Connection setConnection(){
		
		
		
		Connection con;
		try {
			

			Class.forName(driver_name);
			con = DriverManager.getConnection(address,user,password);
			return con;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	
	public void closeConnection(Connection con){
		
		
		
		try {
			
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
}
