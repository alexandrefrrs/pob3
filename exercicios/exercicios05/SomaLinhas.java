package exercicios.exercicios05;

import java.util.Scanner;

public class SomaLinhas {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] matriz = new int[4][3];
        int[] somaLinhas = new int[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print("Digite o valor [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 4; i++) {

            int soma = 0;

            for (int j = 0; j < 3; j++) {
                soma += matriz[i][j];
            }

            somaLinhas[i] = soma;
        }

        for (int i = 0; i < somaLinhas.length; i++) {
            
            System.out.println("Soma da linha " + i + ": " + somaLinhas[i]);
        }

        sc.close();
    }
}