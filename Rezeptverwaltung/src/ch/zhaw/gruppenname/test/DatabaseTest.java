package ch.zhaw.gruppenname.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.gruppenname.database.Database;

public class DatabaseTest {
	private Database database;
	
	@Before
	public void setUp() throws Exception {
		database = new Database();
	}
	
	@Test
	public void testGetIngredients(){
		assertNotNull(database.getIngredients(17));
	}
	
}
