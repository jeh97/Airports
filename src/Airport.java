import java.util.*;

public class Airport {
	private String name;
	private String code;
	private ArrayList<Route> routes;
	private Metro city;
	public Airport(String codeIATA, Metro cty) {
		code = codeIATA;
		name = "";
		routes = new ArrayList<Route>();
		city = cty;
	}
	/**
	 * method to get the airport's IATA code
	 * @return IATA code
	 */
	public String getCode() { return code; }
	/**
	 * method to get airport's name. Returns blank string if no name is assigned
	 * @return Airport name or blank string if no name is assigned
	 */
	public String getName() { return name; }
	/**
	 * method to set the airport's name
	 * @param nm
	 */
	public void setName(String nm) { name = nm; }
	/**
	 * method to get the airport's routes
	 * @return the airport's routes
	 */
	public ArrayList<Route> getRoutes() { return routes; }
	public Metro getCity() { return city;}
	public void addRoute(Route rt) { routes.add(rt); }
	public String toString() {
		return "#IATA Code: <"+code+"> Metro Area: "+city+"#";
	}
	public boolean equals(Airport obj) {
		return name.equals(obj.getName()) && code.equals(obj.getCode()) && routes.equals(obj.getRoutes()) && city.equals(obj.getCity());
	}
	protected Airport clone() {
		Airport cpy = new Airport(this.code,this.city);
		cpy.setName(this.name);
		for (Route rt:routes) {
			cpy.addRoute(rt);
		}
		return cpy;
	}
}
