//import java.awt.Canvas;

import java.awt.Graphics;

import javax.swing.JPanel;

public class SnakeCanvas extends JPanel{
	
	private final static int SCALE = 13;
	private final static int GRID_HEIGHT = 30;
	private final static int GRID_WIDTH = 30;
	
	public static int getScale(){
		return SCALE;
	}
	public static int gridWidth(){
		return GRID_WIDTH;
	}
	public static int gridHeight(){
		return GRID_HEIGHT;
	}
	
	public void drawGrid(Graphics g){
		g.drawRect(10, 10, SCALE*GRID_WIDTH, SCALE*GRID_HEIGHT);
		for(int i = 10; i < SCALE*GRID_WIDTH; i+=SCALE)
			g.drawLine(i, 10, i, SCALE*GRID_HEIGHT);
		for(int j = 10; j < SCALE*GRID_WIDTH; j += SCALE)
			g.drawLine(10, j, SCALE*GRID_WIDTH, j);
	}
	public SnakeCanvas(){
		
	}
	
	public void paint(Graphics g){
		drawGrid(g);
		
	}
	
	public void update(Graphics g){
		
	}

}
