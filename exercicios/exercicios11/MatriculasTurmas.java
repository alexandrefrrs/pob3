package exercicios.exercicios11;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MatriculasTurmas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Set<Integer> turmaA = new HashSet<>();
        Set<Integer> turmaB = new HashSet<>();

        System.out.print("Quantas matrículas deseja informar para a Turma A? ");
        int quantidadeA = sc.nextInt();

        for (int i = 0; i < quantidadeA; i++) {
            System.out.print("Digite a matrícula " + (i + 1) + " da Turma A: ");
            turmaA.add(sc.nextInt());
        }

        System.out.print("Quantas matrículas deseja informar para a Turma B? ");
        int quantidadeB = sc.nextInt();

        for (int i = 0; i < quantidadeB; i++) {
            System.out.print("Digite a matrícula " + (i + 1) + " da Turma B: ");
            turmaB.add(sc.nextInt());
        }

        Set<Integer> uniao = new HashSet<>(turmaA);
        uniao.addAll(turmaB);

        Set<Integer> intersecao = new HashSet<>(turmaA);
        intersecao.retainAll(turmaB);

        System.out.println("\nTurma A sem duplicatas: " + turmaA);
        System.out.println("Turma B sem duplicatas: " + turmaB);
        System.out.println("União das turmas: " + uniao);
        System.out.println("Interseção das turmas: " + intersecao);

        sc.close();
    }
}
