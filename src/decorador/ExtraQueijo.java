package decorador;
import modelo.ItemCardapio;

public class ExtraQueijo extends ExtraDecorator {

   public ExtraQueijo(ItemCardapio item) {
      super(item);
   }

   @Override
   public String getNomeItemCardapio() {
      return itemDecorado.getNomeItemCardapio() + " + Extra Queijo";
   }

   @Override
    public double getPrecoItem() {
      return itemDecorado.getPrecoItem() + 4.00;
    }
}