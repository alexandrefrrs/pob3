package exercicios.exercicio04;

import java.util.Scanner;
import java.text.DecimalFormat;

    /* Enunciado: Faça um programa que leia a base e a altura de um retângulo. 
    Em seguida, calcule e mostre:
    
    A área (base * altura)
    
    O perímetro (2 * (base + altura))
    
    Conceitos: Atribuição de múltiplas saídas a partir dos mesmos dados de entrada.
    
    Exemplo de Entrada: Base: 5.0, Altura: 3.0 
    Saída esperada: Área: 15.0, Perímetro: 16.0

    */

    public class AreaRetangulo{
        public static void main(String[] args){

            Scanner sc = new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("#.00");

            double base;
            double altura;
            double area;
            double perimetro;

            System.out.print("Digite o tamanho da base (EX: 5): ");
            base = sc.nextDouble();

            System.out.print("Digite o tamanho da altura (EX: 3): ");
            altura = sc.nextDouble();

            area = base * altura;
            perimetro = 2 * (base + altura);

            System.out.println("Area: " + df.format(area));
            System.out.println("perimetro: " + df.format(perimetro));
        }
    }