package exercicios.exercicios07;

class Funcionario {

    private String nome;
    private String matricula;
    private double salario;

    Funcionario(String nome, String matricula, double salario) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double novoSalario) {
        if (novoSalario > salario) {
            salario = novoSalario;
        } else {
            System.out.println("Erro: o novo salário deve ser maior que o atual.");
        }
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário: R$ " + salario);
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {

        Funcionario funcionario =
                new Funcionario("Carlos", "12345", 3000);

        System.out.println("Dados iniciais:");
        funcionario.exibirDados();

        funcionario.setSalario(3500);

        System.out.println("Após aumento salarial:");
        funcionario.exibirDados();

        funcionario.setSalario(2500);

        System.out.println("Após tentativa de redução:");
        funcionario.exibirDados();

        funcionario.setSalario(3500);

        System.out.println("Após tentativa de manter o mesmo salário:");
        funcionario.exibirDados();
    }
}