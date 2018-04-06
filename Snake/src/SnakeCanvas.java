import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JPanel;

public class SnakeCanvas extends JPanel{
	
	private final int gridHeight;
	private final int gridWidth;
	private int score = 0;
	private String highScore ="";
	public GameWindow gw;
	
	public boolean isInGame = false;
	public boolean isOver = false;
	public boolean isInMenu = true;
	
	
	// record the points on board if has been taken(as part of the snake or walls for future element to be added
	private final boolean[][] status;
	private SnakeObj snake = new SnakeObj(new LinkedList<Point>());
	private Fruit fruit = new Fruit(new Point(4, 4));
	
	public SnakeCanvas(int w, int h){
		this.gridWidth = w;
		this.gridHeight = h;
		status = new boolean[w][h];
		defaultSnake();
		fruit.createFruit();
	}
	
	public SnakeObj defaultSnake(){
		snake.clear();
//		snake = new SnakeObj(new LinkedList<Point>());
		int x = gridWidth/2;
		int y = gridHeight/2;
		// default snake with length of 3 points, and set the status of the points in snake as true -- the points have been taken
		for(int i = 0; i < 3; i++){
			x += 1;
			snake.addTail(new Point(x, y));
			status[x][y] = true;
		}
		snake.setDirection(Direction.UP);
		return snake;
	}
	
	public void changeDirection(Direction d){
		if(snake.getDirection().equals(d))
		{
//			System.out.println(snake.getDirection());
//			System.out.println(d);	
			return;
		}
		
		snake.setDirection(d);
	}
	
	public int getScore(){
		return this.score;
	}
	
	// 1. normal move
	// 2. eat fruit
	// 3. collision situation
	public boolean nextStep(){
		Point tail = snake.getTail();
//		Point head = snake.getHead();
		Point newHead = new Point(snake.move(snake.getDirection()));
		// 3. checking collision
		if(checkCollision(newHead)){
			snake.addHead(newHead);
//			Point tail = snake.move(snake.getDirection());
//			Point newHead = snake.getHead();
			status[newHead.x][newHead.y] = true;
			status[tail.x][tail.y] = false;
			
			// 2. checking if hit fruit
			if(newHead.equals(fruit.getFruit())){
				this.score += 10;
				snake.addTail(tail);
				status[tail.x][tail.y] = true;
				Point newFruit = fruit.createFruit(gridWidth/2, gridHeight);
				fruit.setFruit(newFruit);
//				System.out.println(fruit);
			}
			return true;
		}
		return false;
	}
	
	public boolean checkCollision(Point point){
		int x = point.x;
		int y = point.y;
		
		if(x < 0 || x > (gridWidth-1)){
			isOver = true;
			return false;
		}
		if(y < 0 || y > (gridHeight-1))
		{
			isOver = true;
			return false;
		}
		if(snake.containsSelf(point))
		{
			isOver = true;
			return false;
		}
		return true;
	}
	
	public SnakeObj getSnake(){
		return snake;
	}
	
	public Fruit getFruit(){
		return fruit;
	}
	
	public int getWidht(){
		return gridWidth;
	}
	
	public int getHeight(){
		return gridHeight;
	}
	
}
