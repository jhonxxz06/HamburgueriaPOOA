package fabrica;

import construtor.BuilderBebida;
import modelo.Bebida;
import modelo.ItemCardapio;

public class FabricaDeBebida extends FabricaItemCardapio<BuilderBebida>{

    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, double pesoItem, double precoItem, BuilderBebida builderBebida) {
        return new Bebida(nomeItemCardapio, pesoItem, precoItem,builderBebida.getVolumeMl(), builderBebida.getMarca(),
                builderBebida.getSabor(),builderBebida.isQuererCopo(),builderBebida.isQuererCanudo());
    }
}
