package modelo;

public class Sobremesa extends ItemCardapio{
    // sorvete, pizza doce, açaí
    String sabor;
    String saborCalda;
    boolean temJujuba = true;
    boolean temNutella = true;

    public Sobremesa(String nomeItemCardapio, double pesoItem, double precoItem, String sabor, String saborCalda, boolean temJujuba, boolean temNutella) {
        this.nomeItemCardapio = nomeItemCardapio;
        this.pesoItem = pesoItem;
        this.precoItem = precoItem;
        this.sabor = sabor;
        this.saborCalda = saborCalda;
        this.temJujuba = temJujuba;
        this.temNutella = temNutella;
    }
}
