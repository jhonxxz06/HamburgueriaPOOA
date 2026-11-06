package construtor;

public class BuilderBebida {
    double volumeMl = 350;
    String marca = "coca-cola";
    String sabor = "cola";
    boolean quererCopo = true;
    boolean quererCanudo = true;

    public BuilderBebida tamanhoBebida(double quantidadeVolume){
        this.volumeMl = quantidadeVolume;
        return this;
    }
    public BuilderBebida marcaBebida(String marca){
        this.marca = marca;
        return this;
    }
    public BuilderBebida saborBebida(String sabor){
        this.sabor = sabor;
        return this;
    }
    public BuilderBebida terCopo(boolean confirmacao){
        this.quererCopo = confirmacao;
        return this;
    }
    public BuilderBebida terCanudo(boolean confirmacao){
        this.quererCanudo = confirmacao;
        return this;
    }

    public double getVolumeMl() {
        return volumeMl;
    }

    public String getMarca() {
        return marca;
    }

    public String getSabor() {
        return sabor;
    }

    public boolean isQuererCopo() {
        return quererCopo;
    }

    public boolean isQuererCanudo() {
        return quererCanudo;
    }
}
