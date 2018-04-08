import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

public class GameWindow extends JPanel{
	private final static int SCALE = 13;
	private SnakeCanvas view;
	private JPanel window;
	private GameListener gl;
	private int score;
	private String highScore = "";
//	private GameController gc;
	
	
	public GameWindow(SnakeCanvas view){
		this.view = view;
	}
	
	public JPanel getWindow(){
		return this.window;
	}
	
	// initialize the game window and paint the window
	public void init(){
		this.window = new JPanel(){
			  @Override
			  public void paintComponent(Graphics graphics) {
				  super.paintComponent(graphics);
				  drawBg(graphics);
				  drawScore(graphics,score);
				  if(view.isInMenu){
					  drawWelcome(graphics);
				  }
				  else if(view.isPause){
					  drawPause(graphics);
				  }
				  else{
					  drawGrid(graphics);
					  drawSnake(graphics, view.getSnake());
					  drawFruit(graphics, view.getFruit());
				  }
			  }
		};
		
		gl = new GameListener(this.view);
		this.window.addKeyListener(this.gl);
		this.window.setFocusable(true);
	}
	
	public void drawWelcome(Graphics g){
		g.drawString("Please press ENTER to start ...", 90, 330);
	}
	
	private void drawPause(Graphics g) {
		g.drawString("Please press ENTER to continue ...", 90, 330);
		
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
		//draw grids' horizontal lines
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
		g.drawString(String.valueOf(view.getScore()), 30, 80);
		
		if(highScore.equals("")){
			highScore = getHighScore();
		  }
		g.drawString(highScore, 30, 45);
		
		for(int i = 85; i < 93; i+= 2){
			g.drawLine(10, i, SCALE*view.getWidht()+10, i);
		}
		
	}
	
	public String getHighScore(){
		FileReader readFile = null;
		BufferedReader reader = null;
		try{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch(Exception e){
			return "John Doe:0";
		}
		finally{
			try{
				if(reader != null)
					reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void refreshHighScore(){
		FileReader readFile = null;
		BufferedReader reader = null;
		try{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			this.highScore = reader.readLine();
		}
		catch(Exception e){
			reader = new BufferedReader(readFile);
			this.highScore = "John Doe:0";
		}
		finally{
			try{
				if(reader != null)
					reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

