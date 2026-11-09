package decorador;

import modelo.ItemCardapio;

public abstract class ExtraDecorator extends ItemCardapio {
    protected ItemCardapio itemDecorado;

     public ExtraDecorator(ItemCardapio item) {
        this.itemDecorado = item;
     }


   @Override
    public int getQuantidade() {
      return itemDecorado.getQuantidade();
    }
}