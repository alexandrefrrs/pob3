package exercicios.exercicios13;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class PadronizacaoClientes {

    public static void main(String[] args) {

        Path entrada = Path.of("exercicios", "exercicios13", "clientes_bruto.txt");
        Path saida = Path.of("exercicios", "exercicios13", "clientes_padronizado.txt");

        try {
            List<String> clientes;

            try (Stream<String> linhas = Files.lines(entrada)) {
                clientes = linhas
                        .map(String::trim)
                        .filter(linha -> !linha.isBlank())
                        .map(String::toUpperCase)
                        .toList();
            }

            Files.write(saida, clientes,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Clientes padronizados: " + clientes.size());
            System.out.println("Arquivo gravado em " + saida);
        } catch (IOException | UncheckedIOException e) {
            System.out.println("Erro ao processar os clientes: " + e.getMessage());
        }
    }
}
