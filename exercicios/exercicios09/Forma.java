package exercicios.exercicios09;

abstract class Forma {

    protected String cor;

    Forma(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public abstract double calcularArea();

    public void exibirCor() {
        System.out.println("Cor da forma: " + cor);
    }
}

class Retangulo extends Forma {

    private double largura;
    private double altura;

    Retangulo(String cor, double largura, double altura) {
        super(cor);
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return largura * altura;
    }
}

class Circulo extends Forma {

    private double raio;

    Circulo(String cor, double raio) {
        super(cor);
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}

public class Main {
    public static void main(String[] args) {

        Retangulo retangulo = new Retangulo("Azul", 5, 10);
        Circulo circulo = new Circulo("Vermelho", 3);

        System.out.println("Retângulo:");
        retangulo.exibirCor();

        System.out.println("Área: " +
                retangulo.calcularArea());

        System.out.println();

        System.out.println("Círculo:");
        circulo.exibirCor();

        System.out.println("Área: " +
                circulo.calcularArea());
    }
}