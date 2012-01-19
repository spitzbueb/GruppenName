/**
 * Klasse Ingredient, 
 * 
 * @author GruppenName
 * @version 18.01.12
 */
package ch.zhaw.gruppenname.application;

import java.util.ArrayList;

import ch.zhaw.gruppenname.database.Database;
import ch.zhaw.gruppenname.interfaces.Mutation;

public class Ingredient implements Mutation{
	private String name;
	private Database database = new Database();
	
	public Ingredient(String name){
		this.name = name;
	}
	/**
	 * Fügt eine Zutat der Datenbank hinzu
	 */
	public void add() {
		database.addIngredients(name);	
	}
	/**
	 * Löscht Zutat aus Datenbank
	 * @return boolean
	 */
	@Override
	public boolean remove() {
		return database.removeIngredients(name);
	}

	/**
	 * Gibt Name der Zutat zurück
	 * @return String
	 */
	@Override
	public String getName() {
		return name;
	}
	/**
	 * Gibt alle Zutaten in Liste zurück
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getAllNames(){
		return new Database().getAllIngredients();
	}
	
}
