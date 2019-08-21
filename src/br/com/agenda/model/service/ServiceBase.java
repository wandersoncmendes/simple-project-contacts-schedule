package br.com.agenda.model.service;

import java.util.List;

import br.com.agenda.model.ClasseBase;
import br.com.agenda.model.dao.IDAO;
import br.com.agenda.model.erros.ModelException;

/**
 * Classe abstrata que realia as operaçoes padroes do cruds
 * @author Wanderson
 * @param <T> 
 */
public abstract class ServiceBase <T extends ClasseBase> {

	private final IDAO<T> dao;
	
        /**
         * Construtor
         * @param dao A classe que deseja implementar os metodos do crud
         */
	public ServiceBase(IDAO<T> dao) {
		this.dao = dao;
	}
	
        /**
         * Salva um registro da entidade informada
         * @param entidade contato
         * @return um valor true para salvo e false para nao salvo
         * @throws Exception 
         */
	public boolean salvar(T entidade) throws Exception{
		
		//Validações
		if(entidade == null)
			throw new ModelException("Registro n�o informado!");
		
		entidade.ehValido();
		
		if(entidade.getId() == 0)
			dao.insert(entidade);
		else
			dao.update(entidade);
		
		return true;
	}

        /**
         * Exlclui um registro
         * @param id
         * @return um valor true caso o registro seja deletado com sucesso
         * @throws Exception 
         */
	public boolean excluir(int id) throws Exception{
		if(id == 0)
			throw new ModelException("Código não informado!");
		
		dao.delete(id);
		
		return true;
	}
	
        /**
         * Seleciona todos os registros
         * @return A lista dos registros
         * @throws Exception 
         */
	public List<T> selecionarTodos() throws Exception{
		
		return dao.selectAll();
	}
}
