package modelo;

public class Hamburguer extends ItemCardapio {
    String tipoPao = "pão italiano";
    int quantidade_hamburgueres = 1;
    boolean temCebola = true;
    boolean condimentos = true;
    boolean ketchup = true;

    public Hamburguer(String nomeItemCardapio, double pesoItem, double precoItem, String tipoPao,
                  int quantidadeHamburgueres, boolean temCebola, boolean condimentos, boolean ketchup) {
        this.nomeItemCardapio = nomeItemCardapio;
        this.pesoItem = pesoItem;
        this.precoItem = precoItem;
        this.tipoPao = tipoPao;
        this.quantidade_hamburgueres = quantidadeHamburgueres;
        this.temCebola = temCebola;
        this.condimentos = condimentos;
        this.ketchup = ketchup;
    }


    @Override
    public String getNomeItemCardapio() {
        return super.getNomeItemCardapio() + " (" + tipoPao + ", " + quantidade_hamburgueres + " hamb.)";
    }
}