package fabrica;

import construtor.BuilderHamburguer;
import modelo.ItemCardapio;
import modelo.Hamburguer;

public class FabricaDeHamburguer extends FabricaItemCardapio<BuilderHamburguer> {

    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, BuilderHamburguer builderHamburguer) {
        double precoBase = switch (nomeItemCardapio) {
            case "X-Burger" -> 22.0;
            case "X-Salada" -> 25.0;
            case "X-Tudo" -> 32.0;
            default -> 22.0;
        };

        double pesoBase = switch (nomeItemCardapio) {
            case "X-Burger" -> 280.0;
            case "X-Salada" -> 320.0;
            case "X-Tudo" -> 420.0;
            default -> 280.0;
        };

        int extrasCarne = builderHamburguer.getQuantidade_hamburgueres() - 1;
        double precoFinal = precoBase + (extrasCarne * 8.0);
        double pesoFinal = pesoBase + (extrasCarne * 120.0);

        return new Hamburguer(nomeItemCardapio, pesoFinal, precoFinal,
                builderHamburguer.getTipoPao(), builderHamburguer.getQuantidade_hamburgueres(),
                builderHamburguer.isTemCebola(), builderHamburguer.isCondimentos(), builderHamburguer.isKetchup());
    }
}