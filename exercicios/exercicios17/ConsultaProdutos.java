package exercicios.exercicios17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Produto {

    private Long id;
    private String nome;
    private Double preco;
    private Integer estoque;

    Produto(Long id, String nome, Double preco, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }

    public Double getPreco() { return preco; }

    public Integer getEstoque() { return estoque; }
}

public class ConsultaProdutos {

    public static List<Produto> buscarPorFaixaPreco(Connection conn, double min, double max) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco, estoque FROM produtos WHERE preco BETWEEN ? AND ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, min);
            pstmt.setDouble(2, max);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    Double preco = rs.getDouble("preco");
                    if (rs.wasNull()) {
                        preco = null;
                    }
                    Integer estoque = rs.getInt("estoque");
                    if (rs.wasNull()) {
                        estoque = null;
                    }
                    produtos.add(new Produto(id, nome, preco, estoque));
                }
            }
        }
        return produtos;
    }

    public static void main(String[] args) {
        try (Connection conn = ConexaoBanco.conectar()) {
            ConexaoBanco.prepararExemplo(conn);

            for (Produto produto : buscarPorFaixaPreco(conn, 60.00, 200.00)) {
                System.out.printf("ID: %d | %s | R$ %.2f | Estoque: %d%n",
                        produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
        }
    }
}
