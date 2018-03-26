import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SnakeApp implements Runnable{
	public void run(){
		SnakeCanvas gameView = new SnakeCanvas();
		JFrame frame= new JFrame("Snake");
		GameWindow gameWindow = new GameWindow(gameView);
		gameWindow.init();
		int width = 800;
		int height = 600;
		gameWindow.getWindow().setPreferredSize(new Dimension(width,height));
		Container pane = frame.getContentPane();
	    pane.add(gameWindow.getWindow(), BorderLayout.CENTER);
	        
	    frame.pack();
	    
	    frame.setResizable(false);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GameController gc = new GameController(gameView);
	    new Thread().start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SnakeApp());
	}

}
