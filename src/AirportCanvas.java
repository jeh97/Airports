import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.awt.FontMetrics;
import java.awt.Font;

import javax.imageio.ImageIO;





public class AirportCanvas extends Canvas implements MouseListener, MouseMotionListener {
	public static final int RADIUS = 4;	//Pixel radius of city dots
	private static final long serialVersionUID = 1L;
	
	private BufferedImage img; //the background image
	private int lastX,lastY;
	private int windowWidth, windowHeight;
	private TreeMap<String,Airport> airports;
	private int mapW,mapH;
	private TreeMap<String,CityDot> cityDots;
	private XmlReader xml;
	private ArrayList<Route> routesToShow;
	private CityDot cityToLabel;
	
	public AirportCanvas(int winWidth, int winHeight) {
		windowWidth = winWidth;
		windowHeight = winHeight;
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
		xml = new XmlReader();
		
		try {
			resize("/Users/Jacob/Dropbox/Java/Airports/Resources/Map2.png",
					"/Users/Jacob/Dropbox/Java/Airports/Resources/Map2resize.png",0.5);
			img = ImageIO.read(new File("/Users/Jacob/Dropbox/Java/Airports/Resources/Map2resize.png"));
			mapW = img.getWidth();
			mapH = img.getHeight();
			cityDots = new TreeMap<String,CityDot>();
			routesToShow = new ArrayList<Route>();
			cityToLabel = null;
			airports = xml.getAirports();
			for (Map.Entry<String,Airport> entry:airports.entrySet()) {
				Airport apt = entry.getValue();
				Metro city = apt.getCity();
				int x = (int)(city.getXFactor()*mapW);
				int y = (int)(city.getYFactor()*mapH);
				CityDot t = new CityDot(x,y,city);
				cityDots.put(city.getName(),t);
				//routesToShow.addAll(apt.getRoutes());
			}
			
		} catch (IOException e) {
			System.out.println("Failed to load image");
			System.out.println(e);
		}
		
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		
		if (img!=null) {
			int x = (getWidth() - img.getWidth()) / 2;
			int y = (getHeight() - img.getHeight()) / 2;
			graphics.drawImage(img,0,0,this);
		}
		graphics.setColor(Color.red);
		for (Map.Entry<String,CityDot> entry:cityDots.entrySet()) {
			CityDot dot = entry.getValue();
			//System.out.printf("Drew dot for %s at %d, %d\n",dot.getCity().getName(),dot.getX(),dot.getY());
			graphics.fillOval(dot.getX()-RADIUS, dot.getY()-RADIUS, 2*RADIUS, 2*RADIUS);
		}
		graphics.setColor(Color.blue);
		//System.out.println(routesToShow);
		for (Route r:routesToShow) {
			int startX,startY,endX,endY;
			
			String orig = r.getOrigin().getCity().getName();
			String dest = r.getDestination().getCity().getName();
			
			CityDot originDot = cityDots.get(orig);
			CityDot destDot = cityDots.get(dest);
			
			startX = originDot.getX();
			startY = originDot.getY();
			endX = destDot.getX();
			endY = destDot.getY();
			graphics.drawLine(startX,startY,endX,endY);
		}
		if (cityToLabel != null) {
			//System.out.printf("Drawing label for %s\n", cityToLabel.getCity().getName());
			graphics.setFont(new Font("Monospaced",0,12));
			FontMetrics fm = graphics.getFontMetrics();
			int x = fm.stringWidth(cityToLabel.getCity().getName());
			int y = fm.getAscent();
			graphics.setColor(Color.decode("#000080"));
			graphics.drawString(cityToLabel.getCity().getName(),cityToLabel.getX(),cityToLabel.getY());
		}
	}
	private static void resize(String inputImagePath, String outputImagePath,int scaledWidth,int scaledHeight) throws IOException{
		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		
		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight,inputImage.getType());
		
		//scales the input image to output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		
		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".")+1);
		
		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}
	private static void resize(String inputImagePath, String outputImagePath,double percent) throws IOException {
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		int scaledWidth = (int) (inputImage.getWidth()*percent);
		int scaledHeight = (int) (inputImage.getHeight()*percent);
		resize(inputImagePath,outputImagePath,scaledWidth,scaledHeight);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Map.Entry<String,CityDot> entry:cityDots.entrySet()) {
			CityDot t = entry.getValue();
			if (t.inDot(e.getX(),e.getY())) {
				//System.out.printf("hovering over %s\n", t.getCity().getName());
				cityToLabel = t;
				break;
			} else {
				cityToLabel = null;
			}
		}
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (Map.Entry<String,CityDot> entry: cityDots.entrySet()) {
			CityDot t = entry.getValue();
			if (t.inDot(e.getX(),e.getY())) {
				//System.out.printf("Clicked on %s\n", t.getCity().getName());
				routesToShow = new ArrayList<Route>();
				//System.out.println(t.getCity().getAirports());
				for (Airport a: t.getCity().getAirports()) {
					//System.out.print("Airport: ");
					//System.out.println(a);
					//System.out.printf("And has %d routes\n", a.getRoutes().size());
					routesToShow.addAll(a.getRoutes());
				}
				//System.out.print("Inside mouseClicked: ");
				//System.out.println(routesToShow);
			}
		}
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}



class CityDot {
	private int x;
	private int y;
	private Metro city;
	public CityDot(int x, int y, Metro city) {
		this.x = x;
		this.y = y;
		this.city = city;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public int setX(int x) {return this.x = x;}
	public int setY(int y) {return this.y = y;}
	public Metro getCity() {return city;}
	//Check if a coord is in the dot
	public boolean inDot(int x, int y) {
		return ((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y) <= (AirportCanvas.RADIUS*AirportCanvas.RADIUS));
	}
}
