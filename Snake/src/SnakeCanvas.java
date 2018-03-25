import java.awt.Point;
import javax.swing.JPanel;

public class SnakeCanvas extends JPanel{
	
	private final int GRID_HEIGHT = 30;
	private final int GRID_WIDTH = 30;
	private final boolean[][] status = new boolean[GRID_WIDTH][GRID_HEIGHT];
	
	private SnakeObj snake;
	private Fruit fruit;
	
	public SnakeCanvas(){
		defaultSnake();
		fruit.createFruit();
	}
	
	public SnakeObj defaultSnake(){
		snake = new SnakeObj();
		int x = GRID_WIDTH/2;
		int y = GRID_HEIGHT/2;
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
	
	public void nextStep(){
		Point head = snake.getHead();
		if(checkCollision(head)){
			Point tail = snake.move(snake.getDirection());
			if(snake.getHead().equals(fruit))
				snake.addTail(tail);
			fruit.createFruit(GRID_WIDTH/2, GRID_HEIGHT);
		}
	}
	
	private boolean checkCollision(Point point){
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
	
	
}
