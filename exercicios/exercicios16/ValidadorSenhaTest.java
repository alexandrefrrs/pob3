package exercicios.exercicios16;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorSenhaTest {

    private final ValidadorSenha validador = new ValidadorSenha();

    @ParameterizedTest
    @ValueSource(strings = {"Senha123", "Abcdefg1", "SEGURA2026", "MinhaSenha9"})
    void deveAceitarSenhasFortes(String senha) {
        assertTrue(validador.isForte(senha));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"Abc1234", "SemNumeros", "senha123", "12345678", "        "})
    void deveRejeitarSenhasFracas(String senha) {
        assertFalse(validador.isForte(senha));
    }
}
