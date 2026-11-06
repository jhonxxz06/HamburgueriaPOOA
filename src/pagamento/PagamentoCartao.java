package pagamento;

public class PagamentoCartao implements Pagamento{
    @Override
    public void realizarPagamento(double valorPago) {
        System.out.println("Pagamento em cartão realizado com sucesso, total de: " + valorPago);
    }
}
