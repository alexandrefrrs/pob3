package exercicios.exercicios11;

import java.util.HashMap;
import java.util.Map;

public class ControleEstoque {

    private Map<String, Integer> produtos = new HashMap<>();

    public boolean consultarProduto(String codigo) {
        return produtos.containsKey(codigo);
    }

    public void inserirProduto(String codigo, int quantidade) {
        if (consultarProduto(codigo)) {
            System.out.println("O produto " + codigo + " já está cadastrado.");
            return;
        }

        if (quantidade < 0) {
            System.out.println("A quantidade em estoque não pode ser negativa.");
            return;
        }

        produtos.put(codigo, quantidade);
        System.out.println("Produto " + codigo + " cadastrado com " + quantidade + " unidades.");
    }

    public void atualizarEstoque(String codigo, int quantidade) {
        if (!consultarProduto(codigo)) {
            System.out.println("Produto " + codigo + " não encontrado.");
            return;
        }

        int novoEstoque = produtos.get(codigo) + quantidade;

        if (novoEstoque < 0) {
            System.out.println("Estoque insuficiente para retirar essa quantidade.");
            return;
        }

        produtos.put(codigo, novoEstoque);
        System.out.println("Estoque de " + codigo + " atualizado para " + novoEstoque + " unidades.");
    }

    public void exibirEstoqueBaixo(int valorAlerta) {
        System.out.println("\nProdutos com estoque zerado ou abaixo de " + valorAlerta + " unidades:");

        for (Map.Entry<String, Integer> produto : produtos.entrySet()) {
            if (produto.getValue() == 0 || produto.getValue() < valorAlerta) {
                System.out.println(produto.getKey() + ": " + produto.getValue() + " unidades");
            }
        }
    }

    public static void main(String[] args) {

        ControleEstoque estoque = new ControleEstoque();

        estoque.inserirProduto("P001", 10);
        estoque.inserirProduto("P002", 3);
        estoque.inserirProduto("P003", 2);
        estoque.inserirProduto("P004", 0);

        estoque.atualizarEstoque("P001", -7);
        estoque.atualizarEstoque("P002", 5);
        estoque.atualizarEstoque("P003", -2);
        estoque.atualizarEstoque("P005", 4);

        System.out.println("Produto P001 cadastrado? " + estoque.consultarProduto("P001"));
        estoque.exibirEstoqueBaixo(5);
    }
}
