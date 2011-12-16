package ch.zhaw.gruppenname.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private String username,password,url;
	private java.sql.Connection connect = null;
	private java.sql.Statement statement;
	
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
			receiptIdQuery = statement.executeQuery("SELECT ID from Rezept WHERE Name = '" + receipt + "'");
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
		java.sql.Statement select;
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
		connect();				
		oneIngredient = ingredients.split(",");
		
		for (String string:oneIngredient)
		{
			try {
				statement.executeUpdate("INSERT INTO Zutaten_Rezept(RezeptIDFS,ZutatIDFS) VALUES('" + getReceiptId(receipt) + "','" + getIngredientsId(string) + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}
	
	public void addReceipt(String title, String description, String author, int numberOfVotes, String manual) throws SQLException{
		connect();
		try {
			statement.executeUpdate("INSERT INTO Rezept(Name,Beschreibung,Author,Bewertung,Vorgehen)" + 
				" VALUES" + "('" + title + "','" + description + "','" + author + "','" + numberOfVotes + "','" + manual + "')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}
	
	public void removeReceipt(String receipt){
		connect();
		try {
			statement.executeUpdate("DELETE FROM Zutaten_Rezept where RezeptIDFS='" + getReceiptId(receipt) +"'");
			statement.executeUpdate("DELETE FROM Rezept where Name='" + receipt + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}
	
	public void addIngredients(String ingredients)
	{
		String[] zutaten = ingredients.split(",");
		connect();
		for(String string:zutaten)
		{
			try {
				statement.executeUpdate("INSERT INTO Zutat(Name) VALUES('" + string + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}
	private void connect(){
			try {
				statement = connect.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void close(){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		new Database();
	}
	
}