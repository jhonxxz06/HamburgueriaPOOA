package decorador;
import modelo.ItemCardapio;

public class ExtraBacon extends ExtraDecorator {

   public ExtraBacon(ItemCardapio item) {
      super(item);
   }

    @Override
    public String getNomeItemCardapio() {
      return itemDecorado.getNomeItemCardapio() + " + Extra Bacon";
    }

   @Override
   public double getPrecoItem() {
      return itemDecorado.getPrecoItem() + 6.00;
   }
}