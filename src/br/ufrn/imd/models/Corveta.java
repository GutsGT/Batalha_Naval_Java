package br.ufrn.imd.models;

/**
 * @author Otávio Augusto
 * @version 1.0
 * 
 */


public class Corveta extends Boat{
	public Corveta() {
		this.setWidth(2);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
