import java.util.*;

public class Airline {
	private String name;
	private ArrayList<Route> routes;
	public Airline(String nm) {
		name = nm;
		routes = new ArrayList<Route>();
	}
	/**
	 * method to add a new destination to the airline, 
	 * @param code
	 */
	public void addRoute(Route newRoute) { routes.add(newRoute); }
	public ArrayList<Route> getRoutes() { return routes; }
	public String getName() { return name; }
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) {
			Airline other = (Airline) obj;
			return name.equals(other.getName()) && routes.equals(other.getRoutes());
		}
		return false;
	}
}
