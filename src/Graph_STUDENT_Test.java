
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	
	Graph townGraph;

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	Town town5;
	Town town6;
	Town town7;
	Town town8;
	
	@Before
	public void setUp() throws Exception {
		townGraph = new Graph();
		
		town1 = new Town("Venus");
		town2 = new Town("Mercury");
		town3 = new Town("Earth");
		town4 = new Town("Mars");
		town5 = new Town("Jupiter");
		town6 = new Town("Saturn");
		town7 = new Town("Uranus");
		town8 = new Town("Neptune");
		
		townGraph.addVertex(town1);
		townGraph.addVertex(town2);
		townGraph.addVertex(town3);
		townGraph.addVertex(town4);
		townGraph.addVertex(town5);
		townGraph.addVertex(town6);
		townGraph.addVertex(town7);
		townGraph.addVertex(town8);
		
		townGraph.addEdge(town1, town2, 3, "S1TOS2");
		townGraph.addEdge(town1, town3, 8, "S1TOS3");
		townGraph.addEdge(town2, town3, 5, "S2TOS3");
		townGraph.addEdge(town3, town4, 10, "S3TOS4");
		townGraph.addEdge(town4, town5, 3, "S4TOS5");
		townGraph.addEdge(town3, town8, 2, "S3TOS8");
		townGraph.addEdge(town3, town7, 3, "S3TOS7");
		townGraph.addEdge(town3, town6, 1, "S3TOS6");
		townGraph.addEdge(town1, town8, 16, "S1TOS8");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = town4 = town5 = town6 = town7 = town8 = null;
		townGraph = null;
	}

	@Test
	public void testGetEdge() {
		assertTrue(townGraph.getEdge(town1, town2).getName().equals("S1TOS2"));
		assertTrue(townGraph.getEdge(town3, town4).getName().equals("S3TOS4"));
		assertTrue(townGraph.getEdge(town4, town5).getName().equals("S4TOS5"));
		assertTrue(townGraph.getEdge(town3, town7).getName().equals("S3TOS7"));
		assertTrue(townGraph.getEdge(town1, town8).getName().equals("S1TOS8"));
	}

	@Test
	public void testAddEdge() {
		townGraph.addEdge(town4, town1, 20, "E1");
		assertTrue(townGraph.getEdge(town4, town1).getName().equals("E1"));
		
		townGraph.addEdge(town1, town5, 10, "E2");
		assertTrue(townGraph.getEdge(town1, town5).getName().equals("E2"));
		
		townGraph.addEdge(town2, town4, 4, "E3");
		assertTrue(townGraph.getEdge(town2, town4).getName().equals("E3"));
		
		townGraph.addEdge(town4, town3, 50, "E4");
		assertFalse(townGraph.getEdge(town4, town3).getName().equals("Not E4"));
		
	}

	@Test
	public void testAddVertex() {
		Town tTown1 = new Town("Test");
		Town tTown2 = new Town("Test2");
		
		townGraph.addVertex(tTown1);
		assertTrue(townGraph.containsVertex(tTown1));
		
		assertFalse(townGraph.containsVertex(tTown2));
		
		townGraph.addVertex(tTown2);
		assertTrue(townGraph.containsVertex(tTown2));
	}

	@Test
	public void testContainsEdge() {
		assertTrue(townGraph.containsEdge(town1, town2));
		assertTrue(townGraph.containsEdge(town1, town8));
		assertFalse(townGraph.containsEdge(town4, town6));
	}

	@Test
	public void testContainsVertex() {
		assertTrue(townGraph.containsVertex(town1));
		assertTrue(townGraph.containsVertex(town4));
		assertTrue(townGraph.containsVertex(town3));
		assertTrue(townGraph.containsVertex(town7));
		
		Town tTown1 = new Town("Test");
		
		assertFalse(townGraph.containsVertex(tTown1));
	}

	@Test
	public void testEdgeSet() {
		Set<String> tRoad = new HashSet<>();
		for (Road road : townGraph.edgeSet())
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("S1TOS3"));
		assertTrue(tRoad.contains("S3TOS8"));
		assertTrue(tRoad.contains("S2TOS3"));
		assertTrue(tRoad.contains("S3TOS4"));
		assertTrue(tRoad.contains("S4TOS5"));
		assertTrue(tRoad.contains("S1TOS8"));
		assertTrue(tRoad.contains("S3TOS7"));
		assertTrue(tRoad.contains("S1TOS2"));
		assertTrue(tRoad.contains("S3TOS6"));
	}

	@Test
	public void testEdgesOf() {
		Set<String> tRoad = new HashSet<>();
		for (Road road : townGraph.edgesOf(town1))
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("S1TOS3"));
		assertTrue(tRoad.contains("S1TOS8"));
		assertTrue(tRoad.contains("S1TOS2"));
		
		tRoad.clear();
		for (Road road : townGraph.edgesOf(town2))
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("S2TOS3"));
		assertTrue(tRoad.contains("S1TOS2"));
		
	}

	@Test
	public void testRemoveEdge() {
		townGraph.removeEdge(town1, town2, 3, "S1TOS2");
		assertFalse(townGraph.containsEdge(town1, town2));
		townGraph.removeEdge(town4, town5, 3 , "S4TOS5");
		assertFalse(townGraph.containsEdge(town4, town5));
	}

	@Test
	public void testRemoveVertex() {
		townGraph.removeVertex(town1);
		assertFalse(townGraph.containsVertex(town1));
		assertFalse(townGraph.containsEdge(town1, town2));
	}

	@Test
	public void testVertexSet() {
		Set<Town> townSet = townGraph.vertexSet();
		
		assertTrue(townSet.contains(town1));
		assertTrue(townSet.contains(town2));
		assertTrue(townSet.contains(town3));
		assertTrue(townSet.contains(town4));
		assertTrue(townSet.contains(town5));
		assertTrue(townSet.contains(town6));
		assertTrue(townSet.contains(town7));
		assertTrue(townSet.contains(town8));
	}

	@Test
	public void testShortestPath() {
		ArrayList<String> shortPath = townGraph.shortestPath(town1, town8);
		assertTrue(shortPath.get(0).equals("Venus via S1TOS3 to Earth 8 mi"));
		assertTrue(shortPath.get(1).equals("Earth via S3TOS8 to Neptune 2 mi"));
		
		shortPath = townGraph.shortestPath(town4, town1);
		assertTrue(shortPath.get(0).equals("Mars via S3TOS4 to Earth 10 mi"));
		assertTrue(shortPath.get(1).equals("Earth via S1TOS3 to Venus 8 mi"));
	}

}