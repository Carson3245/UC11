package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    // ajuste a senha em PASS
    private static final String URL  = "jdbc:mysql://127.0.0.1:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "uc11";
    private static final String PASS = "12345";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Connector/J não encontrado. Coloque mysql-connector-j-8.x.x.jar em lib/ e inclua no classpath.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        ensureSchema(conn);
        return conn;
    }

    // Cria a tabela se não existir (sintaxe MySQL)
    private static void ensureSchema(Connection conn) {
        String sql =
            "CREATE TABLE IF NOT EXISTS produtos (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  nome VARCHAR(200) NOT NULL," +
            "  descricao TEXT," +
            "  categoria VARCHAR(100)," +
            "  valor DECIMAL(10,2) NOT NULL" +
            ")";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException ignored) {}
    }
}
