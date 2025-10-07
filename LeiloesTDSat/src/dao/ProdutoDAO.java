package dao;

import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, descricao, categoria, valor) VALUES (?,?,?,?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getValor());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, categoria, valor FROM produtos ORDER BY id DESC";
        try (Connection c = ConnectionFactory.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("categoria"),
                        rs.getDouble("valor")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}