package exercicios.exercicios07;

class Produto {

    private String nome;
    private double preco;
    private int quantidadeEstoque;

    Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    Produto(String nome, double preco) {
        this(nome, preco, 0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            System.out.println("O preço não pode ser negativo.");
        }
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        } else {
            System.out.println("O estoque não pode ser negativo.");
        }
    }

    public double calcularValorTotalEmEstoque() {
        return preco * quantidadeEstoque;
    }
}

public class Main {
    public static void main(String[] args) {

        Produto produtoA = new Produto("Camisa", 100, 10);
        Produto produtoB = new Produto("Calça", 150);

        System.out.println("Produto A: " + produtoA.getNome());
        System.out.println("Preço: R$ " + produtoA.getPreco());
        System.out.println("Estoque: " + produtoA.getQuantidadeEstoque());

        System.out.println("Valor total: R$ " +
                produtoA.calcularValorTotalEmEstoque());

        System.out.println();

        System.out.println("Produto B: " + produtoB.getNome());
        System.out.println("Estoque: " + produtoB.getQuantidadeEstoque());

        produtoA.setPreco(-10);

        System.out.println("Preço após tentativa de alteração: R$ " +
                produtoA.getPreco());
    }
}