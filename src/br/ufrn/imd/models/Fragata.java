package br.ufrn.imd.models;

public class Fragata extends Boat{
	public Fragata() {
		this.setWidth(4);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
