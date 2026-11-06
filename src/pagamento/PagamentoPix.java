package pagamento;

import modelo.Pedido;

public class PagamentoPix implements Pagamento{
    @Override
    public void realizarPagamento(double valorPago) {
        valorPago = valorPago * 0.95;
        System.out.println("Pagamento em pix realizado, total: " + valorPago);
    }
}
