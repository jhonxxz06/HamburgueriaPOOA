package modelo;

import observador.ObservadorPedido;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    List<ItemCardapio> itens;
    String endereco;
    String observacoes;
    double valorEntrega;
    StatusPedido status;
    private List<ObservadorPedido> observadores = new ArrayList<>();

    public Pedido(List<ItemCardapio> itens, String endereco, String observacoes, double valorEntrega) {
        this.itens = new ArrayList<>(itens);
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.valorEntrega = valorEntrega;
        this.status = StatusPedido.RECEBIDO;
    }

    public void adicionarObservador(ObservadorPedido observador) {
        this.observadores.add(observador);
    }

    public void setStatus(StatusPedido novoStatus) {
      this.status = novoStatus;
      notificarObservadores();
    }

    private void notificarObservadores() {
      for (ObservadorPedido obs : observadores) {
        obs.atualizar(this);
      }
    }

    public StatusPedido getStatus() {
      return status;
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
