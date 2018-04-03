import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SnakeApp implements Runnable{
	private SnakeCanvas gameView;
	private GameController gc;
	private GameWindow gameWindow;
	private GameListener gl;
	
	public void run(){
		this.gameView = new SnakeCanvas(40, 30);
		JFrame frame= new JFrame("Snake");
		this.gl = new GameListener(this.gameView);
		this.gameWindow = new GameWindow(this.gameView, this.gl);
		this.gc = new GameController(this.gameView, this.gameWindow);

		this.gameWindow.init();
		
		this.gameWindow.getWindow().setPreferredSize(new Dimension(700,500));
	    
	    frame.getContentPane().add(this.gameWindow.getWindow());
	    frame.pack();
	    frame.setResizable(false);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    new Thread(gc).start();

	}

	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new SnakeApp());
	}

}
