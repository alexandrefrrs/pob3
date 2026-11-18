package exercicios.exercicios15;

import java.util.Arrays;
import java.util.List;

abstract class Figura {

    public abstract double calcularArea();
}

class Quadrado extends Figura {

    private double lado;

    Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}

class Circulo extends Figura {

    private double raio;

    Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}

public class SomaAreas {

    public static double somarAreas(List<? extends Figura> listaFiguras) {
        double total = 0;

        // Producer Extends: a lista fornece elementos que podem ser lidos como Figura.
        for (Figura figura : listaFiguras) {
            total += figura.calcularArea();
        }

        // Não compila: a lista recebida poderia ser uma List<Circulo>.
        // listaFiguras.add(new Quadrado(2));
        return total;
    }

    public static void main(String[] args) {

        List<Quadrado> quadrados = Arrays.asList(new Quadrado(2), new Quadrado(3));
        List<Circulo> circulos = Arrays.asList(new Circulo(1), new Circulo(2));
        List<Figura> figuras = Arrays.asList(new Quadrado(4), new Circulo(3));

        System.out.printf("Área total dos quadrados: %.2f%n", somarAreas(quadrados));
        System.out.printf("Área total dos círculos: %.2f%n", somarAreas(circulos));
        System.out.printf("Área total das figuras: %.2f%n", somarAreas(figuras));
    }
}
