import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

public class Test {

    static int nr = 0; //used for parsing the department id from the item id

    public static Store parseStore(File f) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(f));
        String name = in.readLine(); //creating the store using it's name
        Store store = Store.getInstance();
        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(line, ";");
            String nameDep = token.nextToken();
            int idDep = Integer.parseInt(token.nextToken());
            Department dep = null;
            switch (nameDep) {
                case "BookDepartment":
                    dep = new BookDepartment(idDep);
                    break;
                case "MusicDepartment":
                    dep = new MusicDepartment(idDep);
                    break;
                case "SoftwareDepartment":
                    dep = new SoftwareDepartment(idDep);
                    break;
                case "VideoDepartment":
                    dep = new VideoDepartment(idDep);
                    break;
            }
            store.addDepartment(dep);
            Integer nrItems = Integer.parseInt(in.readLine());
            if (nr < String.valueOf(nrItems).length() + 1)
                nr = String.valueOf(nrItems).length() + 1;
            for (int i = 0; i < nrItems; i++) {
                token = new StringTokenizer(in.readLine(), ";");
                Item item = new Item(token.nextToken(), Integer.parseInt(token.nextToken()), Double.parseDouble(token.nextToken()), dep);
                dep.addItem(item);
            }
        }

        in.close();
        return store;
    }

    public static void parseCustomers(Store store, File f) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(f));
        int nrCustomers = Integer.parseInt(in.readLine());

        for (int i = 0; i < nrCustomers; i++) {
            StringTokenizer token = new StringTokenizer(in.readLine(), ";");
            String name = token.nextToken();
            Double budget = Double.parseDouble(token.nextToken());
            String strategyName = token.nextToken();
            Strategy strategy = null;
            switch (strategyName) {
                case "A":
                    strategy = new StrategyA();
                    break;
                case "B":
                    strategy = new StrategyB();
                    break;
                case "C":
                    strategy = new StrategyC();
                    break;
            }
            store.enter(new Customer(name, budget, strategy));
        }
        in.close();
    }

    public static void parseEvents(Store store) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("events.txt"));
        String line;
        BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
        while ((line = in.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(line, ";");
            Item item;
            switch (token.nextToken()) {

                case "addItem":
                    item = store.getItem(Integer.parseInt(token.nextToken()));
                    Customer customer;
                    switch (token.nextToken()) {
                        case "ShoppingCart":
                            //adding item into the shopping cart and entering the customer to department(the vector customers from Department class contains duplicates)
                            customer = store.getCustomer(token.nextToken());
                            customer.getShoppingCart().add(new Item(item.getName(), item.getID(), item.getPrice(), item.getDepartment()));
                            store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", item.getID()).charAt(0))).enter(customer);
                            break;
                        case "WishList":
                            //adding item into the wishlist and adding customer to observers
                            customer = store.getCustomer(token.nextToken());
                            customer.getWishList().add(new Item(item.getName(), item.getID(), item.getPrice(), item.getDepartment()));
                            store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", item.getID()).charAt(0))).addObserver(customer);
                            break;
                    }
                    break;

                case "delItem":
                    item = store.getItem(Integer.parseInt(token.nextToken()));
                    switch (token.nextToken()) {
                        case "ShoppingCart":
                            //deleting item from shopping cart and exiting the customer from department
                            customer = store.getCustomer(token.nextToken());
                            customer.getShoppingCart().remove(item);
                            store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", item.getID()).charAt(0))).exit(customer);
                            break;
                        case "WishList":
                            //deleting item from wishlist and removing customer from observers
                            customer = store.getCustomer(token.nextToken());
                            customer.getWishList().remove(item);
                            store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", item.getID()).charAt(0))).removeObserver(customer);
                            break;
                    }
                    break;

                case "addProduct":
                    Department dep = store.getDepartment(Integer.parseInt(token.nextToken()));
                    int itemID = Integer.parseInt(token.nextToken());
                    double price = Double.parseDouble(token.nextToken());
                    item = new Item(token.nextToken(), itemID, price, dep); //creating the item
                    dep.addItem(item); //adding it to the department
                    dep.notifyAllObservers(new Notification(new Date(), NotificationType.ADD, dep.getId(), itemID)); //sending notifications
                    break;

                case "modifyProduct":
                    dep = store.getDepartment(Integer.parseInt(token.nextToken()));
                    item = dep.getItem(Integer.parseInt(token.nextToken())); //getting the item from the department
                    item.setPrice(Double.parseDouble(token.nextToken())); //updating item's price
                    dep.notifyAllObservers(new Notification(new Date(), NotificationType.MODIFY, dep.getId(), item.getID())); //sending notification
                    break;

                case "delProduct":
                    itemID = Integer.parseInt(token.nextToken());
                    item = store.getItem(itemID); //getting the item which is being deleting
                    dep = store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", itemID).charAt(0)));
                    dep.notifyAllObservers(new Notification(new Date(), NotificationType.REMOVE, dep.getId(), itemID)); //sending notifications
                    //removing customers from observers if they no longer have items from dep department in their wishlist
                    for (int i = 0; i < dep.getObservers().size(); i++)
                        dep.removeObserver(dep.getObservers().elementAt(i));
                    break;

                case "getItem":
                    customer = store.getCustomer(token.nextToken());
                    item = customer.getWishList().executeStrategy(); //executing strategy of customers and getting the item resulted from that
                    dep = store.getDepartment(Character.getNumericValue(String.format("%0" + nr + "d", item.getID()).charAt(0)));
                    dep.removeObserver(customer);
                    customer.getShoppingCart().add(item); //adding item to customer's shoppingcart
                    out.write(item.toString());
                    out.newLine();
                    break;

                case "getItems":
                    //writing out the items from customer's list
                    switch (token.nextToken()) {
                        case "ShoppingCart":
                            out.write((store.getCustomer(token.nextToken()).getShoppingCart()).toString());
                            out.newLine();
                            break;
                        case "WishList":
                            out.write((store.getCustomer(token.nextToken()).getWishList()).toString());
                            out.newLine();
                            break;
                    }
                    break;
                case "getTotal":

                    //writing out the total of costumer's list
                    switch (token.nextToken()) {
                        case "ShoppingCart":
                            out.write(String.format("%.2f", store.getCustomer(token.nextToken()).getShoppingCart().getPrice()));
                            out.newLine();
                            break;
                        case "WishList":
                            out.write(String.format("%.2f", store.getCustomer(token.nextToken()).getWishList().getPrice()));
                            out.newLine();
                            break;

                    }
                    break;

                case "accept":
                    //call accept
                    store.getDepartment(Integer.parseInt(token.nextToken())).accept(store.getCustomer(token.nextToken()).getShoppingCart());
                    break;

                case "getObservers":
                    //print observers
                    out.write((store.getDepartment(Integer.parseInt(token.nextToken())).getObservers()).toString());
                    out.newLine();
                    break;

                case "getNotifications":
                    //print notifications
                    out.write((store.getCustomer(token.nextToken()).getNotifications()).toString());
                    out.newLine();
                    break;
            }
        }
        in.close();
        out.close();

    }

    public static void main(String[] args) throws IOException {


        try {
            new StoreFrame().setVisible(true);
            ;
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }


    }
}
