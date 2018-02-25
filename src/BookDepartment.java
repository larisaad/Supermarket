
public class BookDepartment extends Department {

    BookDepartment(int ID) {
        super("BookDepartment", ID);
    }

    @Override
    public int accept(ShoppingCart cart) {
        return cart.visit(this);
    }
}
