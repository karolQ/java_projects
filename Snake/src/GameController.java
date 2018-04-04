import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable {
	private boolean isInGame = false;
	private boolean isOver = false;
	private SnakeCanvas canvas;
	private GameWindow gw;
	
	public GameController(SnakeCanvas snakeCanvas, GameWindow gameWin){
		
		this.canvas = snakeCanvas;
		this.gw = gameWin;
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

	@Override
	public void run() {
		System.out.println("controller");
		while(true){
			try{
				Thread.currentThread();
				Thread.sleep(150);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			boolean next = canvas.nextStep();
            if (next == false) {
            	System.out.println(next);
                gw.gameOver();
            }
            gw.draw();
		}
		
	}
}
