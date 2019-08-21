package br.com.agenda.model;

import br.com.agenda.model.erros.ModelException;

/**
 * @version 1.0
 * @author Wanderson
 * Clase base para todas as classes do sistema
 */
public abstract class ClasseBase {

	/**
	 * Campo chave da entidade
	 */
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public abstract boolean ehValido() throws ModelException, Exception;
	
}
