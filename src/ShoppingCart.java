import java.util.Comparator;
import java.util.ListIterator;

public class ShoppingCart extends ItemList implements Visitor {

    private double budget;
    private static Comparator<Item> ShoppingCartComparator = new Comparator<Item>() {

        @Override
        public int compare(Item o1, Item o2) {
            if (o1.getPrice() == o2.getPrice())
                return o1.getName().compareTo(o2.getName());
            else
                return (int) (o1.getPrice() - o2.getPrice());
        }

    }; //shoppingcart must be ordered by price

    public ShoppingCart() {
        this(0);
    }

    public ShoppingCart(double budget) {
        super(ShoppingCartComparator);
        this.budget = budget;
    }

    public ShoppingCart(double budget, Comparator<Item> c) {
        super(c);
        this.budget = budget;
    }

    @Override
    public boolean add(Item element) {
        if (budget - element.getPrice() < 0)
            return false;
        if (super.add(element)) {
            budget -= element.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Item element) {
        budget += element.getPrice();
        return super.remove(element);
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public int visit(BookDepartment dep) {
        double price;
        Item item;
        ListIterator<Item> ite = this.listIterator();
        while (ite.hasNext()) {
            item = ite.next();
            if (item.getDepartment().equals(dep)) {
                //lowering prices with 10% and increasing the budgets
                price = item.getPrice();
                budget += 10 * price / 100;
                item.setPrice(90 * price / 100);
            }
        }
        return 0;
    }

    @Override
    public int visit(MusicDepartment dep) {
        ListIterator<Item> ite = this.listIterator();
        Item item;
        double total = 0;
        while (ite.hasNext()) {
            item = ite.next();
            if (item.getDepartment().equals(dep)) {
                //total of items from shoppingcart from the department
                total += item.getPrice();
            }
        }
        budget += 10 * total / 100;
        return 0;
    }


    @Override
    public int visit(SoftwareDepartment dep) {
        ListIterator<Item> ite = this.listIterator();
        Item item;
        double minimum = Integer.MAX_VALUE; //will get the minimum price from the items both in the department and the shoppingcart
        while (ite.hasNext()) {
            item = ite.next();
            if (item.getDepartment().equals(dep)) {
                if (item.getPrice() < minimum) {
                    minimum = item.getPrice();
                }
            }
        }

        if (minimum < budget)
            return 0;
        double price;
        while (ite.hasPrevious()) {
            item = ite.previous();
            if (item.getDepartment().equals(dep)) {
                //lowering prices with 20%
                price = item.getPrice();
                budget += 20 / 100 * price;
                item.setPrice(price * 80 / 100);
            }
        }
        return 0;
    }

    @Override
    public int visit(VideoDepartment dep) {
        double total = 0;
        ListIterator<Item> ite = this.listIterator(0);
        Item item;
        double maximum = 0;
        for (int i = 0; i < dep.getItems().size(); i++) {
            if (dep.getItems().elementAt(i).getPrice() > maximum) {
                maximum = dep.getItems().elementAt(i).getPrice();
            }
        }
        while (ite.hasNext()) {
            item = ite.next();
            if (item.getDepartment().equals(dep)) {
                total += item.getPrice(); //totalt of items both in the shoppingcart and in the department
            }
        }
        if (total > maximum) {
            double price;
            while (ite.hasPrevious()) {
                item = ite.previous();
                if (item.getDepartment().equals(dep)) {
                    //lowering prices with 15%
                    price = item.getPrice();
                    budget += 15 / 100 * price;
                    item.setPrice(price * 85 / 100);
                }
            }
        }

        budget += 5 * total / 100;
        return 0;
    }
}
