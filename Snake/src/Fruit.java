import java.awt.Point;
import java.util.*;
import java.io.*;
import java.util.Random;

public class Fruit {
	private Point fruit;
	
	// constructor
	public Fruit(int x, int y){
		this.fruit.x = x;
		this.fruit.y = y;
	}
	
	// for creating fruits
	public void setFruit(int x, int y){
		fruit.x = x;
		fruit.y = y;
	}
	
	public Point getFruit(){
		return fruit;
	}
	
	//default way of creating fruit
	public Point createFruit(){
		Random rand = new Random();
		int randomX = rand.nextInt(250);
		int randomY = rand.nextInt(250);
		Point randomPoint = new Point(randomX, randomY);
		return randomPoint;
	}
	
	// creating fruits with two seeds
	public Point createFruit(int x, int y){
		Random rand = new Random();
		int randomX = rand.nextInt(x);
		int randomY = rand.nextInt(y);
		Point randomPoint = new Point(randomX, randomY);
		return randomPoint;
	}
	
}
