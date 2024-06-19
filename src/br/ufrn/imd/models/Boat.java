package br.ufrn.imd.models;

import java.util.ArrayList;

public abstract class Boat implements IBoat{
	private int width;		
	private int height;
	private boolean[] destroyedParts;
	protected int shootRangeLevel;	//[1-4]
	private boolean isHorizontal;
	
	public Boat() {
		this.isHorizontal = true;
	}
	
	public int getWidth() {
		return width;
	}
	protected void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	protected void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Retorna o array booleano indicando quais partes do barco já foram destruídas.
	 * @return boolean[]
	 */
	public boolean[] getDestroyedParts() {
		return this.destroyedParts;
	}
	
	public void setDestroyedParts() {
		this.destroyedParts = new boolean[this.width*this.height];
		for(int f = 0; f < this.width*this.height; f++) {
			this.destroyedParts[f] = false;
		}
	}
	
	/**
	 * Destrói a parte do barco de acordo com o índice
	 */
	public void destroyPart(int index) {
		this.destroyedParts[index] = true;
	}
	
	/**
	 * Informa se o barco está na horizontal ou vertical para simplificar a verificação de onde está o resto do barco.
	 * @return
	 */
	public boolean getIsHorizontal() {
		return this.isHorizontal;
	}
	
	public void rotate() {
		int val = this.width;
		this.width = this.height;
		this.height = val;
		this.isHorizontal = !this.isHorizontal;
	}
	
	public int[] shootIn(int x, int y) {
		int[] res = new int[3];
		res[0] = x;
		res[1] = y;
		res[2] = this.shootRangeLevel;
		return res;
	}
}
