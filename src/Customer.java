
import java.util.ListIterator;
import java.util.Vector;

public class Customer implements Observer {

    private String name;
    private ShoppingCart cart;
    private WishList wishList;
    private Vector<Notification> notifications;

    public Customer(String name, double budget, Strategy strategy) {
        this.name = name;
        cart = new ShoppingCart(budget);
        wishList = new WishList(strategy);
        notifications = new Vector<>();
    }

    public WishList getWishList() {
        return this.wishList;
    }

    public ShoppingCart getShoppingCart() {
        return this.cart;
    }

    public String getName() {
        return this.name;
    }

    public void setShoppingCart(ShoppingCart newcart) {
        this.cart = newcart;
    }

    public Vector<Notification> getNotifications() {
        return this.notifications;
    }

    @Override
    public void update(Notification notification) {

        notifications.add(notification);

        switch (notification.type) {
            case ADD:
                break;
            case REMOVE:
                //if an item was removed from the department, it has to be removed from the shoppingcarts and wishlists of observers
                ListIterator<Item> ite = wishList.listIterator(0);
                while (ite.hasNext()) {
                    Item item = ite.next();
                    if (item.getID() == notification.itemID) {
                        wishList.remove(item);
                    }
                }
                ite = cart.listIterator(0);
                while (ite.hasNext()) {
                    Item item = ite.next();
                    if (item.getID() == notification.itemID)
                        cart.remove(item);
                }
                break;
            case MODIFY:
                //if an item was modified it should be updated in the wishlist and shoppingcart of each observer
                Item item = null;
                Department dep = Store.getInstance().getDepartment(notification.departmentID);
                for (int j = 0; j < dep.getItems().size(); j++) {
                    if (dep.getItems().elementAt(j).getID() == notification.itemID) {
                        item = dep.getItems().elementAt(j); //getting the item which was modified
                    }
                }

                ite = cart.listIterator();
                while (ite.hasNext()) {
                    Item item2 = ite.next();
                    if (item2.getID() == notification.itemID) {
                        if (item.getPrice() - item2.getPrice() > cart.getBudget())
                            cart.remove(item);
                        else {
                            ite.previous();
                            ite.set(item);
                            ite.next();
                        }
                    }
                }
                ite = wishList.listIterator();
                while (ite.hasNext()) {
                    if (ite.next().getID() == notification.itemID) { //replacing the old item with the new one
                        ite.previous();
                        ite.set(item);
                        ite.next();

                    }
                }
                break;
        }
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(this.name);
        return s.toString();
    }

}
