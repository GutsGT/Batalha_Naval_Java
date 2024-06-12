package br.ufrn.imd.dao;

import java.util.ArrayList;
import br.ufrn.imd.models.Boat;

public class BoatDAO {
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	
	public void addBoat(Boat b) {
		this.boats.add(b);
	}
	
	public void remBoat(Boat b) {
		this.boats.remove(b);
	}
	
	public boolean hasBoatInCoord(int x, int y) {
		for(Boat boat: this.boats) {
			if(x >= boat.getXPosition() && x < boat.getXPosition()+boat.getWidth()
				&& y >= boat.getYPosition() && y < boat.getYPosition()+boat.getHeight()
			){
				return true;
			}
		}
		return false;
	}
}
