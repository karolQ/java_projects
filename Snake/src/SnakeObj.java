import java.awt.Point;
import java.util.LinkedList;

public class SnakeObj {
	private LinkedList<Point> snake;
	
	
	public void setDefault(){
		snake = new LinkedList<Point>();
		snake.add(new Point(0,2));
		snake.add(new Point(0,1));
		snake.add(new Point(0,0));
		Direction.setDirection(0);
	}
	
	public void move(){
		
	}
	
	public void eatFruit(){
		
	}
	
	public void checkCollision(){
		
	}
	
	
}
