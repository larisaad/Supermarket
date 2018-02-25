import java.util.Comparator;
import java.util.ListIterator;

public class WishList extends ItemList {

    private static Comparator<Item> WishListComparator = new Comparator<Item>() {

        @Override
        public int compare(Item o1, Item o2) {
            String itemName1 = o1.getName().toLowerCase();
            String itemName2 = o2.getName().toLowerCase();
            return itemName1.compareTo(itemName2);
        } //wishlists must be ordered alphabetically

    };
    private Strategy strategy;
    private Item lastItem; //for implementing StrategyC

    public WishList(Strategy strategy) {
        super(WishListComparator);
        this.strategy = strategy;
    }

    public Item executeStrategy() {
        Item item = strategy.execute(this);
        this.remove(item);
        return item;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public boolean add(Item element) {
        lastItem = element;
        return super.add(element);
    }

    public boolean contains(int ID) {
        ListIterator<Item> ite = this.listIterator();
        while (ite.hasNext()) {
            if (ite.next().getID() == ID)
                return true;
        }
        return false;
    }

    public Item getLastItem() {
        return lastItem;
    }
}
