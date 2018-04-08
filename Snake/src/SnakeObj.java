import java.awt.Point;
import java.util.LinkedList;

public class SnakeObj {
	private LinkedList<Point> body;
	private Direction direction;

	public SnakeObj(LinkedList<Point> snake){
		body = snake;
	}
	
	//return current head point of the snake
	public Point getHead(){
		return body.getFirst();
	}
	
	//return current direction of the snake
	public Direction getDirection(){
		return this.direction;
	}
	
	// change direction
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
		return newHead;
	}
	
	// add a new point to the front as the new head of the snake
	public void addHead(Point p){
		this.body.addFirst(p);
	}
	
	// adding point to the tail
	public void addTail(Point p){
		body.addLast(p);
	}
	
	// remove the last point of the snake body
	public void deleteTail(Point p){
		body.removeLast();
	}
	
	public Point getTail(){
		return this.body.peekLast();
	}
	// check if the point is a part of the snake
	public boolean containsSelf(Point p){
		return body.contains(p);
	}
	
	public LinkedList<Point> getSnake(){
		return body;
	}
	
	public void clear(){
		body.clear();
	}
}
