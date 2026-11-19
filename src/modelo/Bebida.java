package modelo;

public class Bebida extends ItemCardapio {
    double volumeMl;
    String marca;
    String sabor;
    boolean quererCopo = true;
    boolean quererCanudo = true;

    public Bebida(String nomeItemCardapio, double pesoItem, double precoItem, double volumeMl,
                  String marca, String sabor, boolean quererCopo, boolean quererCanudo) {
        this.nomeItemCardapio = nomeItemCardapio;
        this.pesoItem = pesoItem;
        this.precoItem = precoItem;
        this.volumeMl = volumeMl;
        this.marca = marca;
        this.sabor = sabor;
        this.quererCopo = quererCopo;
        this.quererCanudo = quererCanudo;
    }


    @Override
    public String getNomeItemCardapio() {
        return super.getNomeItemCardapio() + " " + marca + " " + sabor + " (" + (int) volumeMl + "ml)";
    }
}