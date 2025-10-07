package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class listagemVIEW extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public listagemVIEW() {
        super("Listagem de Produtos - Casa de Leilões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(680, 420);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Categoria", "Valor"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAtualizar = new JButton("Atualizar");
        top.add(btnAtualizar);
        add(top, BorderLayout.NORTH);

        btnAtualizar.addActionListener(e -> carregar());

        carregar();
    }

    private void carregar() {
        model.setRowCount(0);
        List<Produto> lista = new ProdutoDAO().listarTodos();
        for (Produto p : lista) {
            model.addRow(new Object[]{ p.getId(), p.getNome(), p.getDescricao(), p.getCategoria(), String.format("R$ %.2f", p.getValor()) });
        }
    }
}