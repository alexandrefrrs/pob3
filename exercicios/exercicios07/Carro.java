package exercicios.exercicios07;

class Carro {

    private String modelo;
    private int ano;
    private int velocidadeAtual;

    Carro(String modelo, int ano) {
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void acelerar(int incremento) {
        if (incremento > 0) {
            velocidadeAtual += incremento;
        } else {
            System.out.println("Incremento inválido.");
        }
    }

    public void frear(int decremento) {
        if (decremento > 0) {
            velocidadeAtual -= decremento;

            if (velocidadeAtual < 0) {
                velocidadeAtual = 0;
            }
        } else {
            System.out.println("Decremento inválido.");
        }
    }

    public boolean isEmMovimento() {
        return velocidadeAtual > 0;
    }
}

public class Main {
    public static void main(String[] args) {

        Carro carro = new Carro("Civic", 2020);

        System.out.println("Modelo: " + carro.getModelo());
        System.out.println("Ano: " + carro.getAno());

        System.out.println("Velocidade inicial: " +
                carro.getVelocidadeAtual());

        carro.acelerar(60);

        System.out.println("Velocidade após acelerar: " +
                carro.getVelocidadeAtual());

        System.out.println("Em movimento: " +
                carro.isEmMovimento());

        carro.frear(30);

        System.out.println("Velocidade após frear: " +
                carro.getVelocidadeAtual());

        carro.frear(50);

        System.out.println("Velocidade final: " +
                carro.getVelocidadeAtual());

        System.out.println("Em movimento: " +
                carro.isEmMovimento());
    }
}