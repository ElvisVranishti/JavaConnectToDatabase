import java.sql.*;
import java.util.Scanner;

public class bankingDatabase {

public static void main(String[]args){
	
	Connection conn = null;
	
	try {
    	conn =
       DriverManager.getConnection("jdbc:mysql://website:port/dbname?serverTimezone=UTC&" + "user=dbname&password=password");
    String SQL = "SELECT * FROM Transactions";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(SQL);
    
    System.out.println("Please pick from the following menu:");
    System.out.println("");
    System.out.println("To view table, please enter 1.");
    System.out.println("To insert a record, please enter 2.");
    
    Scanner kbd = new Scanner(System.in);
    int input = kbd.nextInt();
    kbd.nextLine();
    if(input == 1) {
      System.out.printf("%-20s%-20s%-20s%-10s\n", "accountNumber", "date", "type", "amount");
      while(rs.next()) {
      System.out.printf("%-20s%-20s%-20s%-10s\n", rs.getString("accountNumber"), rs.getString("date"), rs.getString("type"), rs.getDouble("amount"));
      }
    }
    if(input == 2) {
      System.out.print("Please enter the account number: ");
      String newID= kbd.nextLine();

      System.out.print("Please enter the date in (xx/xx/xxxx) format: ");
      String newDate = kbd.nextLine();
      
      System.out.print("Please enter the transaction type (WITHDRAW OR DEPOSIT): ");
      String newType = kbd.nextLine();

      System.out.print("Please enter the amount (as a double): ");
      double newAmount = kbd.nextDouble();
      
      String sql = "INSERT INTO Transactions VALUES('" + newID + "','" + newDate + "','" + newType + "','" + newAmount + "')";
      stmt.executeUpdate(sql);
      
    }
      
	} catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
	}
}
}
