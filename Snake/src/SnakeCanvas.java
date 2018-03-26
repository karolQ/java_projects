import java.awt.Point;
import javax.swing.JPanel;

public class SnakeCanvas extends JPanel{
	
	private final int GRID_HEIGHT = 30;
	private final int GRID_WIDTH = 30;
	
	// record the points on board if has been taken(as part of the snake or walls for future element to be added
	private final boolean[][] status = new boolean[GRID_WIDTH][GRID_HEIGHT];
	
	private SnakeObj snake;
	private Fruit fruit;
	
	public SnakeCanvas(){
		if(snake.getSnake() == null){
			defaultSnake();
			fruit.createFruit();
		}
	}
	
	public SnakeObj defaultSnake(){
		snake = new SnakeObj();
		int x = GRID_WIDTH/2;
		int y = GRID_HEIGHT/2;
		// default snake with length of 3 points, and set the status of the points in snake as true -- the points have been taken
		for(int i = 0; i < 4; i++){
			x += 1;
			snake.addTail(new Point(x, y));
			status[x][y] = true;
		}
		return snake;
	}
	
	public void changeDirection(Direction d){
		if(snake.getDirection().equals(d))
			return;
		snake.setDirection(d);
	}
	
	// 1. normal move
	// 2. eat fruit
	// 3. collision situation
	public void nextStep(){
		Point head = snake.getHead();
		// 3. checking collision
		if(checkCollision(head)){
			Point tail = snake.move(snake.getDirection());
			Point newHead = snake.getHead();
			status[newHead.x][newHead.y] = true;
			status[tail.x][tail.y] = false;
			// 2. checking if hit fruit
			if(snake.getHead().equals(fruit)){
				snake.addTail(tail);
				status[tail.x][tail.y] = true;
			}
			fruit.createFruit(GRID_WIDTH/2, GRID_HEIGHT);
		}
	}
	
	public boolean checkCollision(Point point){
		int x = point.x;
		int y = point.y;
		
		if(x < 0 || x > (GRID_WIDTH-1))
			return false;
		if(y < 0 || y > (GRID_HEIGHT-1))
			return false;
		if(snake.containsSelf(point))
			return false;
		return true;
	}
	
	public SnakeObj getSnake(){
		return snake;
	}
	
	public Fruit getFruit(){
		return fruit;
	}
	
	public int getWidht(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
}
