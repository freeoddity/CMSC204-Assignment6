import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph = new Graph();

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		// TODO Auto-generated method stub
		try {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		graph.addEdge(t1, t2, weight, roadName);
		return true;
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		// TODO Auto-generated method stub
		try {
			Town t1 = new Town(town1);
			Town t2 = new Town(town2);
			Road r = graph.getEdge(t1, t2);
			return r.getName();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addTown(String v) {
		// TODO Auto-generated method stub
		try {
			graph.addVertex(new Town(v));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Town getTown(String name) {
		// TODO Auto-generated method stub
		try {
			Town a = new Town(name);
			for (Town t: graph.vertexSet()) {
				if (t.equals(a)) {
					return t;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		// TODO Auto-generated method stub
		
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		// TODO Auto-generated method stub
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		// TODO Auto-generated method stub
		ArrayList<String> roads = new ArrayList<>();
		for (Road r: graph.edgeSet()) {
			roads.add(r.getName());
		}
		Collections.sort(roads);
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		// TODO Auto-generated method stub
        int weight = 0;
        for (Road r : graph.edgeSet()) {
            if (r.getName().equals(getRoad(town1, town2))) {
                weight = r.getWeight();
            }
        }
        return graph.removeEdge(new Town(town1), 
                new Town(town2), weight, road) != null;
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		Town townA;
		townA = new Town(v);
		return graph.removeVertex(townA);
	}

	@Override
	public ArrayList<String> allTowns() {
		// TODO Auto-generated method stub
        ArrayList<String> towns = new ArrayList<>();
        for (Town t : graph.vertexSet()) {
            towns.add(t.getName());
        }
        Collections.sort(towns);
        return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
        return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException,IOException	 {
		// TODO Auto-generated method stub
        Scanner readInput = new Scanner(selectedFile);
        String text = "";
        while (readInput.hasNextLine()) {
            text += readInput.nextLine() + " ";
        }
        readInput.close();
        
        String[] roads = text.split(" ");
        String[][] roadsInfo = new String[roads.length][];
        
        for (int i = 0; i < roadsInfo.length; i++) {
            
            roadsInfo[i] = new String[4];
            roadsInfo[i][0] = roads[i].split(";")[0].split(",")[0];
            roadsInfo[i][1] = roads[i].split(";")[0].split(",")[1];
            roadsInfo[i][2] = roads[i].split(";")[1];
            roadsInfo[i][3] = roads[i].split(";")[2];
            
            addTown(roadsInfo[i][2]);
            addTown(roadsInfo[i][3]);
            addRoad(roadsInfo[i][2], roadsInfo[i][3], 
                    Integer.parseInt(roadsInfo[i][1]), roadsInfo[i][0]);
        }
		
	}

}
