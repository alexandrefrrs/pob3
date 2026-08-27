package exercicios.exercicio05;

import java.util.Scanner;
import java.text.DecimalFormat;

    /* Enunciado: Escreva um programa que leia um valor inteiro representando um total de segundos e o decomponha em horas, 
    minutos e segundos restantes.

    Conceitos: Divisão inteira (/) e operador de resto da divisão (%).

    Exemplo de Entrada: 3665 Saída esperada: 1 hora(s), 1 minuto(s) e 5 segundo(s)
    */

    public class ConversorTempo {
        public static void main(String[] args){

            Scanner sc = new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("#.00");

            int segundos;
            int minutos;
            int horas;
            int resto;


            System.out.print("Digite o tempo em segundos: ");
            segundos = sc.nextInt();

            horas = segundos / 3600;
            resto = segundos % 3600;
            minutos = resto / 60;
            resto = resto % 60;
            segundos = resto;

            System.out.println(df.format(horas) + " hora(s)," + df.format(minutos) + " minuto(s) e " + df.format(segundos) + "segundo(s)");
        }
    }
