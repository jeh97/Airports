import java.util.*;
import java.io.*;
public class Metro {
	private String metro;
	private ArrayList<Airport> airports;
	private double xCoordFactor;
	private double yCoordFactor;
	public Metro(String name) {
		metro = name;
		airports = new ArrayList<Airport>();
		xCoordFactor = 0.0;
		yCoordFactor = 0.0;
	}
	public Metro(String name, double xFact, double yFact) {
		metro = name;
		airports = new ArrayList<Airport>();
		xCoordFactor = xFact;
		yCoordFactor = yFact;
	}
	public void addAirport(Airport airport) { airports.add(airport); }
	public String toString() {
		return metro;
	}
	public String getName() { return metro;	}
	// returns the position of the city on Map2 given the map size is 1x1
	public double getXFactor() {return xCoordFactor;}
	public double getYFactor() {return yCoordFactor;}
	public double setXFactor(double xFact) {return xCoordFactor = xFact;}
	public double setYFactor(double yFact) {return yCoordFactor = yFact;}
	public ArrayList<Airport> getAirports() {return airports;}
	public boolean equals(Metro obj) {
		return metro.equals(obj.getName()) && airports.equals(obj.getAirports()) && 
				(xCoordFactor == obj.getXFactor()) && (yCoordFactor == obj.getYFactor());
	}
	protected Metro clone() {
		Metro cpy = new Metro(this.metro,this.xCoordFactor,this.yCoordFactor);
		for (Airport apt: airports) {
			cpy.addAirport(apt);
		}
		return cpy;
	}
}