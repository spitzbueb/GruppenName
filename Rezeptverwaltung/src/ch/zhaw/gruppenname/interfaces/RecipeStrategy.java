package ch.zhaw.gruppenname.interfaces;

import java.util.ArrayList;

import ch.zhaw.gruppenname.database.Database;

public class RecipeStrategy implements ISearchStrategy {

	@Override
	public String getRecipe(String name) {
		// TODO Auto-generated method stub
		Database temp = new Database();
		ArrayList<String> list = new ArrayList<String>();
		boolean found = false;
		
		list = temp.getAllTitles();
		
		for (String string:list)
		{
			if (string.equals(name) && found==false)
			{
				name = string;
				found = true;
			}
			else
			{
				name = "not found";
			}
		}
		
		return name;
	}

}
