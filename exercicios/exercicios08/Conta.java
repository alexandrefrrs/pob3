package exercicios.exercicios08;

class Conta {

    private String numero;
    private double saldo;

    Conta(String numero) {
        this.numero = numero;
        this.saldo = 0;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Depósito inválido.");
        }
    }

    protected boolean podeSacar(double valor) {
        return saldo >= valor;
    }

    public void sacar(double valor) {
        if (valor > 0 && podeSacar(valor)) {
            saldo -= valor;
        } else {
            System.out.println("Saque inválido ou saldo insuficiente.");
        }
    }
}

class ContaPoupanca extends Conta {

    private double taxaRendimento;

    ContaPoupanca(String numero, double taxaRendimento) {
        super(numero);
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaRendimento / 100;

        if (rendimento > 0) {
            super.depositar(rendimento);
        }
    }
}

class ContaCorrente extends Conta {

    private double limiteChequeEspecial;

    ContaCorrente(String numero, double limiteChequeEspecial) {
        super(numero);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    protected boolean podeSacar(double valor) {
        return getSaldo() - valor >= -limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            super.sacar(valor + 2);
        } else {
            System.out.println("Valor de saque inválido.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        ContaPoupanca poupanca =
                new ContaPoupanca("12345", 5);

        ContaCorrente corrente =
                new ContaCorrente("67890", 500);

        poupanca.depositar(1000);

        System.out.println("Saldo inicial da poupança: R$ " +
                poupanca.getSaldo());

        poupanca.aplicarRendimento();

        System.out.println("Saldo após rendimento: R$ " +
                poupanca.getSaldo());

        poupanca.sacar(200);

        System.out.println("Saldo após saque: R$ " +
                poupanca.getSaldo());

        System.out.println();

        corrente.depositar(300);

        System.out.println("Saldo inicial da conta corrente: R$ " +
                corrente.getSaldo());

        corrente.sacar(400);

        System.out.println("Saldo após saque: R$ " +
                corrente.getSaldo());

        corrente.sacar(500);

        System.out.println("Saldo final: R$ " +
                corrente.getSaldo());
    }
}