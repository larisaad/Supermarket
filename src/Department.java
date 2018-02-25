import java.util.Vector;

public abstract class Department implements Subject {

    private String name;
    private Vector<Item> items;
    private Vector<Customer> customers;
    private Vector<Customer> observers;
    private final int ID;

    Department(String name, int ID) {
        this.name = name;
        this.ID = ID;
        items = new Vector<>();
        customers = new Vector<>();
        observers = new Vector<>();
    }

    public void enter(Customer customer) { //customer contains duplicates
        customers.add(customer);
    }

    public void exit(Customer customer) {
        customers.remove(customer);
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public int getId() {
        return ID;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Vector<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void addObserver(Customer customer) {
        if (!observers.contains(customer))
            observers.add(customer);
    }

    public void removeObserver(Customer customer) {
        for (int i = 0; i < this.getItems().size(); i++)
            if (customer.getWishList().contains(this.getItems().elementAt(i).getID()))
                return;
        observers.remove(customer);
    }

    public void notifyAllObservers(Notification notification) {
        for (int i = 0; i < observers.size(); i++)
            observers.elementAt(i).update(notification);
    }

    public abstract int accept(ShoppingCart cart);

    public boolean equals(Object o) {
        Department d = (Department) o;
        return (d.ID == ID);
    }

    public Item getItem(int itemID) {
        for (int i = 0; i < this.getItems().size(); i++)
            if (this.getItems().elementAt(i).getID() == itemID)
                return this.getItems().elementAt(i);
        return null;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Vector<Customer> getObservers() {
        return this.observers;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Dep.Name: " + this.name + " ID: " + this.ID);
        return s.toString();
    }
}
