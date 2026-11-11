package exercicios.exercicios12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

class Produto {

    private String nome;
    private double preco;

    Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

public class EstatisticasProdutos {

    public static void main(String[] args) {

        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 60.00),
                new Produto("Teclado", 140.00),
                new Produto("Monitor", 900.00),
                new Produto("Impressora", 700.00),
                new Produto("Fone", 100.00)
        );

        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        OptionalDouble media = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .average();

        Optional<Produto> maisCaro = produtos.stream()
                .max(Comparator.comparingDouble(Produto::getPreco));

        System.out.printf("Valor total dos produtos: R$ %.2f%n", total);
        media.ifPresent(valor -> System.out.printf("Preço médio: R$ %.2f%n", valor));
        maisCaro.ifPresent(produto -> System.out.printf(
                "Produto mais caro: %s - R$ %.2f%n", produto.getNome(), produto.getPreco()));
    }
}
