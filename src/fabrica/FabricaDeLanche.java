package fabrica;

import construtor.BuilderLanche;
import modelo.ItemCardapio;
import modelo.Lanche;

public class FabricaDeLanche extends FabricaItemCardapio<BuilderLanche> {


    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, double pesoItem, double precoItem, BuilderLanche builderLanche) {
        return new Lanche(nomeItemCardapio,pesoItem,precoItem,
                builderLanche.getTipoPao(), builderLanche.getQuantidade_hamburgueres(),
                builderLanche.isTemCebola(),builderLanche.isCondimentos(), builderLanche.isKetchup());
    }
}
