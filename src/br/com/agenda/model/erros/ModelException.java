package br.com.agenda.model.erros;

/**
 * Classe de manipulação de erros nos models
 * @version 1.0
 * @author Wanderson
 */
public class ModelException extends Exception{
	
	public ModelException(String msg) {
		super(msg);
	}
}
