import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {
		private final int MAX_VERTS = 13;
		private ArrayList<Town> townList;
		private Road[][] adjMat;
		private int nVerts;
		private ArrayList<String>sPath = new ArrayList<String>();
		private Town sDest;
		private Set<Town> tSet = new HashSet<Town>();
		private Set<Road> rSet = new HashSet<Road>();
		
		public Graph() {
			townList = new ArrayList<Town>();
			adjMat = new Road[MAX_VERTS][MAX_VERTS];
			nVerts = 0;
			
		}
		
	public int getTownIndex(Town t) {
		for (int i =0; i < townList.size(); i++) {
			if (townList.get(i).equals(t)) {
				return i;
			}
		}
		return -1;
	}
	
	public void printMat() {
		for (int i = 0; i < adjMat.length; i++) {
			for (int j =0; j < adjMat[i].length; j++) {
				if (adjMat[i][j] == null && adjMat[j][i] == null) {
					continue;
				}
				else
					System.out.println(adjMat[i][j].getName() + " " + adjMat[i][j].getWeight());
			}
		}
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		int sourceIndex = getTownIndex(sourceVertex);
		int destIndex = getTownIndex(destinationVertex);
		if (sourceIndex != -1 && destIndex != -1) {
			return adjMat[sourceIndex][destIndex];
		}
		else
			return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		Road toReturn = null;
		int sourceIndex = getTownIndex(sourceVertex);
		int destIndex = getTownIndex(destinationVertex);
		if (sourceIndex != -1 && destIndex != -1) {
			adjMat[sourceIndex][destIndex] = new Road(sourceVertex, destinationVertex, weight, description);
			adjMat[destIndex][sourceIndex] = new Road(destinationVertex,sourceVertex, weight, description);
		}
		toReturn = new Road(sourceVertex, destinationVertex, weight, description);
		rSet.add(toReturn);
		return toReturn;
	}

	@Override
	public boolean addVertex(Town v) {
		// TODO Auto-generated method stub
		if (containsVertex(v)) {
			return false;
		}
		townList.add(v);
		tSet.add(v);
		nVerts++;
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		int sourceIndex = getTownIndex(sourceVertex);
		int destIndex = getTownIndex(destinationVertex);
		if (sourceIndex != -1 && destIndex != -1) {
			if (adjMat[sourceIndex][destIndex] == null && adjMat[destIndex][sourceIndex] == null) {
				return false;
			}
			else return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		// TODO Auto-generated method stub
		for (Town t: townList) {
			if (t == null) {
				return false;
			}
			if (t.equals(v)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		// TODO Auto-generated method stub
		return rSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		// TODO Auto-generated method stub
		int tIndex = getTownIndex(vertex);
		Set<Road> toReturn = new HashSet<Road>();
		for (Road r: rSet) {
			if (r.conatains(vertex)) {
				toReturn.add(r);
			}
		}
		/*
		 * for (int j =0; j < MAX_VERTS; j++) { if (adjMat[tIndex][j] != null) {
		 * toReturn.add(adjMat[tIndex][j]); } }
		 */
		return toReturn;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		Road r = null;
		int sourceIndex = getTownIndex(sourceVertex);
		int destIndex = getTownIndex(destinationVertex);
		if (adjMat[sourceIndex][destIndex] == null || adjMat[destIndex][sourceIndex] == null) {
			return null;
	}
		else {
			r = adjMat[sourceIndex][destIndex];
		}
		if (r.conatains(sourceVertex) && r.conatains(destinationVertex)) {
			if ((r.getName().equals(description)) && (r.getWeight() == weight)) {
				adjMat[sourceIndex][destIndex] = null;
				adjMat[destIndex][sourceIndex] = null;
			}
		}
		return r;
	
	}
	
	 public void removeAllEdges(int index) {
		 for (int j=0; j < MAX_VERTS; j++) {
			 adjMat[j][index] = null;
		 }
		 for (int i =0; i < MAX_VERTS; i++) {
			 adjMat[i][index] = null;
		 }
	 }

	@Override
	public boolean removeVertex(Town v) {
		// TODO Auto-generated method stub
		for (int i =0; i < townList.size(); i++) {
			if (townList.get(i).equals(v)) {
				removeAllEdges(i);
				townList.remove(i);
				break;
			}
		}
		
		return false;
	}

	@Override
	public Set<Town> vertexSet() {
		// TODO Auto-generated method stub
		for (Town t : townList) {
			if (t != null)
				tSet.add(t);
		}
		return tSet;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		sDest = destinationVertex;
		dijkstraShortestPath(sourceVertex);
        String shortPath = "";
        int totalMiles = 0;
        for (int idx = 0; idx < sPath.size() - 1; idx++) {
            Town source = new Town(sPath.get(idx));
            Town dest = new Town(sPath.get(idx + 1));
            Road road = getEdge(source, dest);
            totalMiles += road.getWeight();
            shortPath += source + " via " + road.getName() + " to " + dest 
                    + " " + road.getWeight() + " mi;";
        }
        sPath.clear();
        for (String step : shortPath.split(";")) {
            sPath.add(step);
        }
        sPath.add("Total miles: " + totalMiles + " mi");
        return sPath;
    }

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// TODO Auto-generated method stub
		sPath.clear();
		Town[] sAT = new Town[townList.size()];
		
		for (int i = 0; i < townList.size(); i++) {
			sAT[i] = new Town(townList.get(i));
		}
		int [][] secMatrix = new int[townList.size()][townList.size()];
		for (int i =0; i < secMatrix.length; i++) {
			for (int j=0; j < secMatrix[i].length; j++) {
				if (i==j || !containsEdge(sAT[i], sAT[j])) {
					secMatrix[i][j] = 0;
				}
				else {
					int weight = getEdge(sAT[i], sAT[j]).getWeight();
					secMatrix[i][j] = secMatrix[j][i] = weight;
				}
			}
		}
		
		int startTown = 0;
		for (Town t: sAT) {
			if (!t.equals(sourceVertex)) {
				startTown++;
			}else {
				break;
			}
		}
		
		int endTown = 0;
		for (Town t: sAT) {
			if (!t.equals(sDest)) {
				endTown++;
			}
			else break;
		}
		
		int numTowns = secMatrix[0].length;
		int[] sWeights = new int[numTowns];
		boolean[] added = new boolean[numTowns];
		
		for (int townIdx =0; townIdx < numTowns; townIdx++) {
			sWeights[townIdx] = Integer.MAX_VALUE;
			added[townIdx] = false;
		}
		
		sWeights[startTown] = 0;
		
		int [] parents = new int[numTowns];
		
		parents[startTown] = -1;
		
        for (int i = 1; i < numTowns; i++) {
            int nearestTown = -1;
            int smallestWeight = Integer.MAX_VALUE;
            for (int townIdx = 0; townIdx < numTowns; townIdx++) {
                if (!added[townIdx] && 
                        sWeights[townIdx] < smallestWeight) {
                    nearestTown = townIdx;
                    smallestWeight = sWeights[townIdx];
                }
            }
            added[nearestTown] = true;
            for (int townIdx = 0; townIdx < numTowns; townIdx++) {
                int roadDist = secMatrix[nearestTown][townIdx]; 
                if (roadDist > 0 && 
                        ((smallestWeight + roadDist) < sWeights[townIdx])) {
                    parents[townIdx] = nearestTown;
                    sWeights[townIdx] = smallestWeight + roadDist;
                }
            }           
        }
        populatePathArrayList(endTown, parents); 
	}
	
	
	private void populatePathArrayList(int currentVertex, int[]parents) {
        if (currentVertex == -1) { 
            return; 
        } 
        populatePathArrayList(parents[currentVertex], parents); 
        int townIdx = 0;
        for (Town t : townList) {
            if (townIdx == currentVertex) {
                sPath.add(t.getName()); 
            }
            townIdx++;
        }
    } 
		
	}
