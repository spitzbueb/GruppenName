package ch.zhaw.gruppenname.test;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.gruppenname.application.Receipe;

public class ReceipeTest {
	private Receipe receipe;
	
	@Before
	public void setUp() throws Exception {
		receipe = new Receipe("Test");
	}
	
	public void addTest(){
		receipe.add("Test","test","test",3,"test");
	}
	@Test
	public void testGetName(){
		assert(receipe.getName().equals("Test"));
	}
	@Test
	public void testGetId(){
		assert(receipe.getID() != 0);
	}
	@Test
	public void testRemoveRecipe(){
		assert(receipe.remove());
	}
	@Test
	public void testGetReceipes(){
		assert(!Receipe.getReceipes(8).isEmpty());
	}
}
