package br.ufrn.imd.models;

/**
 * @author Otávio Augusto
 * @version 1.0
 * 
 */


public class Submarino extends Boat{
	public Submarino() {
		this.setWidth(3);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
