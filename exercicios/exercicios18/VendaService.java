package exercicios.exercicios18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

class Pedido {

    private String cliente;

    Pedido(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() { return cliente; }
}

class ItemPedido {

    private String descricao;
    private int quantidade;
    private double precoUnitario;

    ItemPedido(String descricao, int quantidade, double precoUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
}

interface PedidoDAO {

    long criarPedido(Connection conn, Pedido pedido) throws SQLException;
}

interface ItemPedidoDAO {

    void salvarItens(Connection conn, long pedidoId, List<ItemPedido> itens) throws SQLException;
}

class PedidoDAOImpl implements PedidoDAO {

    @Override
    public long criarPedido(Connection conn, Pedido pedido) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pedidos (cliente) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pedido.getCliente());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ID do pedido não retornado.");
                }
                return rs.getLong(1);
            }
        }
    }
}

class ItemPedidoDAOImpl implements ItemPedidoDAO {

    @Override
    public void salvarItens(Connection conn, long pedidoId, List<ItemPedido> itens) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO itens_pedido (pedido_id, descricao, quantidade, preco_unitario) VALUES (?, ?, ?, ?)")) {
            for (ItemPedido item : itens) {
                stmt.setLong(1, pedidoId);
                stmt.setString(2, item.getDescricao());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPrecoUnitario());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}

public class VendaService {

    private final PedidoDAO pedidoDAO;
    private final ItemPedidoDAO itemPedidoDAO;

    public VendaService(PedidoDAO pedidoDAO, ItemPedidoDAO itemPedidoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.itemPedidoDAO = itemPedidoDAO;
    }

    public long registrarVenda(Pedido pedido, List<ItemPedido> itens) throws SQLException {
        if (pedido == null || itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Informe um pedido e pelo menos um item.");
        }
        try (Connection conn = ConnectionFactory.conectar()) {
            conn.setAutoCommit(false);
            try {
                long pedidoId = pedidoDAO.criarPedido(conn, pedido);
                itemPedidoDAO.salvarItens(conn, pedidoId, itens);
                conn.commit();
                return pedidoId;
            } catch (SQLException | RuntimeException e) {
                try {
                    conn.rollback();
                } catch (SQLException erroRollback) {
                    e.addSuppressed(erroRollback);
                }
                throw e;
            }
            // A conexão pertence ao serviço e é fechada pelo try-with-resources.
        }
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory.prepararBanco();
            VendaService service = new VendaService(new PedidoDAOImpl(), new ItemPedidoDAOImpl());
            long id = service.registrarVenda(new Pedido("Ana"), Arrays.asList(
                    new ItemPedido("Mouse", 2, 60.00), new ItemPedido("Teclado", 1, 140.00)));
            System.out.println("Venda confirmada com ID: " + id);

            try {
                service.registrarVenda(new Pedido("Carlos"), Arrays.asList(
                        new ItemPedido("Monitor", 1, 900.00), new ItemPedido("Item inválido", 0, 10.00)));
            } catch (SQLException e) {
                System.out.println("Venda cancelada: pedido e itens desfeitos por rollback.");
            }

            try (Connection conn = ConnectionFactory.conectar();
                    PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM pedidos");
                    ResultSet rs = stmt.executeQuery()) {
                rs.next();
                System.out.println("Pedidos persistidos: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }
}
