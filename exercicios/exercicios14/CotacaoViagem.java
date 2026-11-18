package exercicios.exercicios14;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CotacaoViagem {

    public static double consultarServico(double valor, long atraso) {
        try {
            Thread.sleep(atraso);
            return valor;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }

    public static void main(String[] args) {

        CompletableFuture<Double> passagem = CompletableFuture.supplyAsync(
                () -> consultarServico(1200.00, 1200));

        CompletableFuture<Double> hospedagem = CompletableFuture.supplyAsync(
                () -> consultarServico(800.00, 800));

        CompletableFuture<String> pacote = passagem.thenCombine(hospedagem,
                (valorPassagem, valorHospedagem) -> String.format(
                        "Pacote de viagem - Passagem: R$ %.2f | Hospedagem: R$ %.2f | Total: R$ %.2f",
                        valorPassagem, valorHospedagem, valorPassagem + valorHospedagem))
                .exceptionally(erro -> "Não foi possível obter a cotação. Tente novamente mais tarde.");

        System.out.println("Consultando passagem e hospedagem em paralelo...");
        System.out.println(pacote.join());
    }
}
