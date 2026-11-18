package exercicios.exercicios15;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraEstatistica<T extends Number> {

    private List<T> numeros = new ArrayList<>();

    public void adicionar(T numero) {
        if (numero == null) {
            throw new IllegalArgumentException("O número não pode ser nulo.");
        }

        numeros.add(numero);
    }

    public double calcularMedia() {
        if (numeros.isEmpty()) {
            throw new IllegalStateException("Não há números para calcular a média.");
        }

        double soma = 0;

        for (T numero : numeros) {
            soma += numero.doubleValue();
        }

        return soma / numeros.size();
    }

    public double calcularMaior() {
        if (numeros.isEmpty()) {
            throw new IllegalStateException("Não há números para calcular o maior valor.");
        }

        double maior = numeros.get(0).doubleValue();

        for (T numero : numeros) {
            maior = Math.max(maior, numero.doubleValue());
        }

        return maior;
    }

    public static void main(String[] args) {

        CalculadoraEstatistica<Integer> inteiros = new CalculadoraEstatistica<>();
        inteiros.adicionar(10);
        inteiros.adicionar(20);
        inteiros.adicionar(30);

        CalculadoraEstatistica<Double> decimais = new CalculadoraEstatistica<>();
        decimais.adicionar(5.5);
        decimais.adicionar(7.5);
        decimais.adicionar(9.5);

        System.out.println("Média dos inteiros: " + inteiros.calcularMedia());
        System.out.println("Maior inteiro: " + inteiros.calcularMaior());
        System.out.println("Média dos decimais: " + decimais.calcularMedia());
        System.out.println("Maior decimal: " + decimais.calcularMaior());

        // Não compila: String não é uma subclasse de Number.
        // CalculadoraEstatistica<String> textos = new CalculadoraEstatistica<>();
    }
}
