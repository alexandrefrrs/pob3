package exercicios.exercicios17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsercaoCliente {

    public static long inserirCliente(Connection conn, String nome, String email) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);

            if (pstmt.executeUpdate() != 1) {
                throw new SQLException("Não foi possível inserir o cliente.");
            }

            try (ResultSet resultado = pstmt.getGeneratedKeys()) {
                if (resultado.next()) {
                    long id = resultado.getLong(1);
                    System.out.println("Cliente inserido com ID: " + id);
                    return id;
                }
                throw new SQLException("O banco não retornou o ID do cliente.");
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = ConexaoBanco.conectar()) {
            ConexaoBanco.prepararExemplo(conn);
            inserirCliente(conn, "Ana Silva", "ana@exemplo.com");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }
}
