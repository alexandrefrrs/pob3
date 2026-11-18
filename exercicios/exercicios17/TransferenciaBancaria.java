package exercicios.exercicios17;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferenciaBancaria {

    public static void transferirFundos(Connection conn, Long idOrigem, Long idDestino, double valor) throws SQLException {
        if (idOrigem == null || idDestino == null || idOrigem.equals(idDestino)) {
            throw new IllegalArgumentException("Informe duas contas diferentes.");
        }
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo e finito.");
        }
        BigDecimal quantia = BigDecimal.valueOf(valor).setScale(2, RoundingMode.UNNECESSARY);
        boolean autoCommitOriginal = conn.getAutoCommit();
        if (!autoCommitOriginal) {
            throw new SQLException("A conexão já possui uma transação em andamento.");
        }

        conn.setAutoCommit(false);
        try {
            // Bloqueia a origem até o fim da transação para validar o saldo com segurança.
            try (PreparedStatement consulta = conn.prepareStatement("SELECT saldo FROM contas WHERE id = ? FOR UPDATE")) {
                consulta.setLong(1, idOrigem);
                try (ResultSet rs = consulta.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Conta de origem não encontrada.");
                    }
                    if (rs.getBigDecimal("saldo").compareTo(quantia) < 0) {
                        throw new IllegalArgumentException("Saldo insuficiente.");
                    }
                }
            }

            try (PreparedStatement debito = conn.prepareStatement("UPDATE contas SET saldo = saldo - ? WHERE id = ?")) {
                debito.setBigDecimal(1, quantia);
                debito.setLong(2, idOrigem);
                if (debito.executeUpdate() != 1) {
                    throw new SQLException("Não foi possível debitar a conta de origem.");
                }
            }

            try (PreparedStatement credito = conn.prepareStatement("UPDATE contas SET saldo = saldo + ? WHERE id = ?")) {
                credito.setBigDecimal(1, quantia);
                credito.setLong(2, idDestino);
                if (credito.executeUpdate() != 1) {
                    throw new SQLException("Conta de destino não encontrada.");
                }
            }

            conn.commit();
            System.out.println("Transferência realizada com sucesso.");
        } catch (SQLException | RuntimeException e) {
            try {
                conn.rollback();
            } catch (SQLException erroRollback) {
                e.addSuppressed(erroRollback);
                // Não reativa autocommit após rollback falho, pois isso poderia confirmar o débito.
                try {
                    conn.close();
                } catch (SQLException erroFechamento) {
                    e.addSuppressed(erroFechamento);
                }
            }
            throw e;
        } finally {
            if (!conn.isClosed()) {
                conn.setAutoCommit(autoCommitOriginal);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = ConexaoBanco.conectar()) {
            ConexaoBanco.prepararExemplo(conn);
            transferirFundos(conn, 1L, 2L, 200.00);

            try {
                // O destino inexistente provoca uma falha depois do débito e exige rollback.
                transferirFundos(conn, 1L, 99L, 100.00);
            } catch (SQLException e) {
                System.out.println("Transferência cancelada: " + e.getMessage());
            }

            try {
                transferirFundos(conn, 1L, 2L, 5000.00);
            } catch (IllegalArgumentException e) {
                System.out.println("Transferência cancelada: " + e.getMessage());
            }

            try (PreparedStatement pstmt = conn.prepareStatement("SELECT id, saldo FROM contas ORDER BY id");
                    ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Conta " + rs.getLong("id") + " - Saldo: R$ " + rs.getBigDecimal("saldo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na transferência: " + e.getMessage());
        }
    }
}
