package br.com.agenda.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.agenda.controller.ContatoController;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JanelaPrincipal extends JFrame {

    private final ContatoController controller;

    private JPanel jpDados;
    private JPanel jpGrade;

    private JLabel jlCodigo;
    private JLabel jlNome;
    private JLabel jlEmail;
    private JLabel jlTelefone;

    private JTextField jtfCodigo;
    private JTextField jtfNome;
    private JTextField jtfEmail;
    private JTextField jtfTelefone;

    private JButton jbGravar;
    private JButton jbCancelar;
    private JButton jbNovo;
    private JButton jbExcluir;

    private JTable jtGrade;
    private JScrollPane jspGrade;
    private TableModel tmGrade;

    public JanelaPrincipal() throws Exception {
        super("Janela Principal");
        controller = new ContatoController();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 600);
        //this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        initLayout();
        initGrid();
        initListeners();
        configuracoesIniciais();
    }

    private void configuracoesIniciais() {
        modoSomenteLeitura();
    }

    private void modoSomenteLeitura() {
        for (int i = 0; i < jpDados.getComponentCount(); i++) {
            if (jpDados.getComponent(i).getClass().getName().endsWith("JTextField")
                    || jpDados.getComponent(i).getClass().getName().endsWith("JButton")) {
                jpDados.getComponent(i).setEnabled(false);
            }
        }

    }

    private void modoEdicao() {
        for (int i = 0; i < jpDados.getComponentCount(); i++) {
            if (jpDados.getComponent(i).getClass().getName().endsWith("JTextField")
                    || jpDados.getComponent(i).getClass().getName().endsWith("JButton")) {
                jpDados.getComponent(i).setEnabled(true);
            }
        }

        jtfCodigo.setEditable(false);
        jtfNome.setFocusable(true);
        jtfNome.grabFocus();

    }

    private void limparCampos() {
        for (int i = 0; i < jpDados.getComponentCount(); i++) {
            if (jpDados.getComponent(i).getClass().getName().endsWith("JTextField")) {
                ((JTextField) jpDados.getComponent(i)).setText("");
            }
        }
    }

    private void initListeners() {
        //Bot�o novo
        jbNovo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                modoEdicao();

            }
        });

        jbCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                modoSomenteLeitura();
            }
        });

        jbGravar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.gravar(jtfCodigo, jtfNome, jtfEmail, jtfTelefone);

                    limparCampos();
                    modoSomenteLeitura();
                    atualizaGrade();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!", "ATEN��O!", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Falha ao Gravar", JOptionPane.ERROR_MESSAGE);

                    //ex.printStackTrace();
                }

            }
        });

        jbExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.excluir(jtGrade);
                    limparCampos();
                    modoSomenteLeitura();
                    atualizaGrade();
                    JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!", "ATEN��O!", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Falha ao Gravar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initLayout() {
        jpDados = new JPanel();
        jpDados.setBorder(
                BorderFactory
                        .createLineBorder(
                                Color.DARK_GRAY, 3));
        jpDados.setBounds(50, 20, 700, 200);
        jpDados.setLayout(null);
        this.add(jpDados);

        jlCodigo = new JLabel("Código:");
        jlCodigo.setBounds(20, 20, 100, 20);
        jpDados.add(jlCodigo);

        jtfCodigo = new JTextField();
        jtfCodigo.setBounds(120, 20, 100, 20);
        jtfCodigo.setEditable(false);
        jpDados.add(jtfCodigo);

        jlNome = new JLabel("Nome:");
        jlNome.setBounds(20, 50, 100, 20);
        jpDados.add(jlNome);

        jtfNome = new JTextField();
        jtfNome.setBounds(120, 50, 350, 20);
        jpDados.add(jtfNome);

        jlEmail = new JLabel("E-mail:");
        jlEmail.setBounds(20, 80, 100, 20);
        jpDados.add(jlEmail);

        jtfEmail = new JTextField();
        jtfEmail.setBounds(120, 80, 350, 20);
        jpDados.add(jtfEmail);

        jlTelefone = new JLabel("Telefone:");
        jlTelefone.setBounds(20, 110, 100, 20);
        jpDados.add(jlTelefone);

        jtfTelefone = new JTextField();
        jtfTelefone.setBounds(120, 110, 100, 20);
        jpDados.add(jtfTelefone);

        jbGravar = new JButton("Gravar");
        jbGravar.setBounds(480, 160, 100, 30);
        jpDados.add(jbGravar);

        jbCancelar = new JButton("Cancelar");
        jbCancelar.setBounds(590, 160, 100, 30);
        jpDados.add(jbCancelar);

        jpGrade = new JPanel();
        jpGrade.setBorder(
                BorderFactory
                        .createLineBorder(
                                Color.DARK_GRAY, 3));
        jpGrade.setBounds(50, 230, 700, 250);
        jpGrade.setLayout(new BorderLayout());
        this.add(jpGrade);

        jbNovo = new JButton("Novo");
        jbNovo.setBounds(540, 500, 100, 30);
        this.add(jbNovo);

        jbExcluir = new JButton("Excluir");
        jbExcluir.setBounds(650, 500, 100, 30);
        this.add(jbExcluir);
    }

    private void initGrid() {

        String[] colunas = {"Código",
            "Nome",
            "E-mail",
            "Telefone"};

        Object[][] dados = {};

        tmGrade = new DefaultTableModel(
                dados, colunas);

        jtGrade = new JTable(tmGrade);

        jspGrade = new JScrollPane(jtGrade);

        jtGrade.getColumnModel()
                .getColumn(0).setPreferredWidth(3);

        jtGrade.getColumnModel()
                .getColumn(1).setPreferredWidth(200);

        jtGrade.getColumnModel()
                .getColumn(2).setPreferredWidth(150);

        jtGrade.getColumnModel()
                .getColumn(3).setPreferredWidth(15);
        jtGrade.setFillsViewportHeight(true);

        ///jspGrade.setBounds(x, y, width, height);
        jpGrade.add(jspGrade, BorderLayout.CENTER);
        atualizaGrade();
    }

    private void atualizaGrade() {
        try {
            controller.atualizaGrade(jtGrade);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Falha ao carregar registros", JOptionPane.ERROR_MESSAGE);
        }
    }
}
