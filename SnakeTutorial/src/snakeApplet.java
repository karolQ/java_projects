import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class snakeApplet extends Applet {
	
	private snakeCanvas c;
	
	public void init(){
		c = new snakeCanvas();
		c.setPreferredSize(new Dimension(640, 480));
		c.setVisible(true);
		c.setFocusable(true);
		this.add(c);
		this.setVisible(true);
		this.setSize(new Dimension(640,480));
	}
	
	public void paint(Graphics g){
		this.setSize(new Dimension(640,480));
	}
	/* I need a main!!!
	 public static void main(String[] args) {
	    SwingUtilities.invokeLater((Runnable) new snakeApplet());
	    }
	    */
}
