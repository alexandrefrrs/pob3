package exercicios.exercicios04;

import java.util.Scanner;

public class BuscaLinear {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] vetor = new int[6];

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o valor da posição " + i + ": ");
            vetor[i] = sc.nextInt();
        }

        System.out.print("Digite o número que deseja buscar: ");
        int busca = sc.nextInt();

        boolean encontrado = false;
        int posicao = -1;

        for (int i = 0; i < vetor.length; i++) {

            if (vetor[i] == busca) {
                encontrado = true;
                posicao = i;
                break;
            }
        }

        if (encontrado) {
            
            System.out.println(
                "Número encontrado na posição " + posicao
            );
        } 
            else {
                System.out.println("Número não encontrado.");
            }

        sc.close();
    }
}