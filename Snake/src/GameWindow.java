import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class GameWindow extends JPanel{
	private final static int SCALE = 13;
	private SnakeCanvas view;
	private JPanel window;
	private GameController controller;
	
	public GameWindow(SnakeCanvas view){
		this.view = view;
	}
	
	public JPanel getWindow(){
		return window;
	}
	
	public void paint(){
		view = new SnakeCanvas();
	}
	
	public void update(){
		
	}
	/*
	public void init(){
		window = new JPanel(){
			  @Override
			  public void paintComponent(Graphics graphics) {
				  drawBg(graphics);
				  drawGrid(graphics);
				  drawSnake(graphics, view.getSnake());
				  drawFruit(graphics, view.getFruit());
			  }
		};
	}
	*/
	
	
	public void drawBg(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, SCALE*view.getWidht(), SCALE*view.getHeight());
	}
	
	public void drawGrid(Graphics g){
		//draw grids' vertical lines
		for(int x = SCALE; x < SCALE*view.getWidht(); x+=SCALE){
			g.drawLine(x, 0, x, SCALE*view.getHeight());
		}
		
		for(int y = SCALE; y < SCALE*view.getHeight(); y += SCALE){
			g.drawLine(0, y, SCALE*view.getWidht(), y);
		}
	}
	
	public void drawSnake(Graphics g, SnakeObj snake){
		g.setColor(Color.GREEN);
		for(Point p : snake.getSnake()){
			g.fillRect(p.x*SCALE, p.y*SCALE, SCALE, SCALE);
		}
	}
	
	public void drawFruit(Graphics g, Fruit fruit){
		g.setColor(Color.RED);
		g.fillOval(fruit.getFruit().x*SCALE, fruit.getFruit().y*SCALE, SCALE, SCALE);
	}
}

