package br.ufrn.imd.views;

import br.ufrn.imd.models.Corveta;
import br.ufrn.imd.models.Destroyer;
import br.ufrn.imd.models.Fragata;
import br.ufrn.imd.models.Player;
import br.ufrn.imd.models.Submarino;

public class Teste {
	public static void main(String[] args) {
		
		Player player1 = new Player();
		
		Fragata fragata1 = new Fragata();
		player1.getBoard().addBoat(fragata1);
		
		Submarino submarino1 = new Submarino();
		player1.getBoard().addBoat(submarino1);
		
		Corveta corveta2 = new Corveta();
		player1.getBoard().addBoat(corveta2);
		
		Destroyer destroyer1 = new Destroyer();
		player1.getBoard().addBoat(destroyer1);
		
		BoardView.printBoard(player1.getBoard());
		
	}
}
