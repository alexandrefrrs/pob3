package exercicios.exercicios02;

import java.util.Scanner;

public class Maioridade {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int anoNascimento, anoAtual, idade;

        System.out.print("Digite o ano de nascimento: ");
        anoNascimento = sc.nextInt();

        System.out.print("Digite o ano atual: ");
        anoAtual = sc.nextInt();

        idade = anoAtual - anoNascimento;

        System.out.println("Idade: " + idade + " anos");

        if (idade >= 18) {

            System.out.println("Maior de idade");

        } 
            else {

                System.out.println("Menor de idade");
             }

        sc.close();
    }
}