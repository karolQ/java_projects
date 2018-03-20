
public class Direction {
	private static final int NO_DIRECTON = 0;
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	private static int direction;
	
	public static void setDirection(int directionCode){
		direction = directionCode;
	}
	
	public int getDirection(){
		return direction;
	}
}
