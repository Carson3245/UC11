import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO obj) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
        try {
            conectaDAO c = new conectaDAO();
            conn = c.connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, obj.getNome());
            prep.setInt(2, obj.getValor());
            prep.setString(3, obj.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        } finally {
            try { if (prep != null) prep.close(); } catch (Exception ig) {}
            try { if (conn != null) conn.close(); } catch (Exception ig) {}
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos ORDER BY id DESC";
        try {
            conectaDAO c = new conectaDAO();
            conn = c.connectDB();
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listagem.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ig) {}
            try { if (prep != null) prep.close(); } catch (Exception ig) {}
            try { if (conn != null) conn.close(); } catch (Exception ig) {}
        }
        return listagem;
    }

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            conectaDAO c = new conectaDAO();
            conn = c.connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            int rows = prep.executeUpdate();
            if (rows == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com ID " + id);
                return false;
            }
            JOptionPane.showMessageDialog(null, "Produto ID " + id + " marcado como Vendido.");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
            return false;
        } finally {
            try { if (prep != null) prep.close(); } catch (Exception ig) {}
            try { if (conn != null) conn.close(); } catch (Exception ig) {}
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido' ORDER BY id DESC";
        try {
            conectaDAO c = new conectaDAO();
            conn = c.connectDB();
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                vendidos.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ig) {}
            try { if (prep != null) prep.close(); } catch (Exception ig) {}
            try { if (conn != null) conn.close(); } catch (Exception ig) {}
        }
        return vendidos;
    }
}