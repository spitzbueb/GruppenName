package ch.zhaw.gruppenname.application;

import java.util.ArrayList;

import ch.zhaw.gruppenname.interfaces.ISearchStrategy;

public class IngredientStrategy implements ISearchStrategy {
	private ArrayList<String> receipes = new ArrayList<String>();
	/**
	 * Es werden alle Rezepte mit der entsprechenden Zutat gesucht
	 * Falls es Rezepte mit der Zutat gibt wird eines nach Zufallsprinzip ausgegeben
	 * ansonsten null
	 * @param name
	 * @return String
	 */
	@Override
	public String getRecipe(String name) {
		ArrayList<String> list = new ArrayList<String>();
		list = Ingredient.getAllNames();
		if(list.contains(name)){
			int id = new Ingredient(name).getID();
			receipes = Receipe.getReceipes(id);
			int number = (int) (Math.random() * receipes.size());
			return receipes.get(number);
		}
		return null;
	}
}
