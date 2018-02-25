import java.util.ListIterator;

public class StrategyC implements Strategy {

    @Override
    public Item execute(WishList wishList) { //getting the most recent item added in wishList


        ListIterator<Item> ite = wishList.listIterator();
        while (ite.hasNext()) {
            Item item = ite.next();
            if (item.equals(wishList.getLastItem()))
                return item;
        }
        return null;
    }


}
