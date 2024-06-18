package br.ufrn.imd.views;

import java.util.Scanner;

import br.ufrn.imd.models.Boat;
import br.ufrn.imd.models.Corveta;
import br.ufrn.imd.models.Destroyer;
import br.ufrn.imd.models.Fragata;
import br.ufrn.imd.models.Player;
import br.ufrn.imd.models.Submarino;

public class Main {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Player player1 = new Player();
		System.out.print("Nome do Player 1: ");
		player1.setName(input.nextLine());
		Corveta corveta1 = new Corveta();
		player1.getBoard().addBoat(corveta1);
		Submarino submarino1 = new Submarino();
		player1.getBoard().addBoat(submarino1);
		Fragata fragata1 = new Fragata();
		player1.getBoard().addBoat(fragata1);
		Destroyer destroyer1 = new Destroyer();
		player1.getBoard().addBoat(destroyer1);
		
		Player player2 = new Player();
		System.out.print("Nome do Player 2: ");
		player2.setName(input.nextLine());
		Corveta corveta2 = new Corveta();
		player2.getBoard().addBoat(corveta2);
		Submarino submarino2 = new Submarino();
		player2.getBoard().addBoat(submarino2);
		Fragata fragata2 = new Fragata();
		player2.getBoard().addBoat(fragata2);
		Destroyer destroyer2 = new Destroyer();
		player2.getBoard().addBoat(destroyer2);
		
		System.out.println("Player "+player1.getName());
		mainMenu(input, player1);
		System.out.println("Player "+player2.getName());
		mainMenu(input, player2);
		
		
		int turno = 1; //1: Player1; 2: Player2; 3: Fim de jogo.
		
		int[] shootCoords = null;
		while(turno < 3){
			if(turno == 1) {
				turno = battleMenu(input, player1, shootCoords)? 2: 4;
			}else if(turno == 2) {
				turno = battleMenu(input, player1, shootCoords)? 1: 3;
			}
		}
		
		input.close();
	}
	
	
	public static void mainMenu(Scanner input, Player player){
		
		int x = -1;
		int y = -1;
		int menuOpt = 0;
		
		while(menuOpt != 3) {
			try {
				BoardView.printBoard(player.getBoard());
				System.out.println(
					"--Selecione uma opção:\n"
					+"1 - Mover barco.\n"
					+"2 - Rotacionar barco.\n"
					+"3 - Finalizar.\n"
				);
				menuOpt = input.nextInt();
				
				int boatOpt = 0;
				Boat selectedBoat = null;
				switch(menuOpt) {
					case 1:				//Mover barco.
						while(selectedBoat == null){
							try {
								System.out.println(
									"--Selecione um navio para mover:\n"
									+"1 - Corveta\n"
									+"2 - Submarino\n"
									+"3 - Fragata\n"
									+"4 - Destroyer\n"
								);
								boatOpt = input.nextInt();
								
								if(boatOpt > 0 && boatOpt < 5) {
									selectedBoat = player.getBoard().getDao().getBoat(boatOpt-1);
								}else{
									System.out.println("Opção inválida.");
								}
								
							}catch(Exception e){
								System.out.println(e.getMessage());
							}
						}
						
						System.out.print("X: ");
						x = input.nextInt();
						System.out.print("Y: ");
						y = input.nextInt();
						player.getBoard().moveBoat(selectedBoat, x, y);
					break;
					case 2:				//Rotacionar barco.
						while(selectedBoat == null){
							try {
								System.out.println(
									"--Selecione um navio para rotacionar:\n"
									+"1 - Corveta\n"
									+"2 - Submarino\n"
									+"3 - Fragata\n"
									+"4 - Destroyer\n"
								);
								boatOpt = input.nextInt();
								
								if(boatOpt > 0 && boatOpt < 5) {
									selectedBoat = player.getBoard().getDao().getBoat(boatOpt-1);
								}else{
									System.out.println("Opção inválida.");
								}
								
							}catch(Exception e){
								System.out.println(e.getMessage());
							}
						}
						player.getBoard().rotateBoat(selectedBoat);
					break;
					case 3:				//Finalizar
						System.out.println("Finalizando...");
					break;
					default:
						System.out.println("Opção inválida.");
					break;
				}
				System.out.println("\n\n\n\n");
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("\n\n\n\n");
			}
		}
	}
	
	public static boolean battleMenu(Scanner input, Player player, int[] prevShootCoords) {
		
		if(prevShootCoords != null && player.getBoard().getCoords()[prevShootCoords[0]][prevShootCoords[1]] instanceof Boat) {
			Boat boatFound = player.getBoard().getCoords()[prevShootCoords[0]][prevShootCoords[1]];
			int partNum = getPartNum(boatFound, player.getBoard().getCoords(), prevShootCoords[0], prevShootCoords[1]);
			boatFound.destroyPart(partNum);
		}
		
		System.out.println("Informe as coordenadas para atirar.");
		System.out.print("X: ");
		int x = input.nextInt();
		System.out.print("Y: ");
		int y = input.nextInt();
		
		return true;
	}
	
	public static int getPartNum(Boat boatFound, Boat[][] coords, int x, int y) {
		int partNum = 1;
		if(boatFound.getIsHorizontal()) {
			while(y-partNum > 0 && coords[x][y-partNum] == boatFound){
				partNum++;
			}
		}else{
			while(x-partNum > 0 && coords[x-partNum][y] == boatFound){
				partNum++;
			}
		}
		
		return partNum;
	}
}
