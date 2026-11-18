package exercicios.exercicios14;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

class Pedido {

    private int id;
    private double total;

    Pedido(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }
}

public class CheckoutAssincrono {

    public static void main(String[] args) {

        int idPedido = 1;

        CompletableFuture<Void> checkout = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CompletionException(e);
            }

            return new Pedido(idPedido, 200.00);
        }).thenApply(pedido -> {
            double frete = 25.00;
            return new Pedido(pedido.getId(), pedido.getTotal() + frete);
        }).thenAccept(pedido -> {
            System.out.printf("Pedido %d confirmado! Total com frete: R$ %.2f%n",
                    pedido.getId(), pedido.getTotal());
            System.out.println("Simulação: confirmação enviada por e-mail.");
        });

        System.out.println("Thread principal: atualizando a tela da loja...");
        System.out.println("Thread principal: exibindo recomendações de produtos...");

        // Aguarda apenas ao final para manter o programa ativo até a conclusão.
        try {
            checkout.join();
        } catch (CompletionException e) {
            System.out.println("Erro ao finalizar o pedido: " + e.getCause().getMessage());
        }
    }
}
