package exercicios.exercicios05;

import java.util.Scanner;

public class MaiorNum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] matriz = new int[3][4];


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.print("Digite o valor [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextInt();
            }
        }

        int maior = matriz[0][0];
        int linhaMaior = 0;
        int colunaMaior = 0;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {

                if (matriz[i][j] > maior) {

                    maior = matriz[i][j];
                    linhaMaior = i;
                    colunaMaior = j;
                }
            }
        }

        System.out.println("Maior valor: " + maior);
        System.out.println("Linha: " + linhaMaior);
        System.out.println("Coluna: " + colunaMaior);

        sc.close();
    }
}