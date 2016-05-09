import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class AirportFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 800;
	private AirportCanvas canvas;
	public void init() {
		canvas = new AirportCanvas(WIDTH,HEIGHT);
		add(canvas);
	}
	public AirportFrame() {
		super("Airports");
		setBounds(100,100,WIDTH,HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String [] args) {
		AirportFrame win = new AirportFrame();
		win.init();
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		win.setVisible(true);
	}
}
