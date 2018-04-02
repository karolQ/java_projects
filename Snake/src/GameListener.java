import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

public class GameListener implements KeyListener{
	private SnakeCanvas canvas;
	
	public GameListener(SnakeCanvas can){
		this.canvas = can;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
			int code = e.getKeyCode();
			switch(code){
				case KeyEvent.VK_UP:
					if(canvas.getSnake().getDirection() != Direction.DOWN)
						canvas.changeDirection(Direction.UP);
					break;
				case KeyEvent.VK_DOWN:
					if(canvas.getSnake().getDirection() != Direction.UP)
						canvas.changeDirection(Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					if(canvas.getSnake().getDirection() != Direction.RIGHT)
						canvas.changeDirection(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					if(canvas.getSnake().getDirection() != Direction.LEFT)
						canvas.changeDirection(Direction.RIGHT);
					break;
				default:
					break;
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
