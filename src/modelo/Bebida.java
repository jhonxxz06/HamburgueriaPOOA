package modelo;

public class Bebida extends ItemCardapio{
    // MilkShake, água, refri, chá mate leão etc - por isso o sabor / marca
    double volumeMl;
    String marca;
    String sabor;
    boolean quererCopo = true;
    boolean quererCanudo = true;

    public Bebida(String nomeItemCardapio, double pesoItem, double precoItem, double volumeMl, String marca, String sabor, boolean quererCopo, boolean quererCanudo) {
        this.nomeItemCardapio = nomeItemCardapio;
        this.pesoItem = pesoItem;
        this.precoItem = precoItem;
        this.volumeMl = volumeMl;
        this.marca = marca;
        this.sabor = sabor;
        this.quererCopo = quererCopo;
        this.quererCanudo = quererCanudo;
    }
}
