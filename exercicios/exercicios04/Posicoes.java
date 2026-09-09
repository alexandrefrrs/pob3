package exercicios.exercicios04;

import java.util.Scanner;

public class Posicoes {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] vetor = new int[10];

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o valor da posição " + i + ": ");
            vetor[i] = sc.nextInt();
        }

        int maior = vetor[0];
        int menor = vetor[0];

        int posicaoMaior = 0;
        int posicaoMenor = 0;

        for (int i = 1; i < vetor.length; i++) {

            if (vetor[i] > maior) {
                maior = vetor[i];
                posicaoMaior = i;
            }

            if (vetor[i] < menor) {
                menor = vetor[i];
                posicaoMenor = i;
            }
        }

        System.out.println("Maior valor: " + maior);
        System.out.println("Posição do maior: " + posicaoMaior);

        System.out.println("Menor valor: " + menor);
        System.out.println("Posição do menor: " + posicaoMenor);

        sc.close();
    }
}