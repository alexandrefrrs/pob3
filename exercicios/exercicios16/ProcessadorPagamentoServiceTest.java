package exercicios.exercicios16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessadorPagamentoServiceTest {

    @Mock
    private GatewayPagamento gateway;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ProcessadorPagamentoService service;

    @Test
    void deveCobrarEAtualizarPedidoPago() {
        Pedido pedido = new Pedido(200.00);

        doAnswer(invocacao -> {
            assertEquals(StatusPedido.PAGO, pedido.getStatus());
            return null;
        }).when(pedidoRepository).atualizar(pedido);

        service.processar(pedido);

        assertEquals(StatusPedido.PAGO, pedido.getStatus());
        InOrder ordem = inOrder(gateway, pedidoRepository);
        ordem.verify(gateway).cobrar(200.00);
        ordem.verify(pedidoRepository).atualizar(pedido);
    }

    @Test
    void naoDeveAtualizarPedidoQuandoPagamentoForRecusado() {
        Pedido pedido = new Pedido(200.00);
        doThrow(new PagamentoRecusadoException()).when(gateway).cobrar(anyDouble());

        PagamentoRecusadoException erro = assertThrows(
                PagamentoRecusadoException.class, () -> service.processar(pedido));

        assertEquals("Pagamento recusado pelo gateway.", erro.getMessage());
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        verify(gateway).cobrar(200.00);
        verify(pedidoRepository, never()).atualizar(any());
    }
}
