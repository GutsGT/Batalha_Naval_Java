package br.ufrn.imd.models;

public class Corveta extends Boat{
	public Corveta() {
		this.setWidth(2);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
