package exercicios.exercicios18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Produto {

    private Long id;
    private String descricao;
    private double precoUnitario;
    private int quantidadeEstoque;

    Produto(Long id, String descricao, double precoUnitario, int quantidadeEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}

public class ProdutoDAOImpl implements GenericDAO<Produto, Long> {

    private final Connection conn;

    public ProdutoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Produto produto) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO produtos (descricao, preco_unitario, quantidade_estoque) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preencher(stmt, produto);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ID do produto não retornado.");
                }
                produto.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao salvar produto.", e);
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar produto.", e);
        }
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produtos ORDER BY id");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao listar produtos.", e);
        }
        return produtos;
    }

    @Override
    public void atualizar(Produto produto) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE produtos SET descricao = ?, preco_unitario = ?, quantidade_estoque = ? WHERE id = ?")) {
            preencher(stmt, produto);
            stmt.setLong(4, produto.getId());
            if (stmt.executeUpdate() == 0) {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao atualizar produto.", e);
        }
    }

    @Override
    public void deletarPorId(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE id = ?")) {
            stmt.setLong(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao excluir produto.", e);
        }
    }

    private void preencher(PreparedStatement stmt, Produto produto) throws SQLException {
        stmt.setString(1, produto.getDescricao());
        stmt.setDouble(2, produto.getPrecoUnitario());
        stmt.setInt(3, produto.getQuantidadeEstoque());
    }

    private Produto mapear(ResultSet rs) throws SQLException {
        return new Produto(rs.getLong("id"), rs.getString("descricao"),
                rs.getDouble("preco_unitario"), rs.getInt("quantidade_estoque"));
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory.prepararBanco();
            try (Connection conn = ConnectionFactory.conectar()) {
                GenericDAO<Produto, Long> dao = new ProdutoDAOImpl(conn);
                Produto produto = new Produto(null, "Mouse", 60.00, 10);
                dao.salvar(produto);
                System.out.println("Produto encontrado: " + dao.buscarPorId(produto.getId()).orElseThrow().getDescricao());
                produto.setPrecoUnitario(75.00);
                dao.atualizar(produto);
                for (Produto item : dao.listarTodos()) {
                    System.out.println(item.getDescricao() + " - R$ " + item.getPrecoUnitario());
                }
                dao.deletarPorId(produto.getId());
                System.out.println("Produto presente após exclusão? " + dao.buscarPorId(produto.getId()).isPresent());
            }
        } catch (SQLException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
}
