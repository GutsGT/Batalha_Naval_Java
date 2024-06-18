package br.ufrn.imd.models;

/**
 * @author Otávio Augusto
 * @version 1.0
 * 
 */


public interface IBoat {
	public int[] shootIn(int x, int y);
	public void destroyPart(int index);
	
	public void setDestroyedParts();
}
