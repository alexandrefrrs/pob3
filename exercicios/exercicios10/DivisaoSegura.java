package exercicios.exercicios10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisaoSegura {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Digite o primeiro número inteiro: ");
            int num1 = sc.nextInt();
            
            System.out.print("Digite o segundo número inteiro: ");
            int num2 = sc.nextInt();
            
            int resultado = num1 / num2;
            System.out.println("Resultado da divisão: " + resultado);
            
        } catch (ArithmeticException e) {
            System.out.println("Erro: Não é possível realizar divisão por zero.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, digite apenas números inteiros.");
        } finally {
            System.out.println("Operação finalizada.");
            sc.close();
        }
    }
}