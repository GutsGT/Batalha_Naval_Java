package br.ufrn.imd.models;

/**
 * @author Otávio Augusto
 * @version 1.0
 * 
 */


public abstract class Boat implements IBoat{
	protected int width;				
	protected int height;				
	private boolean destroyed;		
	protected int shootRangeLevel;	//[1-4]
	private int xPosition;			//Must be between [0, 9-(width-1)], is always the position of the left side of the boat
	private int yPosition;			//Must be between [0, 9-(height-1)], is always the position of the upper side of the boat
	
	public Boat() {
		this.width = 1;
		this.height = 1;
		this.destroyed = false;
		this.xPosition = 0;
		this.yPosition = 0;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public int getXPosition() {
		return xPosition;
	}
	public int getYPosition() {
		return yPosition;
	}

	
	public void destroy() {
		this.destroyed = true;
	}
	
	public void rotate() {
		int val = this.width;
		this.width = this.height;
		this.height = val;
	}
	
	public void move(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int[] shootIn(int x, int y) {
		int[] res = new int[3];
		res[0] = x;
		res[1] = y;
		res[2] = this.shootRangeLevel;
		return res;
	}	
}
