package br.ufrn.imd.models;

public class Submarino extends Boat{
	public Submarino() {
		this.setWidth(3);
		this.setHeight(1);
		this.shootRangeLevel = 1;
		
		this.setDestroyedParts();
	}
}
