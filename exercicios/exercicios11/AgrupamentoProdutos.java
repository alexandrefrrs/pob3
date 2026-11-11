package exercicios.exercicios11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Produto {

    private String nome;
    private String categoria;
    private double preco;

    Produto(String nome, String categoria, double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }
}

public class AgrupamentoProdutos {

    public static void main(String[] args) {

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Notebook", "Informática", 3500.00));
        produtos.add(new Produto("Arroz", "Alimentos", 25.00));
        produtos.add(new Produto("Java para Iniciantes", "Livros", 80.00));
        produtos.add(new Produto("Mouse", "Informática", 60.00));
        produtos.add(new Produto("Feijão", "Alimentos", 9.00));
        produtos.add(new Produto("Estruturas de Dados", "Livros", 95.00));

        Map<String, List<Produto>> categorias = new HashMap<>();

        for (Produto produto : produtos) {
            String categoria = produto.getCategoria();

            if (!categorias.containsKey(categoria)) {
                categorias.put(categoria, new ArrayList<>());
            }

            categorias.get(categoria).add(produto);
        }

        for (Map.Entry<String, List<Produto>> entrada : categorias.entrySet()) {
            System.out.println("\n--- " + entrada.getKey() + " ---");

            for (Produto produto : entrada.getValue()) {
                System.out.printf("%s - R$ %.2f%n", produto.getNome(), produto.getPreco());
            }
        }
    }
}
