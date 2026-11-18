package exercicios.exercicios16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaBancariaTest {

    private ContaBancaria conta;

    @BeforeEach
    void prepararConta() {
        conta = new ContaBancaria(1000.00);
    }

    @Test
    void deveDepositar() {
        conta.depositar(250.00);
        assertEquals(1250.00, conta.getSaldo(), 0.001);
    }

    @Test
    void deveSacar() {
        conta.sacar(400.00);
        assertEquals(600.00, conta.getSaldo(), 0.001);
    }

    @Test
    void devePermitirSaqueDoSaldoTotal() {
        conta.sacar(1000.00);
        assertEquals(0.00, conta.getSaldo(), 0.001);
    }

    @Test
    void deveRejeitarSaqueSuperiorAoSaldo() {
        SaldoInsuficienteException erro = assertThrows(
                SaldoInsuficienteException.class, () -> conta.sacar(1500.00));

        assertEquals("Saldo insuficiente para realizar o saque.", erro.getMessage());
        assertEquals(1000.00, conta.getSaldo(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -10, Double.NaN, Double.POSITIVE_INFINITY})
    void deveRejeitarSaqueInvalido(double valor) {
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(valor));
        assertEquals(1000.00, conta.getSaldo(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -10, Double.NaN, Double.POSITIVE_INFINITY})
    void deveRejeitarDepositoInvalido(double valor) {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(valor));
        assertEquals(1000.00, conta.getSaldo(), 0.001);
    }
}
