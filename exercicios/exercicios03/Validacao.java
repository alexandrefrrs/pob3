package exercicios.exercicios03;

import java.util.Scanner;

public class Validacao {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int senhaCorreta = 2026;
        int senha;

        System.out.print("Digite a senha: ");
        senha = sc.nextInt();

        while (senha != senhaCorreta) {

            System.out.println("Senha Incorreta! Tente novamente.");

            System.out.print("Digite a senha: ");
            senha = sc.nextInt();
        }

        System.out.println("Acesso Permitido!");

        sc.close();
    }
}