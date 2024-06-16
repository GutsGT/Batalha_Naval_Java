package br.ufrn.imd.models;

public class Player {
	private String name;
	private Board board;
	
	public Player() {
		this.board = new Board();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
