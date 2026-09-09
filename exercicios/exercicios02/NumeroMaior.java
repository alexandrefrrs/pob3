package exercicios.exercicios02;

import java.util.Scanner;

public class NumeroMaior {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numero1, numero2, numero3;

        System.out.print("Digite o primeiro número: ");
        numero1 = sc.nextInt();

        System.out.print("Digite o segundo número: ");
        numero2 = sc.nextInt();

        System.out.print("Digite o terceiro número: ");
        numero3 = sc.nextInt();

        if (numero1 > numero2 && numero1 > numero3) {

            System.out.println("O maior número é: " + numero1);

        } 
            else if (numero2 > numero1 && numero2 > numero3) {

                System.out.println("O maior número é: " + numero2);

        } 
                else {

                System.out.println("O maior número é: " + numero3);
        }

        sc.close();
    }
}