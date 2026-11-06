package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    List<ItemCardapio> itens;
    String endereco;
    String observacoes;
    double valorEntrega;
    StatusPedido status;

    public Pedido(List<ItemCardapio> itens, String endereco, String observacoes, double valorEntrega) {
        this.itens = new ArrayList<>(itens);
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.valorEntrega = valorEntrega;
        this.status = StatusPedido.RECEBIDO;
    }
    public double valorTotalPedido(){
        double total = valorEntrega;
        for (ItemCardapio item : itens) {
            total += item.getPrecoItem() * item.getQuantidade();
        }
        return total;
    }

    @Override
    public String toString() {
        return "== Informações de Pedido =={" +
                "endereco do cliente ='" + endereco + '\'' +
                ", itens presentes =" + itens +
                ", detalhes ='" + observacoes + '\'' +
                ", valor de entrega =" + valorEntrega +
                ", valor total =" + //valorTotalPedido +
                ", "+ '}';
    }

}
