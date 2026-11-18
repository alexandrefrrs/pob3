package exercicios.exercicios16;

enum StatusPedido {
    PENDENTE, PAGO
}

class Pedido {

    private double valor;
    private StatusPedido status = StatusPedido.PENDENTE;

    Pedido(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}

class PagamentoRecusadoException extends RuntimeException {

    PagamentoRecusadoException() {
        super("Pagamento recusado pelo gateway.");
    }
}

interface GatewayPagamento {

    void cobrar(double valor);
}

interface PedidoRepository {

    void atualizar(Pedido pedido);
}

public class ProcessadorPagamentoService {

    private final GatewayPagamento gateway;
    private final PedidoRepository pedidoRepository;

    public ProcessadorPagamentoService(GatewayPagamento gateway, PedidoRepository pedidoRepository) {
        this.gateway = gateway;
        this.pedidoRepository = pedidoRepository;
    }

    public void processar(Pedido pedido) {
        gateway.cobrar(pedido.getValor());
        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.atualizar(pedido);
    }
}
