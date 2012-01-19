package ch.zhaw.gruppenname.test;



import org.junit.Before;
import org.junit.Test;

import ch.zhaw.gruppenname.application.RecipeStrategy;
import ch.zhaw.gruppenname.interfaces.ISearchStrategy;


public class RecipeStrategyTest {
	private ISearchStrategy search;
	
	@Before
	public void setUp() throws Exception {
		search = new RecipeStrategy();
	}
	
	@Test
	public void testGetRecipe(){
		assert(!search.getRecipe("Pilzsuppe").equals("not found"));
	}
	@Test
	public void testDontGetRecipe(){
		assert(search.getRecipe("Bla").equals("not found"));
	}
}
