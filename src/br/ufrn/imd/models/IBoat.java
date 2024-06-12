package br.ufrn.imd.models;

public interface IBoat {
	public boolean shoot();
	public void destroy();
	public void rotate();
	
	public void move(int x, int y);
}
