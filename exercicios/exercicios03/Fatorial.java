package exercicios.exercicios03;

import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numero;
        long fatorial = 1;

        System.out.print("Digite um número não negativo: ");
        numero = sc.nextInt();

        if (numero < 0) {

            System.out.println("Erro: o número não pode ser negativo.");

        } else {

            for (int i = 1; i <= numero; i++) {
                fatorial = fatorial * i;
            }

            System.out.println(numero + "! = " + fatorial);
        }

        sc.close();
    }
}