package observador;
import modelo.Pedido;

public class NotificacaoCozinha implements ObservadorPedido {
  @Override
  public void atualizar(Pedido pedido) {
    System.out.println("[COZINHA] Pedido atualizado para: " + pedido.getStatus());
  }
}
