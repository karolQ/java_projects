import java.awt.Point;
import java.util.LinkedList;

public class SnakeObj {
	private LinkedList<Point> body;
	private Point curHead;
	//private Point tail;
	private Direction direction;
	private Fruit fruit;
	
	//default way of creating a snake
	public SnakeObj(){
		body = new LinkedList<Point>();
		body.add(new Point(0,2));
		body.add(new Point(0,1));
		body.add(new Point(0,0));
		direction = Direction.NONE;
	}
	
	//return current head point of the snake
	public Point getHead(){
		return body.getFirst();
	}
	
	//return current direction of the snake
	public Direction getDirection(){
		return this.direction;
	}
	
	//movement of the snake, including the eat fruit situation -- adding the newHead no matter what,
	//and if newHead is the fruit, no need to remove the tail, otherwise, remove the tail.
	public void move(Direction dir){
		curHead = this.getHead();
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
		
		if(newHead.equals(fruit)){
			fruit.createFruit();
			return;
		}
		
		body.removeLast();
	}
		
}
