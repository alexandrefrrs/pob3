package exercicios.exercicios18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Aluno {

    private Long id;
    private String nome;
    private String matricula;

    Aluno(Long id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
}

interface AlunoDAO {

    void inserir(Aluno aluno);

    List<Aluno> listarTodos();
}

public class AlunoDAOImpl implements AlunoDAO {

    private final Connection conn;

    public AlunoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Aluno aluno) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO alunos (nome, matricula) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ID do aluno não retornado.");
                }
                aluno.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao inserir aluno.", e);
        }
    }

    @Override
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT id, nome, matricula FROM alunos ORDER BY id");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                alunos.add(new Aluno(rs.getLong("id"), rs.getString("nome"), rs.getString("matricula")));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao listar alunos.", e);
        }
        return alunos;
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory.prepararBanco();
            try (Connection conn = ConnectionFactory.conectar()) {
                AlunoDAO dao = new AlunoDAOImpl(conn);
                Aluno aluno = new Aluno(null, "Ana Silva", "2026001");
                dao.inserir(aluno);
                System.out.println("ID preenchido no objeto: " + aluno.getId());
                for (Aluno cadastrado : dao.listarTodos()) {
                    System.out.println(cadastrado.getNome() + " - Matrícula: " + cadastrado.getMatricula());
                }
            }
        } catch (SQLException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
}
