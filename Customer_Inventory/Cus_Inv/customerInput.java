import javax.swing.JOptionPane;


public class customerInput {

	int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Customer id"));
	String name = JOptionPane.showInputDialog(null, "Enter Customer name");
	String dob = JOptionPane.showInputDialog(null, "Enter Customer's date of birth");
	double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Customer balance"));
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDob() {
		return dob;
	}
	public double getAmount() {
		return amount;
	}
	


}
