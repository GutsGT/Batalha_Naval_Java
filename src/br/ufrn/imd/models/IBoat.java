package br.ufrn.imd.models;

public interface IBoat {	
	/**
	 * Método para destruir uma parte do barco de acordo com o índice.
	 * @param index
	 */
	public void destroyPart(int index);
	
	/**
	 * Método utilizado para inicializar um barco com todas as partes não destruídas.
	 */
	public void setDestroyedParts();
	
	/**
	 * Rotaciona trocando a largura pela altura e vice-versa. Também inverte o valor booleano que informa se está na horizontal ou não.
	 */
	public void rotate();
}
