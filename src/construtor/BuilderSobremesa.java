package construtor;

public class BuilderSobremesa {
    String sabor = "Morango";
    String saborCalda = "Chocolate";
    boolean temJujuba = true;
    boolean temNutella = true;

    public BuilderSobremesa saborSobremesa(String sabor){
        this.sabor = sabor;
        return this;
    }
    public BuilderSobremesa saborCalda(String saborCalda){
        this.saborCalda = saborCalda;
        return this;
    }
    public BuilderSobremesa temJujuba(boolean confirmacao){
        this.temJujuba = confirmacao;
        return this;
    }
    public BuilderSobremesa temNutella(boolean confirmacao){
        this.temNutella = confirmacao;
        return this;
    }

    public String getSabor() {
        return sabor;
    }

    public String getSaborCalda() {
        return saborCalda;
    }

    public boolean isTemJujuba() {
        return temJujuba;
    }

    public boolean isTemNutella() {
        return temNutella;
    }
}
