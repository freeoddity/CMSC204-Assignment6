import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	Town town5;
	
	Road road1;
	Road road2;
	Road road3;
	Road road4;
	Road road5;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Venus");
		town2 = new Town("Mercury");
		town3 = new Town("Earth");
		town4 = new Town("Mars");
		town5 = new Town("Jupiter");
		
		road1 = new Road(town1, town2, 3, "S1TOS2");
		road2 = new Road(town1, town3, 8, "S1TOS3");
		road3 = new Road(town2, town3, 5, "S2TOS3");
		road4 = new Road(town3, town4, 10, "S3TOS4");
		road5 = new Road(town4, town5, 3, "S4TOS5");
	}

    @Test
	public void equalsTest() {
		Road road6 = new Road(town1,town2, 3, "S1TOS2");
		assertTrue(road1.equals(road6));
		assertFalse(road3.equals(road1));
		assertFalse(road4.equals(road5));
		assertFalse(road6.equals(road4));
	}
	
	@Test
	public void getDestinationTest() {
		assertTrue(road1.getDestination().getName().equals("Mercury"));
		assertTrue(road2.getDestination().getName().equals("Earth"));
		assertFalse(road3.getDestination().getName().equals("Jupiter"));
	}
	
	@Test
	public void getNameTest() {
		assertTrue(road1.getName().equals("S1TOS2"));
		assertTrue(road2.getName().equals("S1TOS3"));
		assertFalse(road5.getName().equals("S3TOS4"));
	}
	
	@Test
	public void getSourceTest() {
		assertTrue(road1.getSource().getName().equals("Gaithersburg"));
		assertTrue(road2.getSource().getName().equals("Gaithersburg"));
		assertTrue(road3.getSource().getName().equals("Rockville"));
	}
	
	@Test
	public void getWeightTest() {
		assertEquals(road1.getWeight(), 3);
		assertEquals(road2.getWeight(), 8);
		assertEquals(road5.getWeight(), 3);
		assertEquals(road4.getWeight(), 10);
	}
	
	@Test
	public void compareToTest() {
		assertTrue(road1.compareTo(road2) < 0);
		assertTrue(road4.compareTo(road2) > 0);
		assertFalse(road4.compareTo(road1) < 0);
	}
}