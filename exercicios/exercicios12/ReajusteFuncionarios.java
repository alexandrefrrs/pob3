package exercicios.exercicios12;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Funcionario {

    private String nome;
    private String departamento;
    private double salario;

    Funcionario(String nome, String departamento, double salario) {
        this.nome = nome;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }
}

public class ReajusteFuncionarios {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario("Ana", "TI", 4000.00),
                new Funcionario("Carlos", "Financeiro", 3500.00),
                new Funcionario("Beatriz", "TI", 5000.00),
                new Funcionario("Antonio", "RH", 3000.00),
                new Funcionario("Amanda", "TI", 6000.00)
        );

        Predicate<Funcionario> pertenceTI = funcionario -> funcionario.getDepartamento().equals("TI");
        Function<Funcionario, Double> reajustarSalario = funcionario -> funcionario.getSalario() * 1.10;
        Consumer<Double> imprimirSalario = salario -> System.out.printf("Salário projetado: R$ %.2f%n", salario);

        System.out.println("--- Reajuste projetado de 10% para TI ---");

        funcionarios.stream()
                .filter(pertenceTI)
                .map(reajustarSalario)
                .forEach(imprimirSalario);
    }
}
