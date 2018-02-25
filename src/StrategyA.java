import java.util.ListIterator;

public class StrategyA implements Strategy {

    @Override
    public Item execute(WishList wishList) { //getting the cheaper item from wishList
        ListIterator<Item> ite = wishList.listIterator();
        Item selectedItem = null;
        Item item = null;
        Double minPrice = Double.MAX_VALUE;
        while (ite.hasNext()) {
            item = ite.next();
            if (item.getPrice() < minPrice) {
                minPrice = item.getPrice();
                selectedItem = item;
            }
        }
        return selectedItem;
    }

}
