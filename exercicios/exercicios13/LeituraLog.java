package exercicios.exercicios13;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class LeituraLog {

    public static void main(String[] args) {

        Path arquivo = Path.of("exercicios", "exercicios13", "servidor.log");

        try (Stream<String> linhas = Files.lines(arquivo)) {
            List<String> erros = linhas
                    .filter(linha -> linha.startsWith("[ERROR]"))
                    .map(linha -> linha.substring("[ERROR]".length()).trim())
                    .toList();

            System.out.println("Total de erros encontrados: " + erros.size());
            erros.forEach(System.out::println);
        } catch (IOException | UncheckedIOException e) {
            System.out.println("Erro ao ler o arquivo de log: " + e.getMessage());
        }
    }
}
