package br.com.agenda.model.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.agenda.model.Contato;
import br.com.agenda.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de crud do contato
 * @version 1.0
 * @author Wanderson
 */
public class ContatoDAO implements IDAO<Contato> {

    private final Connection conexao;

    public Connection getConexao() {
        return this.conexao;
    }

    public ContatoDAO() throws Exception {
        conexao = ConnectionFactory
                .getConnection("Mysql");
    }

    /**
     * Insere um dado no banco
     *
     * @param entidade um contato
     * @return um contato
     * @throws Exception Erro no processo de insert no banco
     */
    @Override
    public Contato insert(Contato entidade) throws Exception {
        try {
            String sql = "insert into Contato (nome, email, telefone) values (?,?,?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getTelefone());

            return ps.execute() ? entidade : null;

        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar contato");
        } catch (Exception e) {
            throw new InternalError("Erro interno!");
        }
    }

    /**
     * Atualiza um dado do banco
     *
     * @param entidade um contato
     * @return um contato
     * @throws Exception Erro no processo de update
     */
    @Override
    public Contato update(Contato entidade) throws Exception {
        try {

            String sql = "update Contato set nome=?, email=?, telefone=? where id=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getTelefone());
            ps.setInt(4, entidade.getId());

            return ps.execute() ? entidade : null;

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar contato");
        } catch (Exception e) {
            throw new InternalError("Erro interno!");
        }
    }

    /**
     * Deleta um dado do banco
     *
     * @param id id do contato
     * @throws Exception Erro ao deletar dado
     */
    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "delete from Contato where id = ?";

            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar contato");
        } catch (Exception e) {
            throw new InternalError("Erro interno!");
        }

    }

    /**
     * Seleciona um dado do banco
     *
     * @param id id do contato
     * @return um contato
     * @throws Exception Erro na busca
     */
    @Override
    public Contato select(int id) throws Exception {
        try {
            String sql = "select * from Contato where id = ?";

            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            Contato contato = null;

            if (rs.next()) {
                contato = new Contato(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
            }

            return contato;

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar contato");
        } catch (Exception e) {
            throw new InternalError("Erro interno!");
        }
    }

    /**
     * Seleciona todos os dados da tabela no banco
     *
     * @return uma lista de contatos
     * @throws Exception Erro na busca dos dados
     */
    @Override
    public List<Contato> selectAll() throws Exception {

        try {
            String sql = "select * from Contato";

            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Contato> contatos = new ArrayList<>();

            while (rs.next()) {
                Contato c = new Contato(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
                contatos.add(c);
            }

            return contatos;

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar contato");
        } catch (Exception e) {
            throw new InternalError("Erro interno!");
        }
    }

}
