package br.com.agenda.model;

import br.com.agenda.model.erros.ModelException;

/**
 * Classe de contato, armazena dados de contato do usuário
 *
 * @version 1.0
 * @author Wandeson
 */
public class Contato extends ClasseBase {

    private int id;
    /**
     * Nome do usuário
     */
    private String nome;
    /**
     * Email de contato
     */
    private String email;
    /**
     * Telefone de contato
     */
    private String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Encapsulamento do atributo nome
     *
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Encapsulamento do atributo nome
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Construtor vazio
     */
    public Contato() {
    }

    /**
     * Construtor com todos os parametros da classe
     *
     * @param id
     * @param nome
     * @param email
     * @param telefone
     */
    public Contato(int id, String nome, String email, String telefone) {
        super();
        this.setId(id);
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    /**
     * Valida atributos do contato
     *
     * @return um valor verdadeiro se os campos estão preenchidos corretamente
     * ou um valor falso para o contrario
     * @throws ModelException Erro ao fazer a validação
     * @throws Exception
     */
    @Override
    public boolean ehValido() throws ModelException, Exception {

        if (nome.isEmpty() || nome.trim().length() == 0) {
            throw new ModelException("Nome n�o informado!");
        }

        if ((email.isEmpty() || email.trim().length() == 0)
                && (telefone.isEmpty() || telefone.trim().length() == 0)) {
            throw new ModelException("Informe uma forma de contato");
        }

        return true;
    }

}
