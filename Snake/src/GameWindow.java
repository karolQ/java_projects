import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JPanel{
	private final static int SCALE = 13;
	private SnakeCanvas view;
	private JPanel window;
	private GameListener gl;
	private int score;
	
	public GameWindow(SnakeCanvas view){
		this.view = view;
//		this.gl = gl;
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
				  score = view.getScore();
				  drawScore(graphics,score);
				  drawGrid(graphics);
				  drawSnake(graphics, view.getSnake());
				  drawFruit(graphics, view.getFruit());
			  }
		};
		gl = new GameListener(this.view);
		this.window.addKeyListener(this.gl);
		this.window.setFocusable(true);
		//this.window.requestFocusInWindow();
		
	}
	
	
	public void draw() {
        Graphics graphics = window.getGraphics();
        drawSnake(graphics, view.getSnake());
        drawFruit(graphics, view.getFruit());
        window.repaint();
    }
	
	
	public void drawBg(Graphics g){
		g.setColor(new Color(153, 203, 152));;
		g.fillRect(10, 10, SCALE*view.getWidht(), SCALE*view.getHeight()+100);
	}
	
	public void drawGrid(Graphics g){
		g.setColor(Color.GRAY);
		//draw grids' vertical lines
		for(int x = 10; x < SCALE*view.getWidht()+15; x+=SCALE){
			g.drawLine(x, 110, x, SCALE*view.getHeight()+110);
		}
		
		for(int y = 110; y < SCALE*view.getHeight()+120; y+= SCALE){
			g.drawLine(10, y, SCALE*view.getWidht()+10, y);
		}
	}
	
	public void drawSnake(Graphics g, SnakeObj snake){
		
		for(Point p : snake.getSnake()){
			g.setColor(Color.GRAY);
			g.drawRect(p.x*SCALE+10, p.y*SCALE+110, SCALE, SCALE);
			g.setColor(Color.BLACK);
			g.fillRoundRect(p.x*SCALE+10, p.y*SCALE+110, SCALE, SCALE, 5, 5);
		}
	}
	
	public void drawFruit(Graphics g, Fruit point){
		g.setColor(Color.RED);
		g.fillOval(point.getFruit().x*SCALE+10, point.getFruit().y*SCALE+110, SCALE, SCALE);
	}
	
	public void drawScore(Graphics g, int score){
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(score), 30, 60);
//		g.setColor(Color.GRAY);
		for(int i = 85; i < 93; i+= 2){
			g.drawLine(10, i, SCALE*view.getWidht()+10, i);
		}
	}
	
	public void gameOver() {
        JOptionPane.showMessageDialog(null, "Game Over", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

