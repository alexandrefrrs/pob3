package exercicios.exercicios11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListaTarefas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<String> tarefas = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            System.out.print("Digite a tarefa " + (i + 1) + ": ");
            tarefas.add(sc.nextLine());
        }

        System.out.print("Digite o nome da tarefa que deseja remover: ");
        String tarefaRemover = sc.nextLine();

        if (tarefas.remove(tarefaRemover)) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }

        System.out.print("Digite o nome da tarefa que deseja buscar: ");
        String tarefaBuscar = sc.nextLine();

        if (tarefas.contains(tarefaBuscar)) {
            System.out.println("A tarefa existe na lista.");
        } else {
            System.out.println("A tarefa não existe na lista.");
        }

        Collections.sort(tarefas);
        System.out.println("\nTarefas em ordem alfabética:");

        for (String tarefa : tarefas) {
            System.out.println(tarefa);
        }

        sc.close();
    }
}
