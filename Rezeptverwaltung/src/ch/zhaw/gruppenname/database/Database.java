/**
 * Klasse welche die kompletten Datenbankaktionen übernimmt
 * WICHTIG: Jede aufgebaute verbindung muss wieder geschlossen werden
 * 
 * @author pianogen
 * @version 23.12.2011
 */
package ch.zhaw.gruppenname.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	
	private String username,password,url;
	private java.sql.Connection connect = null;
	private java.sql.Statement statement;
//----------------------------------------------------------------------------------------------
	/**
	 * Konstruktor
	 */
	public Database(){
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
//----------------------------------------------------------------------------------
	/**
	 * Methode um komplette Infos über Rezept zu bekommen.
	 * 
	 * @param rezeptname
	 * @return ResultSet
	 */
	
	public HashMap getRecipeInfos(String rezeptname)
	{
		ResultSet query;
		HashMap<String,String> hm = new HashMap<String,String>();
		connect();
		try{
			query = statement.executeQuery("SELECT Beschreibung,Author,Bewertung,Vorgehen FROM Rezept WHERE Name = '" + rezeptname + "'");
			query.beforeFirst();
			query.next();
			hm.put("Beschreibung", query.getString("Beschreibung"));
			hm.put("Vorgehen", query.getString("Vorgehen"));
			hm.put("Bewertung", query.getString("Bewertung"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		close();
		return hm;
	}
//----------------------------------------------------------------------------------
	/**
	 * Methode um eine Liste der Zutaten zu einem Rezept zu bekommen
	 * 
	 * @param id
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getIngredients(int id)
	{
		connect();
		ArrayList<String> ingredients = new ArrayList<String>();
		
		try{
			ResultSet query = statement.executeQuery("Select Name from Zutat,Zutaten_Rezept WHERE Zutat.ID = Zutaten_Rezept.ZutatIDFS AND Zutaten_Rezept.RezeptIDFS = " + id);
			while(!query.isLast())
			{
				query.next();
				ingredients.add(query.getString("Name"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		close();
		return ingredients;
		
	}
//--------------------------------------------------------------------------------------------
	/**
	 * Methode um all Rezeptnamen aus der Datenbank zu bekommen
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getAllTitles(){
		connect();
		ResultSet allTitlesQuery;
		ArrayList<String> allTitles = new ArrayList<String>();	
		try {
			allTitlesQuery = statement.executeQuery("Select Name from Rezept");
			while (!allTitlesQuery.isLast()){
				allTitlesQuery.next();
				allTitles.add(allTitlesQuery.getString("Name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return allTitles;
	}
//-----------------------------------------------------------------------------------------------
	/**
	 * Methode um all Zutatsnnamen aus der Datenbank zu bekommen
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getAllIngredients(){
		connect();
		ResultSet allIngredientsQuery;
		ArrayList<String> allIngredients = new ArrayList<String>();	
		try {
			allIngredientsQuery = statement.executeQuery("Select Name from Zutat");
			while (!allIngredientsQuery.isLast()){
				allIngredientsQuery.next();
				allIngredients.add(allIngredientsQuery.getString("Name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return allIngredients;
	}
//-------------------------------------------------------------------------------------------
	/**
	 * Methode um die ID eines Rezeptes herauszufinden
	 * @param receipt
	 * @return int
	 */
	public int getReceiptId(String receipt){
		ResultSet receiptIdQuery;
		int receiptid = 0;
		java.sql.Statement select;
		try {
			select = connect.createStatement();
			receiptIdQuery = select.executeQuery("SELECT ID from Rezept WHERE Name = '" + receipt + "'");
			if (receiptIdQuery.next()){
				receiptid = receiptIdQuery.getInt("ID");
			}
			select.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiptid;
	}
//--------------------------------------------------------------------------------------------------------------------
	/**
	 * Methode um die ID einer Zutat zu erhalten
	 * Falls Zutat nicht existiert, wird Zutat automatisch erstellt
	 * @param ingredient
	 * @return int
	 */
	public int getIngredientsId(String ingredient){
		int ingredientId = 0;
		ResultSet ingredientidQuery;
		java.sql.Statement select;
		try {
			select = connect.createStatement();
			ingredientidQuery = select.executeQuery("SELECT ID from Zutat WHERE Name = '" + ingredient + "'");
			if (!ingredientidQuery.next()){
				select.executeUpdate("INSERT INTO Zutat(Name) VALUES('" + ingredient + "')");
				ingredientidQuery = select.executeQuery("SELECT ID from Zutat WHERE Name = '" + ingredient + "'");
				ingredientidQuery.next();
			}
			ingredientId = ingredientidQuery.getInt("ID");
			select.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingredientId;
	}
//---------------------------------------------------------------------------------------------
	/**
	 * Methode um in der Tabelle Zutaten_Rezept die IDFS zusammenzuführen.
	 * 
	 * @param receipt
	 * @param ingredients
	 */
	public void matchReceipt_Ingredients(String receipt, String ingredients){
		String[] oneIngredient;
		connect();				
		oneIngredient = ingredients.split(",");
		for (String string:oneIngredient){
			try {
				statement.executeUpdate("INSERT INTO Zutaten_Rezept(RezeptIDFS,ZutatIDFS) VALUES('" + getReceiptId(receipt) + "','" + getIngredientsId(string) + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}

//-----------------------------------------------------------------------------------------------
	/**
	 * Methode um ein Rezept in die Datenbank zu schreiben.
	 * Wird von AddRecipe.java aufgerufen
	 * 
	 * @param title
	 * @param description
	 * @param author
	 * @param numberOfVotes
	 * @param manual
	 * @throws SQLException
	 */
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
//-------------------------------------------------------------------------------------------------
	/**
	 * Methode um ein Rezept aus der Datenbank zu löschen
	 * 
	 * @param receipt
	 * @return boolean
	 */
	public boolean removeReceipt(String receipt){
		connect();
		try {
			statement.executeUpdate("DELETE FROM Zutaten_Rezept where RezeptIDFS='" + getReceiptId(receipt) +"'");
			java.sql.Statement stmt = connect.createStatement();
			stmt.executeUpdate("DELETE FROM Rezept where Name='" + receipt + "'");
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		close();
		return true;
	}
//--------------------------------------------------------------------------------------
	/**
	 * Methode um Zutaten in die Datenbank einzufügen.
	 * @param ingredients
	 */
	public void addIngredients(String ingredients){
		String[] zutaten = ingredients.split(",");
		connect();
		for(String string:zutaten){
			try {
				statement.executeUpdate("INSERT INTO Zutat(Name) VALUES('" + string + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}
//------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Methode um Zutat zu l�schen, falls die Zutat mit einem Rezept verlinkt ist, wird false zur�ckgegeben
	 * ansonsten true
	 * @param ingredient
	 * @return boolean
	 */
	public boolean removeIngredients(String ingredient){
		connect();
		ResultSet query;
		try {
			query = statement.executeQuery("Select * FROM Zutaten_Rezept where RezeptIDFS='" + getIngredientsId(ingredient) +"'");
			if (!query.next()){
				statement.executeUpdate("DELETE FROM Zutat where Name='" + ingredient + "'");
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		close();
		return true;
	}
//---------------------------------------------------------------------------------------------
	/**
	 * Methode welche die Verbindung zur Datenbank herstellt
	 */
	private void connect(){
		try {
			statement = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//---------------------------------------------------------------------------------------------
	/**
	 * Methode um die Verbindung zu trennen
	 */
	private void close(){
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}