package br.ufrn.imd.views;

import br.ufrn.imd.models.Board;
import br.ufrn.imd.models.Boat;
import br.ufrn.imd.models.Corveta;
import br.ufrn.imd.models.Destroyer;
import br.ufrn.imd.models.Fragata;
import br.ufrn.imd.models.Submarino;

public abstract class BoardView {
	/**
	 * 
	 * @param board Tabuleiro a imprimir
	 */
	public static void printBoard(Board board) {
		System.out.print("X\\Y");
		for(int f = 0; f < board.getWidth(); f++) {
			System.out.print("  "+f+" ");
		}
		System.out.println();
		System.out.print("   ");
		for(int f = 0; f < board.getWidth(); f++) {
			System.out.print("----");
		}
		Boat[][] coords = board.getCoords();
		for(int f = 0; f < coords.length; f++){
			System.out.println("-");
			System.out.print("  "+f);
			for(int f2 = 0; f2 < coords[f].length; f2++) {
				if(board.getShotCoord(f, f2)) {
					System.out.print("|xxx");
				}else if(coords[f][f2] == null) {
					System.out.print("|   ");
				}else {
					if(coords[f][f2] instanceof Corveta){
						System.out.print("| c ");						
					}
					if(coords[f][f2] instanceof Submarino) {
						System.out.print("| s ");						
					}
					if(coords[f][f2] instanceof Fragata) {
						System.out.print("| f ");						
					}
					if(coords[f][f2] instanceof Destroyer) {
						System.out.print("| d ");						
					}
				}
			}
			System.out.println("|");
			System.out.print("   ");
			for(int f2 = 0; f2 < board.getWidth(); f2++) {
				System.out.print("----");
			}
		}
		System.out.println("-");
	}
}
