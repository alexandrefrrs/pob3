package exercicios.exercicios10;

import java.util.Scanner;

public class ConversaoVetor {

    public static void main(String[] args) {
        
        String[] valores = {"10", "25", "abc", "50"};
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Digite o índice (0 a 3) do vetor que deseja converter: ");
            int indice = sc.nextInt();
            
            int numero = Integer.parseInt(valores[indice]);
            System.out.println("Valor numérico na posição " + indice + " é: " + numero);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: O índice informado não existe no vetor.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: O valor na posição escolhida não pode ser convertido para um número inteiro.");
        } finally {
            sc.close();
        }
    }
}