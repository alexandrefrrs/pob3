package exercicios.exercicios12;

import java.util.Arrays;
import java.util.List;

public class FiltragemNomes {

    public static void main(String[] args) {

        List<String> nomes = Arrays.asList("ana", "carlos", "beatriz", "Antonio", "AMANDA", "bernardo");

        List<String> nomesFiltrados = nomes.stream()
                .filter(nome -> nome.toUpperCase().startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .toList();

        System.out.println("Nomes que começam com A em ordem alfabética: " + nomesFiltrados);
    }
}
