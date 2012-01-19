package ch.zhaw.gruppenname.test;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.gruppenname.application.Ingredient;
import ch.zhaw.gruppenname.application.Receipe;

public class IngredientTest {
	private Ingredient ingredient;
	
	@Before
	public void setUp() throws Exception {
		ingredient = new Ingredient("Test");
	}
	
	public void addTest(){
		ingredient.add();
	}
	@Test
	public void testGetName(){
		assert(ingredient.getName().equals("Test"));
	}
	@Test
	public void testGetId(){
		assert(ingredient.getID() != 0);
	}
	@Test
	public void testRemoveRecipe(){
		assert(ingredient.remove());
	}
	@Test
	public void testGetReceipes(){
		assert(!Receipe.getReceipes(8).isEmpty());
	}
}