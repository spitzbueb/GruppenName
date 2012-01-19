package ch.zhaw.gruppenname.application;

import java.util.ArrayList;

import ch.zhaw.gruppenname.database.Database;
import ch.zhaw.gruppenname.interfaces.ISearchStrategy;

public class RecipeStrategy implements ISearchStrategy {

	@Override
	public String getRecipe(String name) {
		// TODO Auto-generated method stub
		
		ArrayList<String> list = new ArrayList<String>();
		boolean found = false;
		
		list = Receipe.getAllNames();
		
		for (String string:list){
			while (name.isEmpty() && !found){
				if (string.equals(name)){
					name = string;
					found = true;
				}
				else{
					name = "not found";
				}
			}
		}
		return name;
	}
}
