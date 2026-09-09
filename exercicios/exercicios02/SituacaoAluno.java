package exercicios.exercicios02;

import java.util.Scanner;

public class SituacaoAluno {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double nota1, nota2, media;

        System.out.print("Digite a primeira nota: ");
        nota1 = sc.nextDouble();

        System.out.print("Digite a segunda nota: ");
        nota2 = sc.nextDouble();

        media = (nota1 + nota2) / 2;

        System.out.println("Média: " + media);

        if (media >= 7.0) {
            System.out.println("Aprovado");

        } else if (media >= 5.0 && media <= 6.9) {

            System.out.println("Recuperação");     
        } 

            else {
                System.out.println("Reprovado");
         }

        sc.close();
    }
}