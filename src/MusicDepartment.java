
public class MusicDepartment extends Department {

    MusicDepartment(int ID) {
        super("MusicDepartment", ID);
    }

    @Override
    public int accept(ShoppingCart cart) {
        return cart.visit(this);
    }

}
