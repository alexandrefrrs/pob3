package exercicios.exercicios18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String cpf;

    Cliente(Long id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
}

interface ClienteDAO {

    Optional<Cliente> buscarPorCpf(String cpf);

    void salvar(Cliente cliente);
}

class ClienteDAOImpl implements ClienteDAO {

    private final Connection conn;

    ClienteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clientes WHERE cpf = ?")) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Cliente(rs.getLong("id"), rs.getString("nome"),
                            rs.getString("email"), rs.getString("cpf")));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar cliente.", e);
        }
    }

    @Override
    public void salvar(Cliente cliente) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO clientes (nome, email, cpf) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ID do cliente não retornado.");
                }
                cliente.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao salvar cliente.", e);
        }
    }
}

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }
        if (cliente.getCpf() == null || !cliente.getCpf().matches("[0-9]{11}")) {
            throw new IllegalArgumentException("Informe o CPF com 11 dígitos, sem pontuação.");
        }
        if (clienteDAO.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        clienteDAO.salvar(cliente);
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory.prepararBanco();
            try (Connection conn = ConnectionFactory.conectar()) {
                ClienteService service = new ClienteService(new ClienteDAOImpl(conn));
                Cliente cliente = new Cliente(null, "Ana", "ana@exemplo.com", "12345678901");
                service.cadastrarCliente(cliente);
                System.out.println("Cliente cadastrado com ID: " + cliente.getId());
                try {
                    service.cadastrarCliente(new Cliente(null, "Outra pessoa", "outro@exemplo.com", "12345678901"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Cadastro recusado: " + e.getMessage());
                }
            }
        } catch (SQLException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
}
