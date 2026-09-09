package exercicios.exercicios04;

import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] numeros = new int[10];

        int[] pares = new int[10];
        int[] impares = new int[10];

        int qtdPares = 0;
        int qtdImpares = 0;

        for (int i = 0; i < numeros.length; i++) {

            System.out.print("Digite o " + (i + 1) + "º número: ");
            numeros[i] = sc.nextInt();
        }

        for (int i = 0; i < numeros.length; i++) {

            if (numeros[i] % 2 == 0) {

                pares[qtdPares] = numeros[i];
                qtdPares++;

            } 
                else {

                    impares[qtdImpares] = numeros[i];
                    qtdImpares++;
                }
        }

        System.out.println("Números pares:");

        for (int i = 0; i < qtdPares; i++) {
            System.out.println(pares[i]);
        }

        System.out.println("Números ímpares:");

        for (int i = 0; i < qtdImpares; i++) {
            System.out.println(impares[i]);
        }

        sc.close();
    }
}