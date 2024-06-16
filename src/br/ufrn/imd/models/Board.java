package br.ufrn.imd.models;

import java.util.Random;

import br.ufrn.imd.dao.BoatDAO;

public class Board {
	private int width;
	private int height;
	private BoatDAO dao;
	private Boat[][] coords;
	
	public Board() {
		 this.width = 10;
		 this.height = 10;
		 this.dao = new BoatDAO();
		 this.coords = new Boat[width][height];
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public BoatDAO getDao() {
		return dao;
	}

	public Boat[][] getCoords() {
		return coords;
	}
	
	public void addBoat(Boat b) {
		Random random = new Random();
		
		int x = random.nextInt(9-b.getWidth()+1);
		int y = random.nextInt(9-b.getHeight()+1);
		while(hasBoatInCoord(x, y) || hasBoatInCoord(x+b.getWidth()-1, y+b.getHeight()-1)){
			x = random.nextInt(9-b.getWidth()+1);
			y = random.nextInt(9-b.getHeight()+1);
		}
		
		b.move(x, y);
		this.dao.addBoat(b);
		
		for(int f = 0; f < b.getWidth(); f++) {
			for(int f2 = 0; f2 < b.getHeight(); f2++) {
				this.coords[x+f][y+f2] = b;
			}
		}
	}
	
	public void remBoat(Boat b) {
		this.dao.remBoat(b);
	}

	public boolean hasBoatInCoord(int x, int y) {
		if(this.coords[x][y] != null) {
			return true;
		}
		return false;
	}
}
