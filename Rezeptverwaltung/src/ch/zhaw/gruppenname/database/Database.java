package ch.zhaw.gruppenname.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
public class Database {
	
	private String username,password,url;
	private java.sql.Connection connect = null;
	
	public Database()
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
	}
	
	public void matchReceipt_Ingredients(String receipt, String ingredients)
	{
		ResultSet receiptidQuery = null;
		int receiptid = 0,ingredientid = 0;
		String[] oneIngredient;
		java.sql.Statement select = null;
		try {
			select = connect.createStatement();
			receiptidQuery = select.executeQuery("SELECT ID from Rezept WHERE Name = '" + receipt + "'");
			receiptidQuery.beforeFirst();
			receiptid = receiptidQuery.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(receiptidQuery);
		
		
		oneIngredient = ingredients.split(",");
		
		for (String string:oneIngredient)
		{
			try {
				ingredientid = select.executeQuery("SELECT ID from Zutat WHERE Name = '" + string + "'").getInt(1);
				select.executeUpdate("INSERT INTO Zutat_Rezept(RezeptIDFS,ZutatIDFS) VALUES('" + receiptid + "'," + ingredientid + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void addReceipt(String title, String description, String author, int numberOfVotes, String manual) throws SQLException{
		try {
			java.sql.Statement s = connect.createStatement();
			s.executeUpdate("INSERT INTO Rezept(Name,Beschreibung,Author,Bewertung,Vorgehen)" + 
				" VALUES" + "('" + title + "','" + description + "','" + author + "','" + numberOfVotes + "','" + manual + "')");
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void addIngredients(String ingredients)
	{
		String[] zutaten = ingredients.split(",");
		
		java.sql.Statement s=null;
		try {
			s = connect.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(String string:zutaten)
		{
			try {
				s.executeUpdate("INSERT INTO Zutat(Name) VALUES('" + string + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Database();
	}
	
}
