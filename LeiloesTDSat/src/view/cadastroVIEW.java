package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class cadastroVIEW extends JFrame {

    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JComboBox<String> cbCategoria;
    private JFormattedTextField txtValor;

    public cadastroVIEW() {
        super("Cadastro de Produto - Casa de Leilões");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;

        int row = 0;

        // Nome
        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Nome*"), gc);
        txtNome = new JTextField();
        gc.gridx = 1; gc.gridy = row; form.add(txtNome, gc);
        row++;

        // Descrição
        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Descrição"), gc);
        txtDescricao = new JTextArea(4, 20);
        JScrollPane spDesc = new JScrollPane(txtDescricao);
        gc.gridx = 1; gc.gridy = row; form.add(spDesc, gc);
        row++;

        // Categoria
        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Categoria"), gc);
        cbCategoria = new JComboBox<>(new String[]{"Arte", "Colecionáveis", "Antiguidades", "Outros"});
        gc.gridx = 1; gc.gridy = row; form.add(cbCategoria, gc);
        row++;

        // Valor
        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Valor* (R$)"), gc);
        txtValor = new JFormattedTextField();
        txtValor.setColumns(12);
        gc.gridx = 1; gc.gridy = row; form.add(txtValor, gc);
        row++;

        // Buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnListar = new JButton("Listagem");
        buttons.add(btnListar);
        buttons.add(btnSalvar);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnSalvar.addActionListener(this::onSalvar);
        btnListar.addActionListener(e -> {
            listagemVIEW l = new listagemVIEW();
            l.setLocationRelativeTo(this);
            l.setVisible(true);
        });
    }

    private void onSalvar(ActionEvent e) {
        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String categoria = (String) cbCategoria.getSelectedItem();
        String vtxt = txtValor.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double valor;
        try {
            valor = Double.parseDouble(vtxt.replace(",", "."));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto p = new Produto(null, nome, descricao, categoria, valor);
        boolean ok = new ProdutoDAO().inserir(p);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtNome.setText("");
            txtDescricao.setText("");
            txtValor.setValue(null);
            txtNome.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}