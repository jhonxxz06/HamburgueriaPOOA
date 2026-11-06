package construtor;

import modelo.ItemCardapio;
import modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class BuilderPedido {

    private List<ItemCardapio> itens = new ArrayList<>();
    private String endereco = null;
    private String observacoes = null;
    private double valorEntrega = 0;

    public BuilderPedido adicionarItem(ItemCardapio item) {
        this.itens.add(item);
        return this;
    }

    public BuilderPedido adicionarEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public BuilderPedido adicionarObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public BuilderPedido adicionarValorEntrega(double valorEntrega) {
        this.valorEntrega = valorEntrega;
        return this;
    }

    public Pedido build() {
        if (this.itens.isEmpty()) {
            throw new IllegalStateException("o pedido está sem itens");
        }
        return new Pedido(itens, endereco, observacoes, valorEntrega);
    }
}