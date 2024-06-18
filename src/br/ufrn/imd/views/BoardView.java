package br.ufrn.imd.views;

import br.ufrn.imd.models.Board;
import br.ufrn.imd.models.Boat;
import br.ufrn.imd.models.Corveta;
import br.ufrn.imd.models.Destroyer;
import br.ufrn.imd.models.Fragata;
import br.ufrn.imd.models.Submarino;

public abstract class BoardView {
	public static void printBoard(Board board) {
		System.out.print(" ");
		for(int f = 0; f < board.getWidth(); f++) {
			System.out.print("  "+f+" ");
		}
		System.out.println();
		System.out.print(" ");
		for(int f = 0; f < board.getWidth(); f++) {
			System.out.print("----");
		}
		int rowIndex = 0;
		int partNumber = 0;
		for(Boat[] row: board.getCoords()) {
			System.out.println("-");
			System.out.print(rowIndex);
			rowIndex++;
			for(Boat coord: row) {
				if(coord == null) {
					System.out.print("|   ");
				}else {
					
					if(coord instanceof Corveta){
						System.out.print("| c ");						
					}
					if(coord instanceof Submarino) {
						System.out.print("| s ");						
					}
					if(coord instanceof Fragata) {
						System.out.print("| f ");						
					}
					if(coord instanceof Destroyer) {
						System.out.print("| d ");						
					}
				}
			}
			System.out.println("|");
			System.out.print(" ");
			for(int f = 0; f < board.getWidth(); f++) {
				System.out.print("----");
			}
		}
		System.out.println("-");
	}
}
