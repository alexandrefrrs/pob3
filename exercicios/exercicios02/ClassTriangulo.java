package exercicios.exercicios02;

import java.util.Scanner;

public class ClassTriangulo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double a, b, c;

        System.out.print("Digite o lado A: ");
        a = sc.nextDouble();

        System.out.print("Digite o lado B: ");
        b = sc.nextDouble();

        System.out.print("Digite o lado C: ");
        c = sc.nextDouble();

        if (a <= 0 || b <= 0 || c <= 0) {

            System.out.println("os lados devem ser maiores que zero.");

        } 
            else if (a + b <= c || a + c <= b || b + c <= a) {

                System.out.println("as medidas não formam um triângulo.");

            }   
                else {

            if (a == b && b == c) {

                System.out.println("Triângulo equilátero");

            } 
                else if (a == b || a == c || b == c) {

                    System.out.println("Triângulo isósceles");

            } 
                    else {

                        System.out.println("Triângulo escaleno");
                    }
                }

        sc.close();
    }
}