package fabrica;

import construtor.BuilderSobremesa;
import modelo.ItemCardapio;
import modelo.Sobremesa;

public class FabricaDeSobremesa extends FabricaItemCardapio<BuilderSobremesa>{
    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, double pesoItem, double precoItem, BuilderSobremesa builderSobremesa) {
        return new Sobremesa(nomeItemCardapio,pesoItem,precoItem,builderSobremesa.getSabor(),
                builderSobremesa.getSaborCalda(),builderSobremesa.isTemJujuba(),builderSobremesa.isTemNutella());
    }
}
