package exercicios.exercicios15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopiaElementos {

    public static <T> void copiarElementos(List<? extends T> origem, List<? super T> destino) {
        // Producer Extends: podemos ler cada elemento da origem como T.
        for (T elemento : origem) {
            // Consumer Super: o destino aceita T, pois armazena T ou um supertipo de T.
            destino.add(elemento);
        }
    }

    public static void main(String[] args) {

        List<Integer> inteiros = Arrays.asList(10, 20, 30);
        List<Number> numeros = new ArrayList<>();
        List<Object> objetos = new ArrayList<>();

        numeros.add(5.5);
        objetos.add("Texto já existente");

        copiarElementos(inteiros, numeros);
        copiarElementos(inteiros, objetos);

        System.out.println("Origem (Integer): " + inteiros);
        System.out.println("Destino (Number): " + numeros);
        System.out.println("Destino (Object): " + objetos);
    }
}
