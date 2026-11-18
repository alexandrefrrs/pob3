package exercicios.exercicios15;

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

public class Caixa<T> {

    private T conteudo;

    public void guardar(T elemento) {
        conteudo = elemento;
    }

    public T recuperar() {
        return conteudo;
    }

    public boolean isVazia() {
        return conteudo == null;
    }

    public void limpar() {
        conteudo = null;
    }

    public static void main(String[] args) {

        Caixa<Integer> caixaNumero = new Caixa<>();
        System.out.println("Caixa de número vazia? " + caixaNumero.isVazia());
        caixaNumero.guardar(42);
        Integer numero = caixaNumero.recuperar();
        System.out.println("Número guardado: " + numero);

        Caixa<String> caixaTexto = new Caixa<>();
        caixaTexto.guardar("Aprendendo classes genéricas");
        String texto = caixaTexto.recuperar();
        System.out.println("Texto guardado: " + texto);

        Caixa<Produto> caixaProduto = new Caixa<>();
        caixaProduto.guardar(new Produto("Mouse", 60.00));
        Produto produto = caixaProduto.recuperar();
        System.out.printf("Produto guardado: %s - R$ %.2f%n", produto.getNome(), produto.getPreco());

        caixaNumero.limpar();
        System.out.println("Caixa de número vazia após limpar? " + caixaNumero.isVazia());
    }
}
