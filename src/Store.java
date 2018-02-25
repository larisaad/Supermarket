import java.util.Vector;

public class Store {
    public String name;
    private Vector<Department> departments;
    private Vector<Customer> customers;

    private static Store instance = null;

    private Store() {
        departments = new Vector<>();
        customers = new Vector<>();
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(); //lazy instantiation of Singleton Pattern
        }
        return instance;
    }

    public void enter(Customer customer) {
        customers.add(customer);
    }

    public void exit(Customer customer) {
        customers.remove(customer);

    }

    public ShoppingCart getShoppingCart(double budget) {
        return new ShoppingCart(budget);
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public Vector<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public Department getDepartment(Integer ID) {
        for (int i = 0; i < departments.size(); i++)
            if (departments.elementAt(i).getId() == ID)
                return departments.elementAt(i);

        return null;
    }

    public Customer getCustomer(String name) {
        for (int i = 0; i < this.getCustomers().size(); i++)
            if (this.getCustomers().elementAt(i).getName().equals(name))
                return this.getCustomers().elementAt(i);
        return null;
    }

    public Item getItem(int itemID) {
        for (int i = 0; i < this.getDepartments().size(); i++)
            if (this.getDepartments().elementAt(i).getItem(itemID) != null)
                return this.getDepartments().elementAt(i).getItem(itemID);
        return null;
    }
}
