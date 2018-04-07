import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		//isInGame = true;
	}

	
//	public void gameStatus(){
//		if(!canvas.checkCollision(canvas.getSnake().getHead())){
//			isOver = true;
//			isInGame = false;
//		}
//	}
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		int code = e.getKeyCode();
//		isInGame = true;
//		isOver = false;
//		switch(code){
//			case KeyEvent.VK_UP:
//				if(canvas.getSnake().getDirection() != Direction.DOWN)
//					canvas.changeDirection(Direction.UP);
//				break;
//			case KeyEvent.VK_DOWN:
//				if(canvas.getSnake().getDirection() != Direction.UP)
//					canvas.changeDirection(Direction.DOWN);
//				break;
//			case KeyEvent.VK_LEFT:
//				if(canvas.getSnake().getDirection() != Direction.RIGHT)
//					canvas.changeDirection(Direction.LEFT);
//				break;
//			case KeyEvent.VK_RIGHT:
//				if(canvas.getSnake().getDirection() != Direction.LEFT)
//					canvas.changeDirection(Direction.RIGHT);
//				break;
//			default:
//				break;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void checkScore(){
//		if(hScore == "")
//			return;
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
		System.out.println("controller");
		while(true){
			try{
				Thread.currentThread();
				Thread.sleep(130);
			}
			catch(Exception e){
				e.printStackTrace();
			}
//			System.out.print(canvas.isInGame);
			if(canvas.isInGame){
				canvas.nextStep();
//				System.out.println("in game");
				
//				gw.draw();
				
				
			}
			
//			boolean next = canvas.nextStep();
			else if (canvas.isOver) {
//            	System.out.println(next);
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
