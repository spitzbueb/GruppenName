package ch.zhaw.gruppenname.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
public class database {
	
	private String username,password,url;
	private java.sql.Connection connect = null;
	
	public database()
	{
		 username = "web313";
		 password = "rt5adq";
		 url = "jdbc:mysql://login-74.hoststar.ch/usr_web313_3";
		 
		 try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Setup the connection with the DB
			connect = DriverManager.getConnection(url,username,password);
			java.sql.Statement statement = connect.createStatement();
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 System.out.println("Erfolg!!!");
	}
	public void addReceipt(String title, String description, String author, int numberOfVotes, String manual) throws SQLException{
		try {
			java.sql.Statement s = connect.createStatement();
			s.executeUpdate("INSERT INTO ?" + " VALUES" + "('" + title + "','" + description + "','" + author + "','" + numberOfVotes + "','" + manual + "')");
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new database();
	}
	
}
