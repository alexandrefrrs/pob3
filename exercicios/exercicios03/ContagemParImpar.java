package exercicios.exercicios03;

import java.util.Scanner;

public class ContagemParImpar {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a, b;
        int pares = 0;
        int impares = 0;

        System.out.print("Digite o início do intervalo: ");
        a = sc.nextInt();

        System.out.print("Digite o fim do intervalo: ");
        b = sc.nextInt();

        for (int i = a; i <= b; i++) {

            if (i % 2 == 0) {

                pares++;

            } 
                else {
                    impares++;
                }
        }

        System.out.println("Quantidade de números pares: " + pares);
        System.out.println("Quantidade de números ímpares: " + impares);

        sc.close();
    }
}