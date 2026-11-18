package exercicios.exercicios17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManutencaoFuncionarios {

    public static int atualizarSalario(Connection conn, Long id, double novoSalario) throws SQLException {
        String sql = "UPDATE funcionarios SET salario = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, novoSalario);
            pstmt.setLong(2, id);
            int linhas = pstmt.executeUpdate();
            exibirResultado(linhas);
            return linhas;
        }
    }

    public static int deletarPorId(Connection conn, Long id) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM funcionarios WHERE id = ?")) {
            pstmt.setLong(1, id);
            int linhas = pstmt.executeUpdate();
            exibirResultado(linhas);
            return linhas;
        }
    }

    private static void exibirResultado(int linhas) {
        if (linhas == 0) {
            System.out.println("Nenhum registro encontrado com o ID informado.");
        } else {
            System.out.println("Registros afetados: " + linhas);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = ConexaoBanco.conectar()) {
            ConexaoBanco.prepararExemplo(conn);
            atualizarSalario(conn, 1L, 3500.00);
            atualizarSalario(conn, 99L, 2000.00);
            deletarPorId(conn, 2L);
            deletarPorId(conn, 99L);
        } catch (SQLException e) {
            System.out.println("Erro ao alterar funcionários: " + e.getMessage());
        }
    }
}
