import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class snakeCanvas extends Canvas implements Runnable, KeyListener {
	private final int BOX_HEIGHT = 13;
	private final int BOX_WIDTH = 13;
	private final int GRID_HEIGHT = 30;
	private final int GRID_WIDTH = 30;

	private LinkedList<Point> snake;
	private Point fruit;
	private int direction = Direction.NO_DIRECTION;
	
	private Thread runThread;
	//private Graphics globalGraphics;
	private int score = 0;
	private String highScore = "";
	
	public void init(){
		
	}
	
	public void paint(Graphics g){
		
		if(snake == null){
			snake = new LinkedList<Point>();
			GenerateDefaultSnake();
			PlaceFruit();
		}
		//GenerateDefaultSnake();
		//PlaceFruit();
		
		
		//globalGraphics = g.create();
		
		if(runThread == null){
			this.setPreferredSize(new Dimension(640,480));
			this.addKeyListener(this);
			runThread = new Thread(this);
			runThread.start();
		}
		
		if(highScore.equals("")){
			highScore = this.getHighScore();
		}
		
		DrawSnake(g);
		DrawGrid(g);
		DrawFruit(g);
		DrawScore(g);
	}
	
	public void update(Graphics g){
		Graphics offScreenGraphics;
		BufferedImage offScreen = null;
		Dimension d = this.getSize();
		
		offScreen = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		offScreenGraphics = offScreen.getGraphics();
		offScreenGraphics.setColor(this.getBackground());
		offScreenGraphics.fillRect(0, 0, d.width, d.height);
		offScreenGraphics.setColor(this.getForeground());
		paint(offScreenGraphics);
		
		g.drawImage(offScreen, 0, 0, this);
	}
	
	public void GenerateDefaultSnake(){
		snake.clear();
		score = 0;
		snake.add(new Point(0,2));
		snake.add(new Point(0,1));
		snake.add(new Point(0,0));
		direction = Direction.NO_DIRECTION;
	}
	/*double buffer method to repaint the canvas, now using defaut method repaint to do so
	public void Draw(Graphics g){
		g.clearRect(0, 0, BOX_WIDTH*GRID_WIDTH+10, BOX_HEIGHT*GRID_HEIGHT+20);
		BufferedImage buffer = new BufferedImage(BOX_WIDTH*GRID_WIDTH+10, BOX_HEIGHT*GRID_HEIGHT+20, BufferedImage.TYPE_INT_ARGB); 
		Graphics bufferGraphics = buffer.getGraphics();
		
		
		DrawSnake(bufferGraphics);// when it is double buffer, used bufferGraphics as parameter
		DrawGrid(bufferGraphics);
		DrawFruit(bufferGraphicsg);
		DrawScore(bufferGraphics);
		
		g.drawImage(buffer, 0, 0, BOX_WIDTH*GRID_WIDTH+10, BOX_HEIGHT*GRID_HEIGHT+20, this);
	}
	 */
	public void DrawScore(Graphics g){
		g.drawString("Score: " + score, 0, BOX_HEIGHT*GRID_HEIGHT+10);
		g.drawString("Highscore: " + highScore, 0, BOX_HEIGHT*GRID_HEIGHT+20);
	}
	
	
	public void DrawGrid(Graphics g){
		// draw the outside line of box
		g.drawRect(0, 0, GRID_WIDTH * BOX_WIDTH, GRID_HEIGHT * BOX_HEIGHT);
		//draw grids' vertical lines
		for(int x = BOX_WIDTH; x < GRID_WIDTH*BOX_WIDTH; x+=BOX_WIDTH){
			g.drawLine(x, 0, x, BOX_HEIGHT*GRID_HEIGHT);
		}
		
		for(int y = BOX_HEIGHT; y < BOX_HEIGHT*GRID_HEIGHT; y += BOX_HEIGHT){
			g.drawLine(0, y, BOX_WIDTH*GRID_WIDTH, y);
		}
	}
	
	
	public void DrawSnake(Graphics g){
		g.setColor(Color.GREEN);
		for(Point p : snake){
			g.fillRect(p.x*BOX_WIDTH, p.y*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		}
		g.setColor(Color.BLACK);
	}
	
	public void DrawFruit(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(fruit.x*BOX_WIDTH, fruit.y*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		g.setColor(Color.BLACK);
	}
	
	public void checkScore(){
		if(highScore == "")
			return;
		if(score > Integer.parseInt(highScore.split(":")[1])){
			String name = JOptionPane.showInputDialog("You set a new highscore. Please enter your name: ");
			highScore = name + ":" + score;
			
			File scoreFile = new File("highscore.dat");
			if(!scoreFile.exists())
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try{
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
				
			}
			catch(Exception e){
				
			}
			finally{
				try{
					if(writer != null)
						writer.close();
				}catch(Exception e){
					
				}
			}
		}
	}
	
	public void move(){
		Point head = snake.peekFirst();
		Point newPoint = head;
		switch (direction){
			case Direction.NORTH:
				newPoint = new Point(head.x, head.y-1);
				break;
			case Direction.SOUTH:
				newPoint = new Point(head.x, head.y+1);
				break;
			case Direction.WEST:
				newPoint = new Point(head.x-1, head.y);
				break;
			case Direction.EAST:
				newPoint = new Point(head.x+1, head.y);
				break;
		}
		
		snake.remove(snake.peekLast());
		
		if(newPoint.equals(fruit)){
			//eat fruit
			score += 10;
			Point addPoint = (Point) newPoint.clone();
			
			switch (direction){
			case Direction.NORTH:
				newPoint = new Point(head.x, head.y-1);
				break;
			case Direction.SOUTH:
				newPoint = new Point(head.x, head.y+1);
				break;
			case Direction.WEST:
				newPoint = new Point(head.x-1, head.y);
				break;
			case Direction.EAST:
				newPoint = new Point(head.x+1, head.y);
				break;
			}
			snake.push(addPoint);
			PlaceFruit();
		}
		//checking boundary
		else if(newPoint.x < 0 || newPoint.x > (GRID_WIDTH-1)){
			checkScore();
			GenerateDefaultSnake();
			return;
		}
		
		else if(newPoint.y < 0 || newPoint.y > (GRID_HEIGHT-1)){
			checkScore();
			GenerateDefaultSnake();
			return;
		}
		
		else if(snake.contains(newPoint)){
			// the snake run into itself
			checkScore();
			GenerateDefaultSnake();
			return;
		}
		
		snake.push(newPoint);
		
	}
	
	public void PlaceFruit(){
		Random rand = new Random();
		int randomX = rand.nextInt(GRID_WIDTH);
		int randomY = rand.nextInt(GRID_HEIGHT);
		Point randomPoint = new Point(randomX, randomY);
		while(snake.contains(randomPoint)){
			randomX = rand.nextInt(GRID_WIDTH);
			randomY = rand.nextInt(GRID_HEIGHT);
			randomPoint = new Point(randomX, randomY);
		}
		fruit = randomPoint;
	}

	@Override
	public void run() {
		while(true){
			move();
			//Draw(globalGraphics);
			repaint();
			
			try{
				Thread.currentThread();
				Thread.sleep(150);
			}
			catch(Exception e){
				e.printStackTrace();
			}
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				if(direction != Direction.SOUTH)
					direction = Direction.NORTH;
				break;	
			case KeyEvent.VK_DOWN:
				if(direction != Direction.NORTH)
					direction = Direction.SOUTH;
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != Direction.WEST)
					direction = Direction.EAST;
				break;
			case KeyEvent.VK_LEFT:
				if(direction != Direction.EAST)
					direction = Direction.WEST;
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
