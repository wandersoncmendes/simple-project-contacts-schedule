package br.com.agenda.controller;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.agenda.model.Contato;
import br.com.agenda.model.service.ContatoService;

public class ContatoController {

    private final ContatoService service;

    public ContatoController() throws Exception {
        service = new ContatoService();
    }

    /**
     * Salva um registro
     * @param id
     * @param nome
     * @param email
     * @param telefone
     * @return
     * @throws Exception 
     */
    public boolean gravar(JTextField id,
            JTextField nome,
            JTextField email,
            JTextField telefone) throws Exception {

        Contato entidade = new Contato();

        int codigo = 0;
        if (!id.getText().isEmpty()) {
            codigo = Integer.valueOf(id.getText());
        }

        entidade.setId(codigo);
        entidade.setNome(nome.getText());
        entidade.setEmail(email.getText());
        entidade.setTelefone(telefone.getText());

        service.salvar(entidade);

        return true;
    }

    /**
     * Atulaiza as linhas da tabela
     * @param grade
     * @throws Exception 
     */
    public void atualizaGrade(JTable grade) throws Exception {

        ((DefaultTableModel) grade.getModel()).getDataVector().clear();

        for (Contato item : service.selecionarTodos()) {

            Vector linha = new Vector();
            linha.add(item.getId());
            linha.add(item.getNome());
            linha.add(item.getEmail());
            linha.add(item.getTelefone());

            ((DefaultTableModel) grade.getModel()).addRow(linha);
        }
        grade.repaint();

    }

    /**
     * Exclui um registro
     * @param grade
     * @throws Exception 
     */
    public void excluir(JTable grade) throws Exception {
        int cod = Integer.parseInt(grade.getValueAt(grade.getSelectedRow(), 0).toString());
        service.excluir(cod);
    }
}
