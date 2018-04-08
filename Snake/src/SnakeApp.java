import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
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
		int posWidth = (int)((screenWidth-540)/2);
		int posHeight = (int)((screenHeight-575)/2);

		frame.setBounds(posWidth, posHeight, 540, 575);
		
		this.gameView = new SnakeCanvas(40, 35);
		this.gl = new GameListener(this.gameView);
		this.gameWindow = new GameWindow(this.gameView);
		this.gc = new GameController(this.gameView, this.gameWindow);
		new Thread(gc).start();
		this.gameWindow.init();
		
		this.gameWindow.getWindow().setPreferredSize(new Dimension(540,575));
	    
	    frame.getContentPane().add(this.gameWindow.getWindow());
	    frame.pack();
	    frame.setResizable(false);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	   
	    System.out.println("SankeApp is running");

	}

	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new SnakeApp());
	}

}
