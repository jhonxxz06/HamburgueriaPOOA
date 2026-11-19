package fabrica;

import modelo.ItemCardapio;

public abstract class FabricaItemCardapio<T> {

    protected abstract ItemCardapio instanciarItem(String nomeItemCardapio, T builder);

    public ItemCardapio criarItem(String nomeItemCardapio, T builder) {
        return instanciarItem(nomeItemCardapio, builder);
    }
}