package modelo;

public abstract class ItemCardapio {
   protected String nomeItemCardapio;
   protected double pesoItem;
   protected double precoItem; // usar o locale do java
   protected  int quantidade = 1;

    public String getNomeItemCardapio() {
        return nomeItemCardapio;
    }

    public void setNomeItemCardapio(String nomeItemCardapio) {
        this.nomeItemCardapio = nomeItemCardapio;
    }

    public double getPesoItem() {
        return pesoItem;
    }

    public void setPesoItem(double pesoItem) {
        this.pesoItem = pesoItem;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(double precoItem) {
        this.precoItem = precoItem;
    }

    public String getDescricao() {
        return getNomeItemCardapio();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
