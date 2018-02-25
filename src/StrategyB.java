import java.util.ListIterator;

public class StrategyB implements Strategy {

    @Override
    public Item execute(WishList wishList) { //selects first item from wishList
        ListIterator<Item> ite = wishList.listIterator();
        if (ite.hasNext())
            return ite.next();
        else
            return null;
    }

}
