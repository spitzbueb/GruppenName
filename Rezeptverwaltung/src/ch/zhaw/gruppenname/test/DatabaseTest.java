package ch.zhaw.gruppenname.test;
import static org.junit.Assert.*;

import java.sql.SQLException;

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
	public void testAddReceipt() throws SQLException{
		database.addReceipt("Spaghetti Bolognese", "Spaghetti mit feiner Tomatensauche", "GruppenName", 5, "Ist nur ein Test");
		assertNotNull(database.getReceiptId("Spaghetti Bolognese"));
	}
	
	@Test
	public void testMatchZutatRezept() throws SQLException {
		database.matchReceipt_Ingredients("Spaghetti Bolognese", "Spaghetti,Fleisch,Tomate");
		assertNotNull(database.getIngredientsId("Spaghetti"));
	}
	
	@Test
	public void testDeleteReceipt() throws SQLException {
		database.removeReceipt("Spaghetti Bolognese");
		assertTrue(database.getReceiptId("Spaghetti Bolognese") == 0);
	}
	
	@Test
	public void testDeleteIngredients() throws SQLException {
		database.removeIngredients("Spaghetti");
		assertTrue(database.getReceiptId("Spaghetti") == 0);
	}
	@Test
	public void testGetIngredients(){
		assertNotNull(database.getIngredients(17));
	}
	
}
