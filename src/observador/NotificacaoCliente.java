package observador;
import modelo.Pedido;

public class NotificacaoCliente implements ObservadorPedido {
  @Override
  public void atualizar(Pedido pedido) {
    System.out.println("[CLIENTE] Seu pedido mudou de status para: " + pedido.getStatus());
  }
}