package br.com.agenda.model.service;

import br.com.agenda.model.Contato;
import br.com.agenda.model.dao.ContatoDAO;

/**
 * Classe especifica de services para o contato
 *
 * @author Wanderson
 * @version 1.0
 */
public class ContatoService extends ServiceBase<Contato> {

    /**
     * Construtor que chama o construtor da Service base passando o dao que
     * deseja usar para as operaçoes do banco
     * @throws java.lang.Exception
     */
    public ContatoService() throws Exception {
        super(new ContatoDAO());

    }

}
