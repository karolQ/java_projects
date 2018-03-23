
public enum Direction {
	UP(0),
	DOWN(1),
	LEFT(2),
	RIGHT(4);
		
	private int directionCode;
	
	Direction(int directionCode){
		this.directionCode = directionCode;
	}
		
	public int directionCode(){
		return directionCode;
	}

}
