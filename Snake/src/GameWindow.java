import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JPanel{
	private final static int SCALE = 13;
	private final SnakeCanvas view;
	private JPanel window;
	private GameController gc;
	
	public GameWindow(SnakeCanvas view){
		this.view = view;
	}
	
	public JPanel getWindow(){
		return window;
	}
	
	
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
	
	public void draw() {
        Graphics graphics = window .getGraphics();
        drawSnake(graphics, view.getSnake());
        drawFruit(graphics, view.getFruit());
        window.repaint();
    }
	
	
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
	
	public void drawFruit(Graphics g, Fruit point){
		g.setColor(Color.RED);
		g.fillOval(point.getFruit().x*SCALE, point.getFruit().y*SCALE, SCALE, SCALE);
	}
	
	public void gameOver() {
        JOptionPane.showMessageDialog(null, "Game Over", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

