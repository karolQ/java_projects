import java.awt.Point;
import java.util.*;
import java.io.*;
import java.util.Random;

public class Fruit {
	private Point fruit;
	
	public Fruit(Point p){
		this.fruit = p;
	}
	public void setFruit(int x, int y){
		fruit.x = x;
		fruit.y = y;
	}
	
	public Point getFruit(){
		return fruit;
	}
	public Point createFruit(){
		Random rand = new Random();
		int randomX = rand.nextInt(SnakeCanvas.gridWidth()*SnakeCanvas.getScale());
		int randomY = rand.nextInt(SnakeCanvas.gridHeight()*SnakeCanvas.getScale());
		Point randomPoint = new Point(randomX, randomY);
		return randomPoint;
	}
	
}
