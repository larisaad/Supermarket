
public class VideoDepartment extends Department {

    VideoDepartment(int ID) {
        super("VideoDepartment", ID);
    }

    @Override
    public int accept(ShoppingCart cart) {
        return cart.visit(this);
    }

}
