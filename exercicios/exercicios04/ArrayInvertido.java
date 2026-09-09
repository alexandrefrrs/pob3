package exercicios.exercicios04;

import java.util.Scanner;

public class ArrayInvertido {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] vetor = new int[5];

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o " + (i + 1) + "º valor: ");
            vetor[i] = sc.nextInt();
        }

        System.out.println("Valores na ordem inversa:");

        for (int i = vetor.length - 1; i >= 0; i--) {
            System.out.println(vetor[i]);
        }

        sc.close();
    }
}