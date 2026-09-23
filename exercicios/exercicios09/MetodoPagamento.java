package exercicios.exercicios09;

interface MetodoPagamento {

    void processarPagamento(double valor);

    String obterDetalhes();
}

class CartaoCredito implements MetodoPagamento {

    private String numeroCartao;
    private double limite;

    CartaoCredito(String numeroCartao, double limite) {
        this.numeroCartao = numeroCartao;
        this.limite = limite;
    }

    @Override
    public void processarPagamento(double valor) {
        if (valor > 0 && valor <= limite) {
            limite -= valor;
            System.out.println("Pagamento de R$ " + valor +
                    " realizado no cartão.");
        } else {
            System.out.println("Pagamento inválido ou limite insuficiente.");
        }
    }

    @Override
    public String obterDetalhes() {
        return "Cartão: " + numeroCartao +
                " | Limite disponível: R$ " + limite;
    }
}

class Pix implements MetodoPagamento {

    private String chavePix;

    Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento(double valor) {
        if (valor > 0) {
            System.out.println("Pagamento de R$ " + valor +
                    " realizado via Pix.");
        } else {
            System.out.println("Valor inválido.");
        }
    }

    @Override
    public String obterDetalhes() {
        return "Chave Pix: " + chavePix;
    }
}

public class Main {

    public static void finalizarCompra(MetodoPagamento metodo,
                                      double total) {

        System.out.println(metodo.obterDetalhes());
        metodo.processarPagamento(total);
    }

    public static void main(String[] args) {

        CartaoCredito cartao =
                new CartaoCredito("123456789", 2000);

        Pix pix = new Pix("carlos@gmail.com");

        finalizarCompra(cartao, 500);

        System.out.println();

        finalizarCompra(pix, 150);
    }
}