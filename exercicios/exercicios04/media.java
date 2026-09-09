package exercicios.exercicios04;

import java.util.Scanner;

public class Media {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] notas = new double[8];

        double soma = 0;

        for (int i = 0; i < notas.length; i++) {

            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = sc.nextDouble();

            soma += notas[i];
        }

        double media = soma / notas.length;

        System.out.println("Média da turma: " + media);

        System.out.println("Alunos acima da média:");

        for (int i = 0; i < notas.length; i++) {

            if (notas[i] > media) {
                
                System.out.println(
                    "Aluno " + (i + 1) + ": " + notas[i]
                );
            }
        }

        sc.close();
    }
}