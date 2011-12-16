package ch.zhaw.gruppenname.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private int getReceiptId(String receipt){
		ResultSet receiptIdQuery;
		int receiptid = 0;
		java.sql.Statement select;
		try {
			select = connect.createStatement();
			receiptIdQuery = select.executeQuery("SELECT ID from Rezept WHERE Name = '" + receipt + "'");
			receiptIdQuery.next();
			receiptid = receiptIdQuery.getInt("ID");
			select.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiptid;
	}
	
	private int getIngredientsId(String ingredient){
		int ingredientId = 0;
		ResultSet ingredientidQuery;
		java.sql.Statement select = null;
		try {
			select = connect.createStatement();
			ingredientidQuery = select.executeQuery("SELECT ID from Zutat WHERE Name = '" + ingredient + "'");
			ingredientidQuery.next();
			ingredientId = ingredientidQuery.getInt("ID");
			select.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingredientId;
	}
	public void matchReceipt_Ingredients(String receipt, String ingredients)
	{
		String[] oneIngredient;
		java.sql.Statement select = null;
		try {
			select = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		oneIngredient = ingredients.split(",");
		
		for (String string:oneIngredient)
		{
			try {
				select.executeUpdate("INSERT INTO Zutaten_Rezept(RezeptIDFS,ZutatIDFS) VALUES('" + getReceiptId(receipt) + "','" + getIngredientsId(string) + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			select.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void removeReceipt(String receipt){
		java.sql.Statement s;
		try {
			s = connect.createStatement();
			removeIngredientsOfReceipt(getReceiptId(receipt));
			s.executeUpdate("DELETE FROM Rezept where Name='" + receipt + "'");
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeIngredientsOfReceipt(int id){
		java.sql.Statement s;
		try {
			s = connect.createStatement();
			s.executeUpdate("DELETE FROM Zutaten_Rezept where RezeptIDFS='" + id +"'");
			s.close();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private java.sql.Statement connect(java.sql.Statement s){
		try {
			s = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args) {
		new Database();
	}
	
}