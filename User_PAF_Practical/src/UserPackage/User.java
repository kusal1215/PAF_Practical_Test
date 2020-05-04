package UserPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * @author kusal
 *
 */
public class User {
	
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/User", "root", "root");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String readUsers()
	{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{
							return "Error while connecting to the database for reading.";
					}
					
					// Prepare the html table to be displayed
					output = "<table class=\'table table-striped table-dark\' border='1'>"
							+ "<tr>"
							+ "<th>NIC</th><th>Name</th><th>Gender</th>"
							+ "<th>Contact Number</th><th>Email</th><th>Password</th>"
							+ "<th>Update</th><th>Remove</th>"
							+ "</tr>";
					
					String query = "select * from user"; // table name doctor
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
							String UserNic = rs.getString("UserNic");
							String UserName = rs.getString("UserName");
							String UserGender = rs.getString("UserGender");
							String UserContact = rs.getString("UserContact");
							String UserEmail = rs.getString("UserEmail");
							String UserPassword = rs.getString("UserPassword");
													
									
							// Add into the html table
							output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden' value='" + UserNic  + "'>" + UserNic + "</td>";
							output += "<td>" + UserName + "</td>";
							output += "<td>" + UserGender + "</td>";
							output += "<td>" + UserContact + "</td>";
							output += "<td>" + UserEmail + "</td>";							
							output += "<td>" + UserPassword + "</td>";
							
							// buttons
							output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-success'></td>"
									+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='" + UserNic + "'></td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
			}
			return output;
	}
	
    public String insertusers(String UserNic, String UserName,String UserGender, String UserContact, String UserEmail,String UserPassword ) {
		
		String output = "";
		
		try {
				Connection con = connect();

				if (con == null) {
					
					return "Error while connecting to the database";
					
				}

			// create a prepared statement
			String query = " insert into user " + "(`UserNic`,`UserName`,`UserGender`,`UserContact`,`UserEmail`,`UserPassword`)"
					+ " values (?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, UserNic);
			preparedStmt.setString(2, UserName);
			preparedStmt.setString(3, UserGender);
			preparedStmt.setString(4, UserContact);
			preparedStmt.setString(5, UserEmail);
			preparedStmt.setString(6, UserPassword);
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newuser = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newuser + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the User Deatils.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

    
	public String Updateusers(String UserNic, String UserContact, String UserEmail,String UserPassword ) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "update user set UserContact = ?,UserEmail = ?,UserPassword = ? where UserNic = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, UserContact);
			preparedStmt.setString(2, UserEmail);
			preparedStmt.setString(3, UserPassword);
			
			
			preparedStmt.setString(4, UserNic);
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newuser = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newuser + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the User Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String deleteuser(String UserNic) {
		
		String output = "";

		try {

				Connection con = connect();
	
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
	
				// create a prepared statement
				String query = "delete from user where UserNic=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, UserNic);
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newuser = readUsers();
				output = "{\"status\":\"success\", \"data\": \"" + newuser + "\"}";
		}		
		catch (Exception e)
		{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting a User.\"}";
				System.err.println(e.getMessage());
		}
		
		return output;
	}


}
