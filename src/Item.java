
public class Item {

    private String name;
    private final int ID;
    private double price;
    private Department department;

    Item(String name, int ID, double price, Department department) {
        this.name = name;
        this.ID = ID;
        this.price = price;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public double getPrice() {
        return price;
    }

    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String toString() {
        String format = String.format("%.2f", price);
        return name + ";" + ID + ";" + format;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        Item item = (Item) o;
        return (this.name.equals(item.name) && this.ID == item.ID && (this.price == item.price) && this.department.equals(item.department));
    }
}
