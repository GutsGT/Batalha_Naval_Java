package br.ufrn.imd.models;

public class Destroyer extends Boat{
	public Destroyer() {
		this.setWidth(5);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
