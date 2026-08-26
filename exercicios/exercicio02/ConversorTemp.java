package pob3.exercicios.Exercicio02;

import java.util.Scanner;
import java.text.DecimalFormat;

    /*Enunciado: Crie um programa que solicite uma temperatura em graus Celsius ($C$) e a 
    converta para Fahrenheit (F) utilizando a fórmula:
    
    F = (C * 1.8) + 32 

    Conceitos: Operações aritméticas com precedência de parênteses e formatação de saída.
    
    Exemplo de Entrada: 25.0  Saída esperada: 77    */

    public class ConversorTemp{
        public static void main(String[] args){

            double temperatura;

            Scanner sc = new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("#.00");

            System.out.print("Digie a temperatura em graus celsius (EX: 25.0): ");
            temperatura = sc.nextDouble();

            temperatura = (temperatura * 1.8) + 32;

            System.out.println("Temperatura em fahrenheit: " + df.format(temperatura));

            sc.close();
        }

    }