import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;


public class Client {
	
	
	
	
	
	
	public static void main(String args []) throws FileNotFoundException {
		TreeMap<String,Airport> airports = XmlReader.readCities();
		System.out.println(airports);
		
		/**
		Airline deltaairlines = new Airline("Delta Air Lines");
		Metro batonRouge = new Metro("Baton Rouge");
		Airport btr = new Airport("BTR",batonRouge);
		btr.setName("Baton Rouge Metropolitan Airport");
		batonRouge.addAirport(btr);
		
		Metro atlanta = new Metro("Atlanta");
		Airport atl = new Airport("ATL",atlanta);
		atl.setName("Hartsfield-Jackson Atlanta International Airport");
		atlanta.addAirport(atl);
		
		Route r1 = new Route(btr,atl);
		btr.addRoute(r1);
		r1.addOperator(deltaairlines);
		deltaairlines.addRoute(r1);
		Route r2 = new Route(atl,btr);
		atl.addRoute(r2);
		r2.addOperator(deltaairlines);
		deltaairlines.addRoute(r2);
		
		System.out.println(batonRouge);**/
	}
}
