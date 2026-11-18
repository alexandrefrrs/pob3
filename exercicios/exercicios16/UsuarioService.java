package exercicios.exercicios16;

import java.util.Optional;

class Usuario {

    private Long id;
    private String nome;
    private String email;

    Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

interface UsuarioRepository {

    Optional<Usuario> buscarPorId(Long id);

    void salvar(Usuario usuario);
}

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void atualizarEmail(Long id, String novoEmail) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        usuario.setEmail(novoEmail);
        repository.salvar(usuario);
    }
}
