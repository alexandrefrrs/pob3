package exercicios.exercicios16;

class SaldoInsuficienteException extends RuntimeException {

    SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

public class ContaBancaria {

    private double saldo;

    public ContaBancaria(double saldoInicial) {
        if (!Double.isFinite(saldoInicial) || saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido.");
        }
        saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo e finito.");
        }
        saldo += valor;
    }

    public void sacar(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo e finito.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }
        saldo -= valor;
    }
}
