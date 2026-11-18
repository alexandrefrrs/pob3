package exercicios.exercicios16;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveAtualizarEmailESalvarUsuario() {
        Usuario usuarioExistente = new Usuario(1L, "Ana Silva", "ana@exemplo.com");
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(usuarioExistente));

        service.atualizarEmail(1L, "novo@exemplo.com");

        assertEquals("novo@exemplo.com", usuarioExistente.getEmail());
        verify(repository, times(1)).buscarPorId(1L);
        verify(repository, times(1)).salvar(any(Usuario.class));
        verify(repository).salvar(usuarioExistente);
    }

    @Test
    void naoDeveSalvarUsuarioInexistente() {
        when(repository.buscarPorId(2L)).thenReturn(Optional.empty());

        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> service.atualizarEmail(2L, "novo@exemplo.com"));

        assertEquals("Usuário não encontrado.", erro.getMessage());
        verify(repository, never()).salvar(any(Usuario.class));
    }
}
