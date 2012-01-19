package ch.zhaw.gruppenname.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.gruppenname.application.IngredientStrategy;
import ch.zhaw.gruppenname.interfaces.ISearchStrategy;

public class IngredientStrategyTest {
private ISearchStrategy search;
	
	@Before
	public void setUp() throws Exception {
		search = new IngredientStrategy();
	}
	
	@Test
	public void testGetRecipe(){
		assertNotNull(search.getRecipe("Champignon"));
	}
	
	@Test
	public void testDontGetRecipe(){
		assertNull(search.getRecipe("Bla"));
	}
}
