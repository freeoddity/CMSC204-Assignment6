
public class Road implements Comparable<Road>	 {
	private Town source;
	private Town destination;
	private int degrees;
	private String name;

	public Road(Town source, Town destination, int degrees, String name) {
		// TODO Auto-generated constructor stub
		this.source = source; this.destination = destination;
		this.degrees = degrees; this.name = name;
	}
	public Road(Town source, Town destination, String name) {
		this.source = source; this.destination = destination;
		this.degrees = 1; this.name = name;

	}
	
	public Road(Road road) {
		// TODO Auto-generated constructor stub
		this.degrees = road.getDegrees();
		this.destination = road.getDestination();
		this.name = road.getName();
		this.source = road.getSource();
	}
	public int getWeight() {
		return getDegrees()	;
	}
	
	boolean conatains(Town town) {
			if (getSource().equals(town)){
				return true;
			}
			else if (getDestination().equals(town)) {
				return true;
			}
			else return false;
	}
	
	

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public Town getSource() {
		return source;
	}
	public void setSource(Town source) {
		this.source = source;
	}
	public Town getDestination() {
		return destination;
	}
	public void setDestination(Town destination) {
		this.destination = destination;
	}
	public int getDegrees() {
		return degrees;
	}
	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Object r) {
		Road comapare = (Road) r;
		if (getSource().equals(comapare.getDestination()) && getDestination().equals(comapare.getSource())) {
			return true;
		}
		else if (getSource().equals(comapare.getSource()) && getDestination().equals(comapare.getDestination())) {
			return true;	
		}
		else return false;
		
	}
	@Override
	public int compareTo(Road o) {
		return getName().compareTo(o.getName());
	}

}
