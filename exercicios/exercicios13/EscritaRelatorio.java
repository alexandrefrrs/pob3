package exercicios.exercicios13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EscritaRelatorio {

    public static void main(String[] args) {

        Path diretorio = Path.of("dados", "relatorios");
        Path arquivo = diretorio.resolve("sumario.txt");

        try {
            Files.createDirectories(diretorio);

            String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            String relatorio = "--- Sumário da execução ---\n"
                    + "Data e hora: " + horario + "\n"
                    + "Sistema iniciado com sucesso.\n"
                    + "Relatório gerado sem erros.\n";

            Files.writeString(arquivo, relatorio,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Arquivo criado em " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o relatório: " + e.getMessage());
        }
    }
}
