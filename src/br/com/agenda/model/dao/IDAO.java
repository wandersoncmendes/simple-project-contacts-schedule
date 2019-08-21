package br.com.agenda.model.dao;

import java.util.List;

import br.com.agenda.model.ClasseBase;

/**
 * Interface que padroniza os metodos das classes Dao
 * @author Wanderson
 * @version 1.0
 * @param <T> uma classe model que herda da ClasseBase
 */
public interface IDAO<T extends ClasseBase> {

	public T insert(T entidade) throws Exception;
	public T update(T entidade) throws Exception;
	public void delete(int id) throws Exception;
	public T select(int id) throws Exception;
	public List<T> selectAll() throws Exception;
}
