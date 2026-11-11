package exercicios.exercicios13;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class VarreduraDiretorios {

    public static void main(String[] args) {

        Path diretorio = Path.of("exercicios");

        try (Stream<Path> caminhos = Files.walk(diretorio)) {
            List<Path> arquivosJava = caminhos
                    .filter(Files::isRegularFile)
                    .filter(caminho -> caminho.getFileName().toString().endsWith(".java"))
                    .sorted()
                    .toList();

            long tamanhoTotal = 0;

            for (Path arquivo : arquivosJava) {
                long tamanho = Files.size(arquivo);
                tamanhoTotal += tamanho;
                System.out.println(diretorio.relativize(arquivo) + ": " + tamanho + " bytes");
            }

            System.out.println("Tamanho total dos arquivos Java: " + tamanhoTotal + " bytes");
        } catch (IOException | UncheckedIOException e) {
            System.out.println("Erro ao percorrer o diretório: " + e.getMessage());
        }
    }
}
