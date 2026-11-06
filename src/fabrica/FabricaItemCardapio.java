package fabrica;

import modelo.ItemCardapio;

public abstract class FabricaItemCardapio<T> {
    protected abstract ItemCardapio instanciarItem(String
                                                           nomeItemCardapio, double pesoItem, double precoItem, T builder);

    public ItemCardapio criarItem(String nomeItemCardapio, double pesoItem,
                                  double precoItem, T builder) {

        ItemCardapio novoItemCardapio = instanciarItem(nomeItemCardapio,pesoItem,precoItem, builder);

        return novoItemCardapio;

    }
}
