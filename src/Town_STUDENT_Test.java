import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Venus");
		town2 = new Town("Mercury");
		town3 = new Town("Earth");
		town4 = new Town("Earth");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = null;
	}

	@Test
	public void getNametest() {
		assertTrue(town1.getName().equals("Venus"));
		assertTrue(town2.getName().equals("Mercury"));
		assertTrue(town3.getName().equals("Earth"));
	}

	@Test
	public void createCopyTest() {
		Town town5 = new Town(town1);
		assertTrue(town1.equals(town5));
		
		Town town6 = new Town(town3);
		assertTrue(town3.equals(town6));
	}
	
	@Test
	public void equalsTest() {
		Town town5 = new Town("Venus");
		assertFalse(town2.equals(town5));
		
		assertTrue(town3.equals(town4));
		
		Town town6 = new Town("Venus");
		assertTrue(town1.equals(town6));
	}
	
	@Test
	public void hashCodeTest() {
		Town town5 = new Town("Venus");
		assertNotEquals(town5.hashCode(), town1.hashCode());
		
		assertEquals(town3.hashCode(), town4.hashCode());
		
		assertNotEquals(town2.hashCode(), town3.hashCode());
	}
	
	@Test
	public void compareToTest() {
		assertTrue(town1.compareTo(town2) <= 0);
		assertTrue(town3.compareTo(town4) == 0);
		assertTrue(town2.compareTo(town3) <= 0);
	}
}