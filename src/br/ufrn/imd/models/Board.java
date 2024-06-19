package br.ufrn.imd.models;

import java.util.Random;

import br.ufrn.imd.dao.BoatDAO;

public class Board{
	private int width;
	private int height;
	private BoatDAO dao;
	private Boat[][] coords;
	private boolean[][] shotCoords;
	
	public Board() {
		 this.width = 10;
		 this.height = 10;
		 this.dao = new BoatDAO();
		 this.coords = new Boat[width][height];
		 
		 this.shotCoords = new boolean[width][height];
		 
		 for(int f = 0; f < width; f++) {
			 for(int f2 = 0; f2 < height; f2++) {
				 this.shotCoords[f][f2] = false;
			 }
		 }
	}
	
	//Getters and Setters{
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * Pega a lista de barcos do tabuleiro.
	 * @return BoatDAO
	 */
	public BoatDAO getDao() {
		return dao;
	}
	
	/**
	 * Pega a matriz que representa o tabuleiro.
	 * @return Boat[][]
	 */
	public Boat[][] getCoords() {
		return coords;
	}
	
	/**
	 * Confere se a coordenada já foi atingida.
	 * @param x Linha do tabuleiro
	 * @param y Coluna do tabuleiro
	 * @return boolean
	 */
	public boolean getShotCoord(int x, int y) {
		return this.shotCoords[x][y];
	}
	/**
	 * Define que a coordenada foi atingida.
	 * @param x Linha do tabuleiro
	 * @param y Coluna do tabuleiro
	 */
	public void setShotCoord(int x, int y) {
		this.shotCoords[x][y] = true;
	}
	//}
	
	/**
	 * Adiciona um barco a um local aleatório do tabuleiro.
	 * @param b Barco a adicionar
	 */
	public void addBoat(Boat b) {
		Random random = new Random();
		
		int x = -1;
		int y = -1;
		boolean hasBoat;
		do{
			hasBoat = false;
			x = random.nextInt(9-b.getHeight()+1);
			y = random.nextInt(9-b.getWidth()+1);
			for(int f = y; f < y+b.getWidth(); f++) {
				if(this.coords[x][f] instanceof Boat) {
					hasBoat = true;
					break;
				}
			}
		}while(hasBoat);
		
		this.dao.addBoat(b);
		
		for(int f = 0; f < b.getHeight(); f++) {
			for(int f2 = 0; f2 < b.getWidth(); f2++) {
				this.coords[x+f][y+f2] = b;
			}
		}
	}
	
	/**
	 * Movimenta o barco indicado para as coordenadas [x,y], lança uma Exception caso não seja permitido.
	 * @param boat Barco a mover
	 * @param x Linha do tabuleiro
	 * @param y Coluna do tabuleiro
	 * @throws Exception Caso não seja possível mover
	 */
	public void moveBoat(Boat boat, int x, int y) throws Exception{
		if(x < 0 || y < 0 
				 || x > this.getHeight()-1 || y > this.getWidth()-1 
				 || x+boat.getHeight() > this.height|| y+boat.getWidth() > this.width
		){
			throw new Exception("Fora do tabuleiro.");
		}
		
		canMoveTo(boat, x, y);
		
		int[] boatPosition = null;
		for(int f = 0; f < this.getHeight(); f++) {
			for(int f2 = 0; f2 < this.getWidth(); f2++) {
				if(this.coords[f][f2] == boat) {
					boatPosition = new int[] {f, f2};
					break;
				}
			}
			if(boatPosition != null) {
				break;
			}
		}
		
		if(boat.getIsHorizontal()) {
			for(int f = 0; f < boat.getWidth(); f++) {
				this.coords[boatPosition[0]][boatPosition[1]+f] = null;
			}
			for(int f = 0; f < boat.getWidth(); f++) {
				this.coords[x][y+f] = boat;
			}
		}else{
			for(int f = 0; f < boat.getHeight(); f++) {
				this.coords[boatPosition[0]+f][boatPosition[1]] = null;
			}
			for(int f = 0; f < boat.getHeight(); f++) {
				this.coords[x+f][y] = boat;
			}
		}
	}
	
	
	/**
	 * Rotaciona o barco utilizando o canto superior esquerdo como âncora, lança uma Exception caso não seja permitido.
	 * @param boat Barco a rotacionar
	 * @throws Exception Caso não seja possível rotacionar 
	 */
	public void rotateBoat(Boat boat) throws Exception{
		
		int[] boatPosition = null;
		for(int f = 0; f < this.getHeight(); f++) {
			for(int f2 = 0; f2 < this.getWidth(); f2++) {
				if(this.coords[f][f2] == boat) {
					boatPosition = new int[] {f, f2};
					break;
				}
			}
			if(boatPosition != null) {
				break;
			}
		}
		
		if(boat.getIsHorizontal()) {//Virar o navio para a vertical.
			if(boatPosition[0]+boat.getWidth() > this.getHeight()) {
				throw new Exception("Fora do tabuleiro.");
			}
			
			for(int f = boatPosition[1]; f < boatPosition[1]+boat.getWidth(); f++) {
				this.coords[boatPosition[0]][f] = null;
			}
			for(int f = boatPosition[0]; f < boatPosition[0]+boat.getWidth(); f++) {
				this.coords[f][boatPosition[1]] = boat;
			}
		}else{//Virar o navio para a horizontal.
			if(boatPosition[1]+boat.getHeight() > this.getWidth()) {
				throw new Exception("Fora do tabuleiro.");
			}

			for(int f = boatPosition[0]; f < boatPosition[0]+boat.getHeight(); f++) {
				this.coords[f][boatPosition[1]] = null;
			}
			for(int f = boatPosition[1]; f < boatPosition[1]+boat.getHeight(); f++) {
				this.coords[boatPosition[0]][f] = boat;
			}
		}
		
		boat.rotate();
		
	}
	
	/**
	 * Confere se o barco pode se movimentar para as coordenadas [x,y] sem conflitar com outro barco existente.
	 * @param boat Barco que será movido
	 * @param x Linha do tabuleiro
	 * @param y Coluna do tabuleiro
	 * @throws Exception Caso não seja possível mover
	 */
	public void canMoveTo(Boat boat, int x, int y) throws Exception{
		for(int f = x; f < x+boat.getHeight(); f++) {
			for(int f2 = y; f2 < y+boat.getWidth(); f2++) {
				if(this.coords[f][f2] instanceof Boat && this.coords[f][f2] != boat) {
					throw new Exception("Navio encontrado em: ["+f+", "+f2+"], não é possível movimentar.");
				}
			}
		}
	}
	
	/**
	 * Confere se todas as partes de todos os barcos foram destruídos para retornar se o jogador foi derrotado. 
	 * @return boolean
	 */
	public boolean hasBeenDefeated() {
		Boat boat;
		for(int f = 0; f < this.getDao().getSize(); f++) {
			boat = this.dao.getBoat(f);
			boolean[] dP = boat.getDestroyedParts();
			for(int f2 = 0; f2 < dP.length; f2++) {
				if(dP[f2] == false) {
					return false;
				}
			}
		}
		return true;
	}
}
