import java.awt.Point;
import java.util.LinkedList;

public class SnakeObj {
	private LinkedList<Point> body;
	private Direction direction;
	//private Fruit fruit;
	
	//
	public SnakeObj(){
		
	}
	
	//return current head point of the snake
	public Point getHead(){
		return body.getFirst();
	}
	
	//return current direction of the snake
	public Direction getDirection(){
		return this.direction;
	}
	
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	// move
	public Point move(Direction dir){
		Point curHead = this.getHead();
		Point newHead = null;
		switch(dir){
		case UP: 
			newHead = new Point(curHead.x, curHead.y-1);
			break;
		case DOWN:
			newHead = new Point(curHead.x, curHead.y+1);
			break;
		case LEFT:
			newHead = new Point(curHead.x-1, curHead.y);
			break;
		case RIGHT:
			newHead = new Point(curHead.x+1, curHead.y);
			break;
		default:
			break;
		}
		
		body.addFirst(newHead);
		return body.pollLast();
	}
	
	// adding point to the tail
	public Point addTail(Point p){
		body.addLast(p);
		return p;
	}
	
	// check if the point is a part of the snake
	public boolean containsSelf(Point p){
		return body.contains(p);
	}
	
	public LinkedList<Point> getSnake(){
		return body;
	}
}
