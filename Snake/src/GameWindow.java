import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JPanel{
	private final static int SCALE = 13;
	private final SnakeCanvas view;
	private JPanel window;
	private GameListener gl;
	
	public GameWindow(SnakeCanvas view, GameListener gl){
		this.view = view;
		this.gl = gl;
	}
	
	public JPanel getWindow(){
		return this.window;
	}
	
	public void init(){
		this.window = new JPanel(){
			  @Override
			  public void paintComponent(Graphics graphics) {
				  super.paintComponent(graphics);
				  drawBg(graphics);
				  drawGrid(graphics);
				  drawSnake(graphics, view.getSnake());
				  drawFruit(graphics, view.getFruit());
			  }
		};
		
		this.window.addKeyListener(this.gl);
		
		this.window.setFocusable(true);
		this.window.requestFocusInWindow();
		
	}
	
	public void draw() {
        Graphics graphics = window.getGraphics();
        drawSnake(graphics, view.getSnake());
        drawFruit(graphics, view.getFruit());
        window.repaint();
    }
	
	
	public void drawBg(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCALE*view.getWidht()+20, SCALE*view.getHeight()+20);
	}
	
	public void drawGrid(Graphics g){
		g.setColor(Color.DARK_GRAY);
		//draw grids' vertical lines
		for(int x = SCALE; x < SCALE*view.getWidht()+10; x+=SCALE){
			g.drawLine(x, 0, x, SCALE*view.getHeight());
		}
		
		for(int y = SCALE; y < SCALE*view.getHeight()+10; y += SCALE){
			g.drawLine(0, y, SCALE*view.getWidht(), y);
		}
	}
	
	public void drawSnake(Graphics g, SnakeObj snake){
		g.setColor(Color.GREEN);
		for(Point p : snake.getSnake()){
			g.fillRect(p.x*SCALE, p.y*SCALE, SCALE, SCALE);
		}
	}
	
	public void drawFruit(Graphics g, Fruit point){
		g.setColor(Color.RED);
		g.fillOval(point.getFruit().x*SCALE, point.getFruit().y*SCALE, SCALE, SCALE);
	}
	
	public void gameOver() {
        JOptionPane.showMessageDialog(null, "Game Over", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

