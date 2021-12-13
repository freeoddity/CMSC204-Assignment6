import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	
	TownGraphManager TGM;

	String town1;
	String town2;
	String town3;
	String town4;
	String town5;
	String town6;
	String town7;
	String town8;
	
	@Before
	public void setUp() throws Exception {
		TGM = new TownGraphManager();
		
		town1 = "Venus";
		town2 = "Mercury";
		town3 = "Earth";
		town4 = "Mars";
		town5 = "Jupiter";
		town6 = "Saturn";
		town7 = "Uranus";
		town8= "Neptune";
		
		TGM.addTown(town1);
		TGM.addTown(town2);
		TGM.addTown(town3);
		TGM.addTown(town4);
		TGM.addTown(town5);
		TGM.addTown(town6);
		TGM.addTown(town7);
		TGM.addTown(town8);
		
		TGM.addRoad(town1, town2, 3, "S1TOS2");
		TGM.addRoad(town1, town3, 8, "S1TOS3");
		TGM.addRoad(town2, town3, 5, "S2TOS3");
		TGM.addRoad(town3, town4, 10, "S3TOS4");
		TGM.addRoad(town4, town5, 3, "S4TOS5");
		TGM.addRoad(town3, town8, 2, "S3TOS8");
		TGM.addRoad(town3, town7, 3, "S3TOS7");
		TGM.addRoad(town3, town6, 1, "S3TOS6");
		TGM.addRoad(town1, town8, 16, "S1TOS8");
		
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = town4 = town5 = town6 = town7 = town8 = null;
		TGM = null;
	}

	@Test
	public void testAddRoad() {
		TGM.addRoad(town4, town1, 5, "Test1");
		assertTrue(TGM.getRoad(town4, town1).equals("Test1"));
		
		TGM.addRoad(town1, town5, 800, "Test2");
		assertTrue(TGM.getRoad(town1, town5).equals("Test2"));
		
		TGM.addRoad(town2, town4, 90, "Tes3");
		assertTrue(TGM.getRoad(town2, town4).equals("Tes3"));
		
		TGM.addRoad(town4, town3, 50, "Test4");
		assertFalse(TGM.getRoad(town4, town3).equals("Not Test4"));
	}

	@Test
	public void testGetRoad() {
		assertTrue(TGM.getRoad(town1, town2).equals("S1TOS2"));
		assertTrue(TGM.getRoad(town1, town3).equals("S1TOS3"));
		assertTrue(TGM.getRoad(town1, town8).equals("S1TOS8"));
	}

	@Test
	public void testAddTown() {
		String tTown1 = "LOLTOWN";
		String tTown2 = "SCREW PS5 SCALPERS";
		
		TGM.addTown(tTown1);
		assertTrue(TGM.containsTown(tTown1));
		
		assertFalse(TGM.containsTown(tTown2));
		
		TGM.addTown(tTown2);
		assertTrue(TGM.containsTown(tTown2));
	}

	@Test
	public void testGetTown() {
		assertNotNull(TGM.getTown("Earth"));
		assertNotNull(TGM.getTown("Mercury"));
		assertNotNull(TGM.getTown("Venus"));
		assertNotNull(TGM.getTown("Jupiter"));
		assertNull(TGM.getTown("Pluto"));
	}

	@Test
	public void testContainsTown() {
		assertTrue(TGM.containsTown(town1));
		assertTrue(TGM.containsTown(town4));
		assertTrue(TGM.containsTown(town6));
		
		assertFalse(TGM.containsTown("Astroid"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertTrue(TGM.containsRoadConnection(town1, town2));
		assertTrue(TGM.containsRoadConnection(town1, town3));
		assertTrue(TGM.containsRoadConnection(town3, town4));
		assertFalse(TGM.containsRoadConnection(town1, town5));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> allRoads = TGM.allRoads();
		assertTrue(allRoads.contains("S3TOS4"));
		assertTrue(allRoads.contains("S1TOS3"));
		assertTrue(allRoads.contains("S1TOS8"));
		assertTrue(allRoads.contains("S3TOS7"));
		assertTrue(allRoads.contains("S3TOS6"));
		assertTrue(allRoads.contains("S2TOS3"));
		assertTrue(allRoads.contains("S1TOS2"));
		assertTrue(allRoads.contains("S4TOS5"));
		assertTrue(allRoads.contains("S3TOS8"));
	}

	@Test
	public void testDeleteRoadConnection() {
		TGM.deleteRoadConnection(town1, town2, "S1TOS2");
		assertFalse(TGM.containsRoadConnection(town1, town2));
		
		assertTrue(TGM.containsRoadConnection(town4, town5));
		TGM.deleteRoadConnection(town4, town5, "S4TOS5");
		assertFalse(TGM.containsRoadConnection(town4, town5));
	}

	@Test
	public void testDeleteTown() {
		TGM.deleteTown(town1);
		assertTrue(!TGM.containsTown(town1));
		assertFalse(TGM.containsRoadConnection(town1, town2));
		
		assertTrue(TGM.containsTown(town4));
		TGM.deleteTown(town4);
		assertFalse(TGM.containsTown(town4));
		assertFalse(TGM.containsRoadConnection(town4, town5));
	}

	@Test
	public void testAllTowns() {
		ArrayList<String> allTown = TGM.allTowns();
		assertTrue(allTown.contains("Saturn"));
		assertTrue(allTown.contains("Neptune"));
		assertTrue(allTown.contains("Mars"));
		assertTrue(allTown.contains("Venus"));
		assertTrue(allTown.contains("Mercury"));
		assertTrue(allTown.contains("Earth"));
		assertTrue(allTown.contains("Jupiter"));
		assertTrue(allTown.contains("Uranus"));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = TGM.getPath(town1, town7);	
		assertTrue(path.get(0).equals("Venus via S1TOS3 to Earth 8 mi"));
		assertTrue(path.get(1).equals("Earth via S3TOS7 to Uranus 3 mi"));
		
		path = TGM.getPath(town5, town1);
		assertTrue(path.get(0).equals("Jupiter via S4TOS5 to Mars 3 mi"));
		assertTrue(path.get(1).equals("Mars via S3TOS4 to Earth 10 mi"));
		assertTrue(path.get(2).equals("Earth via S1TOS3 to Venus 8 mi"));
	}

	@Test
	public void testPopulateTownGraph() throws FileNotFoundException {
		File newFile = new File("testFile.txt");
		
		PrintWriter pw = new PrintWriter(newFile);
        pw.println("SPACE,140;Venus;Earth");
        pw.println("SPACE2,280;Earth;Mars");
        pw.println("SPACE3,500;Venus;Mercury");
		
		pw.close();
		
		try {
			TGM.populateTownGraph(newFile);
			assertTrue("No exception thrown", true);
		} catch (Exception e) {
			assertTrue("Exception thrown", false);
		}
		
		assertTrue(TGM.containsTown("Mercury"));
		assertTrue(TGM.containsTown("Venus"));
		
		newFile.delete();
	}

}