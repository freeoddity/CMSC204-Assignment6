import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Town implements Comparable<Town>{
	private String name;
	private List <Town> adjacentTowns;
	public Town(String string) {
		// TODO Auto-generated constructor stub
		this.name = string;
		adjacentTowns = new ArrayList<Town>();
	}
	
	public Town(Town town) {
		this.name = town.getName();
		this.adjacentTowns = town.getAdjacentTowns();
	}

	public List<Town> getAdjacentTowns() {
		return adjacentTowns;
	}

	public void setAdjacentTowns(List<Town> adjacentTowns) {
		this.adjacentTowns = adjacentTowns;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public boolean equals(Object o) {
		return ((Town)o).getName().equals(getName());
	}
	
	public int hashcode() {
		return getName().hashCode();
	}
	
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		return getName().compareTo(o.getName());
	}

}
