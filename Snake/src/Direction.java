
public enum Direction {
	NONE(0),
	UP(1),
	DOWN(2),
	LEFT(3),
	RIGHT(4);
		
	private int directionCode;
	
	Direction(int directionCode){
		this.directionCode = directionCode;
	}
		
	public int directionCode(){
		return directionCode;
	}

}
