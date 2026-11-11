package exercicios.exercicios11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequenciaPalavras {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<String, Integer> frequencias = new HashMap<>();

        System.out.print("Digite um texto: ");
        String texto = sc.nextLine().toLowerCase().trim();

        if (!texto.isEmpty()) {
            String[] palavras = texto.split("\\s+");

            for (String palavra : palavras) {
                frequencias.put(palavra, frequencias.getOrDefault(palavra, 0) + 1);
            }
        }

        System.out.println("\nFrequência das palavras:");

        for (Map.Entry<String, Integer> entrada : frequencias.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }

        sc.close();
    }
}
