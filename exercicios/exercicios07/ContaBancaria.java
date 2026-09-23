package exercicios.exercicios07;

class ContaBancaria {

    private String numeroConta;
    private String titular;
    private double saldo;

    ContaBancaria(String numeroConta, String titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = 0;
    }

    ContaBancaria(String numeroConta, String titular, double depositoInicial) {
        this(numeroConta, titular);
        depositar(depositoInicial);
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saque inválido ou saldo insuficiente.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        ContaBancaria conta1 = new ContaBancaria("12345", "Carlos");
        ContaBancaria conta2 = new ContaBancaria("67890", "Ana", 500);

        System.out.println("Titular: " + conta1.getTitular());
        System.out.println("Saldo inicial: R$ " + conta1.getSaldo());

        conta1.depositar(1000);

        System.out.println("Saldo após depósito: R$ " +
                conta1.getSaldo());

        conta1.sacar(300);

        System.out.println("Saldo após saque: R$ " +
                conta1.getSaldo());

        conta1.sacar(2000);

        System.out.println("Saldo final: R$ " + conta1.getSaldo());

        System.out.println();

        System.out.println("Titular: " + conta2.getTitular());
        System.out.println("Saldo: R$ " + conta2.getSaldo());
    }
}