package exercicios.exercicios10;

class SaldoInsuficienteException extends Exception {

    SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

class ContaCorrente {

    private String numero;
    private double saldo;

    ContaCorrente(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                "Valor do saque (R$ " + valor + ") excede o saldo disponível (R$ " + saldo + ")."
            );
        }
        
        saldo -= valor;
        System.out.println("Saque de R$ " + valor + " realizado com sucesso. Novo saldo: R$ " + saldo);
    }
}

public class SistemaBancario {

    public static void main(String[] args) {
        
        ContaCorrente conta = new ContaCorrente("12345-X", 1000.00);
        
        try {
            System.out.println("Tentando sacar R$ 400.00...");
            conta.sacar(400.00);
            
            System.out.println("\nTentando sacar R$ 800.00...");
            conta.sacar(800.00);
            
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro na transação: " + e.getMessage());
        }
    }
}