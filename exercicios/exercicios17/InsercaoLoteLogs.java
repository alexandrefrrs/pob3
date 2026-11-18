package exercicios.exercicios17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InsercaoLoteLogs {

    public static void inserirLoteLog(Connection conn, List<String> mensagensLog) throws SQLException {
        String sql = "INSERT INTO logs_sistema (mensagem, data_registro) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String mensagem : mensagensLog) {
                pstmt.setString(1, mensagem);
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Mensagens inseridas em lote: " + mensagensLog.size());
        }
    }

    public static void main(String[] args) {
        List<String> mensagens = new ArrayList<>();
        for (int i = 1; i <= 300; i++) {
            mensagens.add("Evento do sistema " + i);
        }

        try (Connection conn = ConexaoBanco.conectar()) {
            ConexaoBanco.prepararExemplo(conn);
            inserirLoteLog(conn, mensagens);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir logs: " + e.getMessage());
        }
    }
}
