package pob3.exercicios.Exercicio03;

import java.util.Scanner;
import java.text.DecimalFormat;

    /* Enunciado: Desenvolva um programa que receba o valor da hora de trabalho de um 
    funcionário e a quantidade de horas trabalhadas no mês. O programa deve calcular o 
    salário bruto, descontar uma taxa fixa de 10% referente ao INSS e exibir o 
    salário líquido final.
    
    Conceitos: Multiplicação, cálculo percentual e variáveis intermediárias.
    
    Exemplo de Entrada: Valor da hora: 50.00, Horas: 160  
    Saída esperada: Salário Bruto: R$ 8000.00, Salário Líquido: R$ 7200.00
    */

public class DescontoInss {
    public static void main(string[] args){

            double valorHora;
            double horas;
            double total;

            System.out.print("Digite o valor da hora: ");
            valorHora = sc.nextDouble();
            System.out.print("Digite quantas horas trabalhadas no mes: ");
            horas = sc.nextDouble();

            total = (horas * valorHora);
            total = total - (total / 10);

            System.out.println("Total = " + df.format(total));

        sc.close();
    }
}
