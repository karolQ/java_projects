import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SnakeApp implements Runnable{
	private SnakeCanvas gameView;
	private GameController gc;
	private GameWindow gameWindow;
	private GameListener gl;
	
	public void run(){
		JFrame frame= new JFrame("Snake");
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		double screenWidth = d.getWidth();
		double screenHeight = d.getHeight();
		int posWidth = (int)((screenWidth-700)/2);
		int posHeight = (int)((screenHeight-600)/2);

		frame.setBounds(posWidth, posHeight, 700, 600);
		frame.setLayout(new FlowLayout());
		
		
		this.gameView = new SnakeCanvas(40, 35);
		this.gl = new GameListener(this.gameView);
		this.gameWindow = new GameWindow(this.gameView);
		this.gc = new GameController(this.gameView, this.gameWindow);

		this.gameWindow.init();
		
		this.gameWindow.getWindow().setPreferredSize(new Dimension(700,600));
	    
	    frame.getContentPane().add(this.gameWindow.getWindow());
//	    JTextField tf= new JTextField();
//		frame.add(tf);
	    frame.pack();
	    frame.setResizable(false);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    new Thread(gc).start();
	    System.out.println("gc thread");

	}

	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new SnakeApp());
	}

}
