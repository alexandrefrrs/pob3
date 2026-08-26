package exercicios.exercicio01;

import java.util.Scanner;
import java.text.DecimalFormat;

    /*Exercício 1: Calculadora de Média Simples
    Enunciado: Escreva um programa em Java que leia três notas de um aluno 
    (números decimais), calcule e exiba a média aritmética simples dessas notas.

    Conceitos: Leitura de dados (Scanner), variáveis do tipo double, operadores 
    aritméticos de soma e divisão.

    Exemplo de Entrada: 7.5, 8.0, 6.5 Saída esperada: Média: 7.33*/

public class CalculoMedia {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");

        double media;
        double notaSomada;


        System.out.print("Digite a primeira nota do aluno: ");
        notaSomada = sc.nextDouble();

        System.out.print("Digite a segunda nota do aluno: ");
        notaSomada += sc.nextDouble();

        System.out.print("Digite a terceira nota do aluno: ");
        notaSomada += sc.nextDouble();

        media = notaSomada / 3;

        System.out.println("Media: " + df.format(media));

        sc.close();


    }
}
