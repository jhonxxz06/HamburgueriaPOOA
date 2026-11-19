package facade;

import construtor.BuilderBebida;
import construtor.BuilderHamburguer;
import construtor.BuilderPedido;
import construtor.BuilderSobremesa;
import decorador.ExtraBacon;
import decorador.ExtraQueijo;
import fabrica.FabricaDeBebida;
import fabrica.FabricaDeHamburguer;
import fabrica.FabricaDeSobremesa;
import modelo.ItemCardapio;
import modelo.Pedido;
import modelo.StatusPedido;
import observador.NotificacaoCliente;
import observador.NotificacaoCozinha;
import pagamento.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class FacadeHamburgueria {

    private static final double VALOR_ENTREGA = 5.0;

    private final FabricaDeHamburguer fabricaDeHamburguer = new FabricaDeHamburguer();
    private final FabricaDeBebida fabricaBebida = new FabricaDeBebida();
    private final FabricaDeSobremesa fabricaSobremesa = new FabricaDeSobremesa();

    private final List<ItemCardapio> itensEmMontagem = new ArrayList<>();
    private List<ItemCardapio> itensDoPedido = new ArrayList<>();
    private Pedido pedido;
    private boolean pago;

    public void adicionarHamburger(String nomeLanche, BuilderHamburguer opcoes, int quantidade, boolean comQueijo, boolean comBacon) {
        exigirPedidoAnteriorPago();
        exigirQuantidade(quantidade);
        iniciarNovoPedidoSeAnteriorPago(); // MUDANÇA: descarta o pedido anterior já pago ao iniciar um novo

        ItemCardapio lanche = fabricaDeHamburguer.criarItem(nomeLanche, opcoes);
        lanche.setQuantidade(quantidade);
        if (comQueijo) {
            lanche = new ExtraQueijo(lanche);
        }
        if (comBacon) {
            lanche = new ExtraBacon(lanche);
        }
        itensEmMontagem.add(lanche);
    }

    public void adicionarBebida(String nomeBebida, BuilderBebida opcoes, int quantidade) {
        exigirPedidoAnteriorPago();
        exigirQuantidade(quantidade);
        iniciarNovoPedidoSeAnteriorPago(); // MUDANÇA: descarta o pedido anterior já pago ao iniciar um novo

        ItemCardapio bebida = fabricaBebida.criarItem(nomeBebida, opcoes);
        bebida.setQuantidade(quantidade);
        itensEmMontagem.add(bebida);
    }

    public void adicionarSobremesa(String nomeSobremesa, BuilderSobremesa opcoes, int quantidade) {
        exigirPedidoAnteriorPago();
        exigirQuantidade(quantidade);
        iniciarNovoPedidoSeAnteriorPago(); // MUDANÇA: descarta o pedido anterior já pago ao iniciar um novo

        ItemCardapio sobremesa = fabricaSobremesa.criarItem(nomeSobremesa, opcoes);
        sobremesa.setQuantidade(quantidade);
        itensEmMontagem.add(sobremesa);
    }

    public void fazerPedido(String endereco, String observacoes) {
        exigirPedidoAnteriorPago();

        BuilderPedido builder = new BuilderPedido()
                .adicionarEndereco(endereco)
                .adicionarObservacoes(observacoes)
                .adicionarValorEntrega(VALOR_ENTREGA);
        for (ItemCardapio item : itensEmMontagem) {
            builder.adicionarItem(item);
        }

        pedido = builder.build();
        pedido.adicionarObservador(new NotificacaoCozinha());
        pedido.adicionarObservador(new NotificacaoCliente());
        pedido.setStatus(StatusPedido.RECEBIDO);

        itensDoPedido = new ArrayList<>(itensEmMontagem);
        itensEmMontagem.clear();
        pago = false;
    }

    // MUDANÇA: Agora valida antes de retornar o valor se há itens não finalizados
    // ou se o pedido atual já foi pago, evitando mostrar o valor de um pedido antigo.
    public double getValorTotalPedido() {
        exigirPedidoDisponivelParaPagamento();
        return pedido.valorTotalPedido();
    }

    public void pagar(Pagamento pagamento) {
        exigirPedidoDisponivelParaPagamento();
        pagamento.realizarPagamento(pedido.valorTotalPedido());
        pago = true;
        processarPedido();
    }

    public void exigirPedidoAnteriorPago() {
        if (pedido != null && !pago) {
            throw new IllegalStateException("Pague o pedido atual antes de iniciar outro.");
        }
    }

    // MUDANÇA: Mantém o pedido pago visível para consulta no resumo logo após pagar,
    // mas limpa os dados antigos assim que o primeiro item de um novo pedido é adicionado.
    private void iniciarNovoPedidoSeAnteriorPago() {
        if (pago) {
            pedido = null;
            itensDoPedido.clear();
            pago = false;
        }
    }

    // MUDANÇA: Centraliza as regras de bloqueio antes de exibir valor ou processar pagamento.
    private void exigirPedidoDisponivelParaPagamento() {
        if (!itensEmMontagem.isEmpty()) {
            throw new IllegalStateException("Finalize o pedido na opção 4 antes de pagar.");
        }
        if (pedido == null) {
            throw new IllegalStateException("Nenhum pedido foi finalizado ainda.");
        }
        if (pago) {
            throw new IllegalStateException("Este pedido já foi pago.");
        }
    }

    private void processarPedido() {
        while (pedido.getStatus() != StatusPedido.ENTREGUE) {
            aguardar(1000);
            pedido.avancarStatus();
        }
    }

    private void aguardar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void resumo() {
        if (!itensEmMontagem.isEmpty()) {
            System.out.println("\n== Resumo do pedido (EM MONTAGEM) ==");
            double subtotal = 0;
            for (ItemCardapio item : itensEmMontagem) {
                double valorItem = item.getPrecoItem() * item.getQuantidade();
                subtotal += valorItem;
                System.out.println(item.getDescricao() + " x" + item.getQuantidade()
                        + " = R$ " + valorItem);
            }
            System.out.println("VALOR DA ENTREGA R$: " + VALOR_ENTREGA);
            System.out.println("VALOR TOTAL ESTIMADO R$: " + (subtotal + VALOR_ENTREGA));
            System.out.println("STATUS: EM MONTAGEM (finalize na opção 4)");
            return;
        }

        if (pedido == null) {
            throw new IllegalStateException("Nenhum item foi adicionado ao pedido ainda.");
        }

        System.out.println("\n== Resumo do pedido ==");
        for (ItemCardapio item : itensDoPedido) {
            System.out.println(item.getDescricao() + " x" + item.getQuantidade()
                    + " = R$ " + item.getPrecoItem() * item.getQuantidade());
        }
        System.out.println("VALOR DA ENTREGA R$: " + VALOR_ENTREGA);
        System.out.println("VALOR TOTAL R$: " + pedido.valorTotalPedido());
        System.out.println("STATUS: " + pedido.getStatus());
    }

    private void exigirQuantidade(int quantidade) {
        if (quantidade < 1) {
            throw new IllegalArgumentException("A quantidade deve ser pelo menos 1.");
        }
    }
}