package exercicios.exercicios06;

class ContaBancaria {

    String titular;
    String numeroConta;
    double saldo = 0;

    void depositar(double valor) {

        if (valor > 0) {

            saldo += valor;
            System.out.println("Depósito realizado.");
        } 
            else {
                System.out.println("Valor inválido.");
            }
    }

    void sacar(double valor) {

        if (valor <= 0) {

            System.out.println("Valor inválido.");
        } 
            else if (saldo >= valor) {

                saldo -= valor;
                System.out.println("Saque realizado.");

             }   
                else {
                
                    System.out.println("Saldo insuficiente.");
                }
    }

    void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }
}

public class Main {
    public static void main(String[] args) {

        ContaBancaria conta = new ContaBancaria();

        conta.titular = "Alexandre";
        conta.numeroConta = "12345";

        conta.depositar(1000);
        conta.consultarSaldo();

        conta.sacar(300);
        conta.consultarSaldo();

        conta.sacar(900);
        conta.depositar(-50);
    }
}