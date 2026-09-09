package exercicios.exercicios06;

import java.util.Scanner;

class Circulo {

    double raio;

    double calcularArea() {
        return Math.PI * Math.pow(raio, 2);
    }

    double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Circulo circulo = new Circulo();

        System.out.print("Digite o raio: ");
        circulo.raio = sc.nextDouble();

        System.out.printf("Área: %.2f%n", circulo.calcularArea());
        System.out.printf("Perímetro: %.2f%n", circulo.calcularPerimetro());

        sc.close();
    }
}