
public class SoftwareDepartment extends Department {

    SoftwareDepartment(int ID) {
        super("SoftwareDepartment", ID);
    }

    @Override
    public int accept(ShoppingCart cart) {
        return cart.visit(this);
    }

}
