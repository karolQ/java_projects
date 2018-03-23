import java.awt.Point;
import java.util.LinkedList;

public class SnakeObj {
	private LinkedList<Point> body;
	private Point head;
	private Point tail;
	private Direction direction;
	
	
	
	public SnakeObj(){
		
	}
	public Point getHead(){
		return body.getFirst();
	}
	
	public void move(Direction direction){
		Point curHead = snake.getHead();
		switch(direction){
		case UP: 
			
		}
	}
	
	public void eatFruit(){
		
	}
	
	public void checkCollision(){
		
	}
	
	
}
