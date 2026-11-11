package exercicios.exercicios12;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface TransformadorTexto {

    String transformar(String entrada);
}

public class TransformacaoTextos {

    public static List<String> processarLista(List<String> lista, TransformadorTexto regra) {
        return lista.stream()
                .map(regra::transformar)
                .toList();
    }

    public static void main(String[] args) {

        List<String> textos = Arrays.asList("java", "coleções", "streams");

        List<String> invertidos = processarLista(textos,
                texto -> new StringBuilder(texto).reverse().toString());

        List<String> entreColchetes = processarLista(textos,
                texto -> "[" + texto + "]");

        List<String> maiusculos = processarLista(textos, String::toUpperCase);

        System.out.println("Textos invertidos: " + invertidos);
        System.out.println("Textos entre colchetes: " + entreColchetes);
        System.out.println("Textos em caixa alta: " + maiusculos);
    }
}
