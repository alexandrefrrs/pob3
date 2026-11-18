package exercicios.exercicios18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Livro {

    private Long id;
    private String titulo;

    Livro(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
}

interface LivroDAO {

    List<Livro> buscarPaginado(int pagina, int tamanhoPagina);
}

public class LivroDAOImpl implements LivroDAO {

    private final Connection conn;

    public LivroDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Livro> buscarPaginado(int pagina, int tamanhoPagina) {
        // As páginas começam em 1.
        if (pagina < 1 || tamanhoPagina < 1) {
            throw new IllegalArgumentException("Página e tamanho devem ser maiores que zero.");
        }
        long offset = (long) (pagina - 1) * tamanhoPagina;
        List<Livro> livros = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livros ORDER BY id LIMIT ? OFFSET ?")) {
            stmt.setInt(1, tamanhoPagina);
            stmt.setLong(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    livros.add(new Livro(rs.getLong("id"), rs.getString("titulo")));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao consultar livros.", e);
        }
        return livros;
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory.prepararBanco();
            try (Connection conn = ConnectionFactory.conectar()) {
                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO livros (titulo) VALUES (?)")) {
                    for (int i = 1; i <= 5; i++) {
                        stmt.setString(1, "Livro " + i);
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
                LivroDAO dao = new LivroDAOImpl(conn);
                for (Livro livro : dao.buscarPaginado(2, 2)) {
                    System.out.println(livro.getId() + " - " + livro.getTitulo());
                }
            }
        } catch (SQLException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
}
