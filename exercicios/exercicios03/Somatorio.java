package exercicios.exercicios03;

import java.util.Scanner;

public class Somatorio {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numero;
        int soma = 0;
        int quantidade = 0;

        System.out.print("Digite um número positivo ou negativo para encerrar: ");
        numero = sc.nextInt();

        while (numero >= 0) {

            soma = soma + numero;
            quantidade++;

            System.out.print("Digite outro número: ");
            numero = sc.nextInt();
        }

        System.out.println("Quantidade de números: " + quantidade);
        System.out.println("Soma: " + soma);

        if (quantidade > 0) {

            double media = (double) soma / quantidade;
            System.out.println("Média: " + media);

        } 
            else {
                System.out.println("Não foi possível calcular a média.");
            }

        sc.close();
    }
}