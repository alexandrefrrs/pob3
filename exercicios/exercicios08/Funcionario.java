package exercicios.exercicios08;

class Funcionario {

    private String nome;
    private double salarioBase;

    Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public double calcularSalario() {
        return salarioBase;
    }
}

class Gerente extends Funcionario {

    private double bonusFixo;

    Gerente(String nome, double salarioBase, double bonusFixo) {
        super(nome, salarioBase);
        this.bonusFixo = bonusFixo;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + bonusFixo;
    }
}

class Vendedor extends Funcionario {

    private double totalVendas;
    private double comissaoPercentual;

    Vendedor(String nome, double salarioBase,
             double totalVendas, double comissaoPercentual) {

        super(nome, salarioBase);
        this.totalVendas = totalVendas;
        this.comissaoPercentual = comissaoPercentual;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() +
                totalVendas * comissaoPercentual / 100;
    }
}

public class Main {
    public static void main(String[] args) {

        Funcionario[] funcionarios = {
            new Funcionario("Carlos", 3000),
            new Gerente("Ana", 5000, 1500),
            new Vendedor("Pedro", 2000, 10000, 5)
        };

        double folhaTotal = 0;

        for (Funcionario funcionario : funcionarios) {

            double salario = funcionario.calcularSalario();

            System.out.println("Funcionário: " +
                    funcionario.getNome());

            System.out.println("Salário: R$ " + salario);
            System.out.println();

            folhaTotal += salario;
        }

        System.out.println("Total da folha de pagamento: R$ " +
                folhaTotal);
    }
}