package exercicios.exercicios08;

class Veiculo {

    private String marca;
    private String modelo;

    Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void exibirDetalhes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
    }
}

class Carro extends Veiculo {

    private int quantidadePortas;

    Carro(String marca, String modelo, int quantidadePortas) {
        super(marca, modelo);
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Quantidade de portas: " + quantidadePortas);
    }
}

class Moto extends Veiculo {

    private int cilindradas;

    Moto(String marca, String modelo, int cilindradas) {
        super(marca, modelo);
        this.cilindradas = cilindradas;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Cilindradas: " + cilindradas);
    }
}

public class Main {
    public static void main(String[] args) {

        Carro carro = new Carro("Honda", "Civic", 4);
        Moto moto = new Moto("Yamaha", "Fazer", 250);

        System.out.println("Dados do carro:");
        carro.exibirDetalhes();

        System.out.println();

        System.out.println("Dados da moto:");
        moto.exibirDetalhes();
    }
}