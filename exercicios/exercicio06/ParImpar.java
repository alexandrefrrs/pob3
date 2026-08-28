package exercicios.exercicio06;

import java.util.Scanner;

    /*
    Enunciado: Escreva um programa em Java que leia um número inteiro e 
    informe se ele é par ou ímpar.

    Conceitos: Operador módulo (%) e estrutura if-else simples.
    */

    public class ParImpar{
        public static void main(String[] args){

            Scanner sc = new Scanner(System.in);
            int num;

            System.out.print("Digite um numero inteiro: ");
            num = sc.nextInt();

            if(num % 2 == 0){

                System.out.println("Par");
            }
                else {

                    System.out.println("Impar");
                }

                sc.close();
        }
    }