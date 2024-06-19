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
	
	public Boat getBoat(int index) {
		return this.boats.get(index);
	}
	
	/**
	 * Retorna quantos barcos há na lista.
	 * @return int
	 */
	public int getSize() {
		return this.boats.size();
	}
	
}
