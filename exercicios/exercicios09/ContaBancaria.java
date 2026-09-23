package exercicios.exercicios09;

import java.util.ArrayList;
import java.util.List;

abstract class ContaBancaria {

    private String numero;
    private double saldo;

    ContaBancaria(String numero) {
        this.numero = numero;
        this.saldo = 0;
    }

    public String getNumero() {
        return numero;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor inválido.");
        }
    }

    public double consultarSaldo() {
        return saldo;
    }

    protected void descontarTaxa(double valor) {
        saldo -= valor;
    }

    public abstract void cobrarTaxaMensal();
}

class ContaCorrente extends ContaBancaria {

    ContaCorrente(String numero) {
        super(numero);
    }

    @Override
    public void cobrarTaxaMensal() {
        descontarTaxa(15);

        System.out.println("Taxa mensal de R$ 15,00 cobrada.");
    }
}

class ContaEmpresarial extends ContaBancaria {

    ContaEmpresarial(String numero) {
        super(numero);
    }

    @Override
    public void cobrarTaxaMensal() {
        double taxa = 30 + consultarSaldo() * 0.005;

        descontarTaxa(taxa);

        System.out.println("Taxa mensal de R$ " +
                taxa + " cobrada.");
    }
}

public class Main {
    public static void main(String[] args) {

        ContaCorrente corrente = new ContaCorrente("12345");

        ContaEmpresarial empresarial =
                new ContaEmpresarial("67890");

        corrente.depositar(1000);
        empresarial.depositar(2000);

        List<ContaBancaria> contas = new ArrayList<>();

        contas.add(corrente);
        contas.add(empresarial);

        for (ContaBancaria conta : contas) {

            System.out.println("Conta: " + conta.getNumero());

            System.out.println("Saldo antes da taxa: R$ " +
                    conta.consultarSaldo());

            conta.cobrarTaxaMensal();

            System.out.println("Saldo após a taxa: R$ " +
                    conta.consultarSaldo());

            System.out.println();
        }
    }
}