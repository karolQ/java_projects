import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class GameController implements Runnable {

	private SnakeCanvas canvas;
	private GameWindow gw;
	private String hScore;
	
	public GameController(SnakeCanvas snakeCanvas, GameWindow gameWin){
		
		this.canvas = snakeCanvas;
		this.gw = gameWin;
		hScore = gw.getHighScore();
		System.out.println(hScore);
	}
	
	// compare current score and high score in record
	public void checkScore(){
		if(canvas.getScore() > Integer.parseInt(hScore.split(":")[1])){
			String name = JOptionPane.showInputDialog("You set a new highscore. Please enter your name: ");
			hScore = name + ":" + canvas.getScore();
			
			File scoreFile = new File("highscore.dat");
			if(!scoreFile.exists())
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try{
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(hScore);
				
				
			}
			catch(Exception e){
				
			}
			finally{
				try{
					if(writer != null)
						writer.close();
				}catch(Exception e){
					
				}
			}
		}
	}

	@Override
	public void run() {
		System.out.println("game controller is running");
		while(true){
			try{
				Thread.currentThread();
				Thread.sleep(130);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			if(canvas.isInGame){
//				System.out.print("In game");
				canvas.nextStep();
			}
			
			else if (canvas.isOver) {
            	System.out.println("Game over");
            	checkScore();
            	
            	canvas.isInGame = false;
            	canvas.isInMenu = true;
            	canvas.isOver = false;
            	canvas.defaultSnake();
            	gw.refreshHighScore();
            }
			gw.getWindow().repaint();
		}
	}
}
