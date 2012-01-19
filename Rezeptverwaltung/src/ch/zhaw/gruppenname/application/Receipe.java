/**
 * Klasse Rezept, 
 * 
 * @author GruppenName
 * @version 18.01.12
 */
package ch.zhaw.gruppenname.application;


import java.sql.SQLException;
import java.util.ArrayList;

import ch.zhaw.gruppenname.database.Database;
import ch.zhaw.gruppenname.interfaces.Mutation;

public class Receipe implements Mutation{
	private String name;
	private Database database = new Database();

	
	public Receipe(String name){
		this.name = name;

	}
	/**
	 * Fügt der Datenbank, das entsprechenende Rezept mit den entsprechenenden Zutaten hinzu
	 * @param ingredients
	 * @param description
	 * @param author
	 * @param numberOfVotes
	 * @param manual
	 */
	public void add(String ingredients, String description, String author, int numberOfVotes, String manual) {
		try {
			database.addReceipt(name, description, author, numberOfVotes, manual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		database.matchReceipt_Ingredients(name, ingredients);
	}
	/**
	 * Löscht entsprechendes Rezept aus Datenbank
	 * @return boolean
	 */
	@Override
	public boolean remove() {
		return database.removeReceipt(name);
	}
	
	/**
	 * Gibt Name des Rezepts züruck
	 * @return String
	 */
	@Override
	public String getName(){
		return name;
	}
	/**
	 * Gibt ID zurück
	 * @return int
	 */
	@Override
	public int getID(){
		return database.getReceiptId(name);
	}
	/**
	 * Gibt Rezepte mit gleicher Zutat zurück
	 * @return ArrayList<String>
	 * 
	 */
	public static ArrayList<String> getReceipes(int id){
		return new Database().getReceipes(id);
	}
	/**
	 * Gibt alle Rezepte züruck
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getAllNames(){
		return new Database().getAllTitles();
	}
}
