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
		System.out.println("\n\n\n\n");
		System.out.println("Player "+player2.getName());
		mainMenu(input, player2);
		System.out.println("\n\n\n\n");
		
		
		int turno = 1; //1: Player1; 2: Player2; 3: Fim de jogo.
		
		int[] shootCoords = new int[]{-1,-1};
		while(turno < 3){
			if(turno == 1) {
				shootCoords = battleMenu(input, player1, shootCoords);
				if(player2.getBoard().hasBeenDefeated()) {
					turno = 3;
					continue;
				}else {
					turno = 2;					
				}
			}else if(turno == 2) {
				shootCoords = battleMenu(input, player2, shootCoords);
				if(player1.getBoard().hasBeenDefeated()) {
					turno = 4;
					continue;
				}else {
					turno = 1;
				}
			}
			System.out.println("\n\n\n\n\n\n\n");
		}
		
		if(turno == 3) {
			System.out.println("PLAYER "+player1.getName()+" VENCEU!!!!");
		}else if(turno == 4) {
			System.out.println("PLAYER "+player2.getName()+" VENCEU!!!!");
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
	
	public static int[] battleMenu(Scanner input, Player player, int[] prevShootCoords) {
		if(prevShootCoords[0] != -1) {
			if(player.getBoard().getCoords()[prevShootCoords[0]][prevShootCoords[1]] instanceof Boat) {
				Boat boatFound = player.getBoard().getCoords()[prevShootCoords[0]][prevShootCoords[1]];
				int partNum = getPartNum(boatFound, player.getBoard().getCoords(), prevShootCoords[0], prevShootCoords[1]);
				boatFound.destroyPart(partNum-1);
				boolean[] dp = boatFound.getDestroyedParts();
			}
			player.getBoard().setShotCoord(prevShootCoords[0], prevShootCoords[1]);
		}
		System.out.println("TURNO DE: "+player.getName());
		BoardView.printBoard(player.getBoard());
		
		int x = -1;
		int y = -1;
		
		System.out.println("Informe as coordenadas para atirar.");
		System.out.print("X: ");
		x = input.nextInt();
		System.out.print("Y: ");
		y = input.nextInt();
		
		return new int[] {x,y};
	}
	
	public static int getPartNum(Boat boatFound, Boat[][] coords, int x, int y) {
		int partNum = 1;
		if(boatFound.getIsHorizontal()) {
			while(y-partNum >= 0 && coords[x][y-partNum] == boatFound){
				partNum++;
			}
		}else{
			while(x-partNum >= 0 && coords[x-partNum][y] == boatFound){
				partNum++;
			}
		}
		
		return partNum;
	}
}
