package ch.zhaw.gruppenname.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
public class database {
	
	private String username,password,url;
	
	
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
			java.sql.Connection connect = null;
			connect = DriverManager.getConnection(url,username,password);
			java.sql.Statement statement = connect.createStatement();
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 System.out.println("Erfolg!!!");
	}
	
	public static void main(String[] args) {
		new database();
	}
	
}
