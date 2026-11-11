package exercicios.exercicios13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InventarioCSV {

    public static void main(String[] args) {

        Path arquivo = Path.of("exercicios", "exercicios13", "produtos.csv");

        try {
            List<String> linhas = Files.readAllLines(arquivo);
            double totalInventario = 0;

            for (int i = 1; i < linhas.size(); i++) {
                String[] campos = linhas.get(i).split(",", -1);

                if (campos.length != 3 || campos[0].trim().isEmpty()) {
                    System.out.println("Linha " + (i + 1) + " ignorada: formato inválido.");
                    continue;
                }

                try {
                    String nome = campos[0].trim();
                    int quantidade = Integer.parseInt(campos[1].trim());
                    double precoUnitario = Double.parseDouble(campos[2].trim());

                    if (quantidade < 0 || precoUnitario < 0 || !Double.isFinite(precoUnitario)) {
                        System.out.println("Linha " + (i + 1) + " ignorada: quantidade ou preço inválido.");
                        continue;
                    }

                    double totalItem = quantidade * precoUnitario;
                    totalInventario += totalItem;
                    System.out.printf("%s: %d unidades - Total: R$ %.2f%n", nome, quantidade, totalItem);
                } catch (NumberFormatException e) {
                    System.out.println("Linha " + (i + 1) + " ignorada: valor numérico inválido.");
                }
            }

            System.out.printf("Valor global do inventário (linhas válidas): R$ %.2f%n", totalInventario);
        } catch (IOException e) {
            System.out.println("Erro ao ler o inventário: " + e.getMessage());
        }
    }
}
