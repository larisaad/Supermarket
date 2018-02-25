import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.Date;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SecondFrame extends JFrame implements ActionListener, ListSelectionListener {


    private JTabbedPane pane1;
    private JRadioButton order1, order2, order3;
    private ButtonGroup group;
    private JList<Item> itemlist1, itemlist2, itemlist3, itemlist4;
    private JScrollPane scroller1, scroller2, scroller3, scroller4;
    private Vector<Item> items1, items2, items3, items4;
    private DefaultListModel<Item> model1, model2, model3, model4;
    private JButton add, modify, delete, customer, observer, mostexpensive;
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12, txt13, txt14, txt15, txt16;
    private JLabel lb1, lb2, lb3, lb4;
    private JDialog dialog;
    private int id1 = 0, id2 = 0, id3 = 0, id4 = 0;

    public SecondFrame() {

        super();
        /*initializing the frame*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(new Color(189, 214, 240));
        setLayout(new FlowLayout());

		/*adding a tabbed pane*/
        pane1 = new JTabbedPane();
        pane1.setPreferredSize(new Dimension(550, 550));
	    
		/*creating first tab*/
        txt1 = new JTextField(5);
        txt2 = new JTextField(5);
        txt3 = new JTextField(5);
        txt4 = new JTextField(5);

        txt1.setPreferredSize(new Dimension(5, 1));
        txt2.setPreferredSize(new Dimension(5, 1));
        txt3.setPreferredSize(new Dimension(5, 1));
        txt4.setPreferredSize(new Dimension(5, 1));
        txt2.setEditable(false);
        txt4.setEditable(false);

        JComponent panel1 = makeTextPanel("BookDepartment", new Color(189, 214, 240), new Color(255, 128, 0));
        panel1.setPreferredSize(new Dimension(200, 400));
        panel1.setBackground(new Color(189, 214, 240));
		
		/*adding 4 labels for item characteristics, and 4 text fields*/
        panel1.add(lb1);
        panel1.add(txt1);
        panel1.add(lb2);
        panel1.add(txt2);
        panel1.add(lb3);
        panel1.add(txt3);
        panel1.add(lb4);
        panel1.add(txt4);
		
		/*creating lists after the items from each department*/
		
		/*creating a list with the items from BookDepartment*/
        model1 = new DefaultListModel<Item>();
        items1 = new Vector<Item>();
        for (int j = 0; j < Store.getInstance().getDepartments().size(); j++) {
            if (Store.getInstance().getDepartments().elementAt(j).getName().equals("BookDepartment")) {
                id1 = Store.getInstance().getDepartments().elementAt(j).getId();
            }
        }
        Department dep = Store.getInstance().getDepartment(id1);
        for (int i = 0; i < dep.getItems().size(); i++) {
            items1.add(dep.getItems().elementAt(i));
            model1.addElement(dep.getItems().elementAt(i));
        }

        itemlist1 = new JList<Item>(model1);
        itemlist1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        itemlist1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        itemlist1.setVisibleRowCount(-1);
        itemlist1.addListSelectionListener(this);
        scroller1 = new JScrollPane(itemlist1);
        scroller1.setPreferredSize(new Dimension(230, 400));
		
		/*auxiliary panel which contains the scroll */
        JPanel littlepanel = new JPanel();
        littlepanel.setPreferredSize(new Dimension(240, 400));
        littlepanel.setBounds(200, 0, 200, 400);
        littlepanel.setBackground(new Color(189, 214, 240));
        littlepanel.add(scroller1);
		
		/*the actual tab panel which contains the panel with the scroll and the panel with the other components*/
        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(550, 550));
        bigpanel.setBackground(new Color(189, 214, 240));
        bigpanel.add(panel1);
        bigpanel.add(littlepanel);
		
		/*adding 3 buttons at the bottom of the frame*/
        customer.setBounds(0, 460, 10, 470);
        customer.setBackground(new Color(255, 128, 0));
        observer.setBounds(20, 460, 30, 470);
        observer.setBackground(new Color(255, 128, 0));
        mostexpensive.setBounds(40, 460, 50, 470);
        mostexpensive.setBackground(new Color(255, 128, 0));
        bigpanel.add(customer);
        bigpanel.add(observer);
        bigpanel.add(mostexpensive);
		
		/*adding the tabb to the tabbed pane*/
        pane1.addTab("Book Department", null, bigpanel, "Does nothing");
        pane1.setMnemonicAt(0, KeyEvent.VK_1);

		/*creating second tab*/
        txt5 = new JTextField(5);
        txt6 = new JTextField(5);
        txt7 = new JTextField(5);
        txt8 = new JTextField(5);
        txt5.setPreferredSize(new Dimension(5, 1));
        txt6.setPreferredSize(new Dimension(5, 1));
        txt7.setPreferredSize(new Dimension(5, 1));
        txt8.setPreferredSize(new Dimension(5, 1));
        txt6.setEditable(false);
        txt8.setEditable(false);

        JComponent panel2 = makeTextPanel("MusicDepartment", new Color(255, 128, 0), new Color(189, 214, 240));
        panel2.setPreferredSize(new Dimension(200, 400));
        panel2.setBackground(new Color(255, 128, 0));

        panel2.add(lb1);
        panel2.add(txt5);
        panel2.add(lb2);
        panel2.add(txt6);
        panel2.add(lb3);
        panel2.add(txt7);
        panel2.add(lb4);
        panel2.add(txt8);

        model2 = new DefaultListModel<>();
        items2 = new Vector<>();
        for (int j = 0; j < Store.getInstance().getDepartments().size(); j++) {
            if (Store.getInstance().getDepartments().elementAt(j).getName().equals("MusicDepartment")) {
                id2 = Store.getInstance().getDepartments().elementAt(j).getId();
            }
        }
        dep = Store.getInstance().getDepartment(id2);
        for (int i = 0; i < dep.getItems().size(); i++) {
            items2.add(dep.getItems().elementAt(i));
            model2.addElement(dep.getItems().elementAt(i));
        }

        itemlist2 = new JList<>(model2);
        itemlist2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        itemlist2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        itemlist2.setVisibleRowCount(-1);
        itemlist2.addListSelectionListener(this);
        scroller2 = new JScrollPane(itemlist2);
        scroller2.setPreferredSize(new Dimension(230, 400));

        JPanel little2 = new JPanel();
        little2.add(scroller2);
        little2.setPreferredSize(new Dimension(240, 400));
        little2.setBounds(200, 0, 200, 400);
        little2.setBackground(new Color(255, 128, 0));

        JPanel big2 = new JPanel();
        big2.setPreferredSize(new Dimension(550, 550));
        big2.setBackground(new Color(255, 128, 0));
        big2.add(panel2);
        big2.add(little2);

        customer.setBounds(0, 460, 10, 470);
        observer.setBounds(20, 460, 30, 470);
        mostexpensive.setBounds(40, 460, 50, 470);
        big2.add(customer);
        big2.add(observer);
        big2.add(mostexpensive);
        pane1.addTab("Music Department", null, big2, "Does twice as much nothing");
        pane1.setMnemonicAt(1, KeyEvent.VK_2);
	
		/*creating the 3rd tab*/

        txt9 = new JTextField(5);
        txt10 = new JTextField(5);
        txt11 = new JTextField(5);
        txt12 = new JTextField(5);
        txt9.setPreferredSize(new Dimension(5, 1));
        txt10.setPreferredSize(new Dimension(5, 1));
        txt11.setPreferredSize(new Dimension(5, 1));
        txt12.setPreferredSize(new Dimension(5, 1));
        txt10.setEditable(false);
        txt12.setEditable(false);

        JComponent panel3 = makeTextPanel("SoftwareDepartment", new Color(255, 128, 0), new Color(189, 214, 240));
        panel3.setPreferredSize(new Dimension(200, 400));
        panel3.setBackground(new Color(255, 128, 0));

        panel3.add(lb1);
        panel3.add(txt9);
        panel3.add(lb2);
        panel3.add(txt10);
        panel3.add(lb3);
        panel3.add(txt11);
        panel3.add(lb4);
        panel3.add(txt12);

        model3 = new DefaultListModel<>();
        items3 = new Vector<>();

        for (int j = 0; j < Store.getInstance().getDepartments().size(); j++) {
            if (Store.getInstance().getDepartments().elementAt(j).getName().equals("SoftwareDepartment")) {
                id3 = Store.getInstance().getDepartments().elementAt(j).getId();
            }
        }
        dep = Store.getInstance().getDepartment(id3);
        for (int i = 0; i < dep.getItems().size(); i++) {
            items3.add(dep.getItems().elementAt(i));
            model3.addElement(dep.getItems().elementAt(i));
        }

        itemlist3 = new JList<>(model3);
        itemlist3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        itemlist3.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        itemlist3.setVisibleRowCount(-1);
        itemlist3.addListSelectionListener(this);
        scroller3 = new JScrollPane(itemlist3);
        scroller3.setPreferredSize(new Dimension(230, 400));

        JPanel little3 = new JPanel();
        little3.setPreferredSize(new Dimension(240, 400));
        little3.setBounds(200, 0, 200, 400);
        little3.setBackground(new Color(255, 128, 0));
        little3.add(scroller3);

        JPanel big3 = new JPanel();
        big3.setPreferredSize(new Dimension(450, 450));
        big3.setBackground(new Color(255, 128, 0));
        big3.add(panel3);
        big3.add(little3);

        customer.setBounds(0, 460, 10, 470);
        observer.setBounds(20, 460, 30, 470);
        mostexpensive.setBounds(40, 460, 50, 470);
        big3.add(customer);
        big3.add(observer);
        big3.add(mostexpensive);
        pane1.addTab("Software Department", null, big3, "Still does nothing");
        pane1.setMnemonicAt(2, KeyEvent.VK_3);
		
		/*creating the 4th tab*/
        txt13 = new JTextField(5);
        txt14 = new JTextField(5);
        txt15 = new JTextField(5);
        txt16 = new JTextField(5);
        txt13.setPreferredSize(new Dimension(5, 1));
        txt14.setPreferredSize(new Dimension(5, 1));
        txt15.setPreferredSize(new Dimension(5, 1));
        txt16.setPreferredSize(new Dimension(5, 1));
        txt14.setEditable(false);
        txt16.setEditable(false);
        JComponent panel4 = makeTextPanel("VideoDepartment", new Color(189, 214, 240), new Color(255, 128, 0));
        panel4.setPreferredSize(new Dimension(200, 400));
        panel4.setBackground(new Color(189, 214, 240));

        panel4.add(lb1);
        panel4.add(txt13);
        panel4.add(lb2);
        panel4.add(txt14);
        panel4.add(lb3);
        panel4.add(txt15);
        panel4.add(lb4);
        panel4.add(txt16);

        model4 = new DefaultListModel<>();
        items4 = new Vector<>();

        for (int j = 0; j < Store.getInstance().getDepartments().size(); j++) {
            if (Store.getInstance().getDepartments().elementAt(j).getName().equals("VideoDepartment")) {
                id4 = Store.getInstance().getDepartments().elementAt(j).getId();
            }
        }
        dep = Store.getInstance().getDepartment(id4);
        for (int i = 0; i < dep.getItems().size(); i++) {
            items4.add(dep.getItems().elementAt(i));
            model4.addElement(dep.getItems().elementAt(i));
        }

        itemlist4 = new JList<>(model4);
        itemlist4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        itemlist4.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        itemlist4.setVisibleRowCount(-1);
        itemlist4.addListSelectionListener(this);
        scroller4 = new JScrollPane(itemlist4);
        scroller4.setPreferredSize(new Dimension(230, 400));

        JPanel little4 = new JPanel();
        little4.setPreferredSize(new Dimension(240, 400));
        little4.setBounds(200, 0, 200, 400);
        little4.setBackground(new Color(189, 214, 240));
        little4.add(scroller4);

        JPanel big4 = new JPanel();
        big4.setPreferredSize(new Dimension(450, 450));
        big4.setBackground(new Color(189, 214, 240));
        big4.add(panel4);
        big4.add(little4);

        customer.setBounds(0, 460, 10, 470);
        observer.setBounds(20, 460, 30, 470);
        mostexpensive.setBounds(40, 460, 50, 470);
        big4.add(customer);
        big4.add(observer);
        big4.add(mostexpensive);
        pane1.addTab("Video Department", null, big4, "Does nothing at all");
        pane1.setMnemonicAt(3, KeyEvent.VK_4);

        add(pane1);
        show();
        pack();
    }

    protected JComponent makeTextPanel(String text, Color color1, Color color2) {

        JPanel panel = new JPanel(false);
        panel.setPreferredSize(new Dimension(200, 400));
        panel.setBackground(color1);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        filler.setFont(new Font("Serif", Font.BOLD, 12));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(filler);


        group = new ButtonGroup();
        order1 = new JRadioButton("alphabetically");
        order2 = new JRadioButton("ascending by price");
        order3 = new JRadioButton("descending by price");

        order1.setBackground(color1);
        order2.setBackground(color1);
        order3.setBackground(color1);

        order1.addActionListener(this);
        order2.addActionListener(this);
        order3.addActionListener(this);
        group.add(order1);
        group.add(order2);
        group.add(order3);
        panel.add(order1);
        panel.add(order2);
        panel.add(order3);

        lb1 = new JLabel("Name:");
        lb2 = new JLabel("ID:");
        lb3 = new JLabel("Price:");
        lb4 = new JLabel("DepID:");

        add = new JButton("  Add   ");
        modify = new JButton(" Modify");
        delete = new JButton("Delete ");

        add.addActionListener(this);
        modify.addActionListener(this);
        delete.addActionListener(this);

        add.setBackground(color2);
        modify.setBackground(color2);
        delete.setBackground(color2);

        add.setForeground(new Color(0, 0, 51));
        modify.setForeground(new Color(0, 0, 51));
        delete.setForeground(new Color(0, 0, 51));

        add.setFont(new Font("Serif", Font.BOLD, 14));
        modify.setFont(new Font("Serif", Font.BOLD, 14));
        delete.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(add);
        panel.add(modify);
        panel.add(delete);

        customer = new JButton("Get customers");
        observer = new JButton("Get observers");
        mostexpensive = new JButton("Most expensive");

        customer.setBackground(color2);
        observer.setBackground(color2);
        mostexpensive.setBackground(color2);

        customer.setForeground(new Color(0, 0, 51));
        observer.setForeground(new Color(0, 0, 51));
        mostexpensive.setForeground(new Color(0, 0, 51));

        customer.setFont(new Font("Serif", Font.BOLD, 14));
        observer.setFont(new Font("Serif", Font.BOLD, 14));
        mostexpensive.setFont(new Font("Serif", Font.BOLD, 14));

        customer.addActionListener(this);
        observer.addActionListener(this);
        mostexpensive.addActionListener(this);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            if (e.getActionCommand().equals("alphabetically")) {
                //order alphabetically the list
                //i'm using a wishlist because it extends itemlist and has a comparator for ordering alphabetically
                switch (pane1.getSelectedIndex()) { //switching tabs
                    case 0: {
                        WishList wish = new WishList(new StrategyA());
                        for (int i = 0; i < model1.size(); i++) {
                            wish.add(model1.get(i));
                        }
                        model1.removeAllElements();
                        ListIterator<Item> ite1 = wish.listIterator();
                        while (ite1.hasNext()) {
                            model1.addElement(ite1.next());
                        }
                        itemlist1.setModel(model1);
                        break;
                    }
                    case 1: {
                        WishList wish = new WishList(new StrategyA());
                        for (int i = 0; i < model2.size(); i++) {
                            wish.add(model2.get(i));
                        }
                        model2.removeAllElements();
                        ListIterator<Item> ite1 = wish.listIterator();
                        while (ite1.hasNext()) {
                            model2.addElement(ite1.next());
                        }
                        itemlist2.setModel(model2);
                        break;
                    }
                    case 2: {
                        WishList wish = new WishList(new StrategyA());
                        for (int i = 0; i < model3.size(); i++) {
                            wish.add(model3.get(i));
                        }
                        model3.removeAllElements();
                        ListIterator<Item> ite1 = wish.listIterator();
                        while (ite1.hasNext()) {
                            model3.addElement(ite1.next());
                        }
                        itemlist3.setModel(model3);
                        break;
                    }
                    case 3: {
                        WishList wish = new WishList(new StrategyA());
                        for (int i = 0; i < model4.size(); i++) {
                            wish.add(model4.get(i));
                        }
                        model4.removeAllElements();
                        ListIterator<Item> ite1 = wish.listIterator();
                        while (ite1.hasNext()) {
                            model4.addElement(ite1.next());
                        }
                        itemlist4.setModel(model4);
                        break;
                    }
                }

            }
            if (e.getActionCommand().equals("ascending by price")) {
                //order ascending by price
                //i'm using a shoppingcart because it extends itemlist and has a comparator for ordering ascending by price

                switch (pane1.getSelectedIndex()) { //switching tabs
                    case 0: {
                        ShoppingCart w = new ShoppingCart(Double.MAX_VALUE);
                        for (int i = 0; i < model1.size(); i++) {
                            w.add(model1.get(i));
                        }
                        model1.removeAllElements();
                        ListIterator<Item> ite = w.listIterator();
                        while (ite.hasNext()) {
                            model1.addElement(ite.next());
                        }
                        itemlist1.setModel(model1);
                        break;
                    }
                    case 1: {
                        ShoppingCart w = new ShoppingCart(Double.MAX_VALUE);
                        for (int i = 0; i < model2.size(); i++) {
                            w.add(model2.get(i));
                        }
                        model2.removeAllElements();
                        ListIterator<Item> ite = w.listIterator();
                        while (ite.hasNext()) {
                            model2.addElement(ite.next());
                        }
                        itemlist2.setModel(model2);
                        break;
                    }
                    case 2: {
                        ShoppingCart w = new ShoppingCart(Double.MAX_VALUE);
                        for (int i = 0; i < model3.size(); i++) {
                            w.add(model3.get(i));
                        }
                        model3.removeAllElements();
                        ListIterator<Item> ite = w.listIterator();
                        while (ite.hasNext()) {
                            model3.addElement(ite.next());
                        }
                        itemlist3.setModel(model3);
                        break;
                    }
                    case 3: {
                        ShoppingCart w = new ShoppingCart(Double.MAX_VALUE);
                        for (int i = 0; i < model4.size(); i++) {
                            w.add(model4.get(i));
                        }
                        model4.removeAllElements();
                        ListIterator<Item> ite = w.listIterator();
                        while (ite.hasNext()) {
                            model4.addElement(ite.next());
                        }
                        itemlist4.setModel(model4);
                        break;
                    }
                }


            }
            if (e.getActionCommand().equals("descending by price")) {
                //order descending by price
                //i'm using a shoppingcart because it extends item list and the below comparator for sorting descending by price
                Comparator<Item> comparator = new Comparator<Item>() {

                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getPrice() == o2.getPrice())
                            return o1.getName().compareTo(o2.getName());
                        else
                            return (int) ((-1) * (o1.getPrice() - o2.getPrice()));
                    }

                };
                switch (pane1.getSelectedIndex()) { //switching tabs
                    case 0: {
                        ShoppingCart s = new ShoppingCart(Double.MAX_VALUE, comparator);
                        for (int i = 0; i < model1.size(); i++) {
                            s.add(model1.get(i));
                        }
                        model1.removeAllElements();
                        ListIterator<Item> ite = s.listIterator();
                        while (ite.hasNext()) {
                            model1.addElement(ite.next());
                        }

                        itemlist1.setModel(model1);
                        break;
                    }
                    case 1: {
                        ShoppingCart s = new ShoppingCart(Double.MAX_VALUE, comparator);
                        for (int i = 0; i < model2.size(); i++) {
                            s.add(model2.get(i));
                        }
                        model2.removeAllElements();
                        ListIterator<Item> ite = s.listIterator();
                        while (ite.hasNext()) {
                            model2.addElement(ite.next());
                        }
                        itemlist2.setModel(model2);
                        break;
                    }
                    case 2: {
                        ShoppingCart s = new ShoppingCart(Double.MAX_VALUE, comparator);
                        for (int i = 0; i < model3.size(); i++) {
                            s.add(model3.get(i));
                        }
                        model3.removeAllElements();
                        ListIterator<Item> ite = s.listIterator();
                        while (ite.hasNext()) {
                            model3.addElement(ite.next());
                        }
                        itemlist3.setModel(model3);
                        break;
                    }
                    case 3: {
                        ShoppingCart s = new ShoppingCart(Double.MAX_VALUE, comparator);
                        for (int i = 0; i < model4.size(); i++) {
                            s.add(model4.get(i));
                        }
                        model4.removeAllElements();
                        ListIterator<Item> ite = s.listIterator();
                        while (ite.hasNext()) {
                            model4.addElement(ite.next());
                        }
                        itemlist4.setModel(model4);
                        break;
                    }
                }


            }
        } else {
            if (e.getSource() instanceof JButton) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals("Delete ")) {
                    Department dep = null;
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            if (itemlist1.isSelectionEmpty()) break;
                            dep = Store.getInstance().getDepartment(id1);
                            dep.removeItem(model1.elementAt(itemlist1.getSelectedIndex())); //deleting from store
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.REMOVE, dep.getId(), model1.elementAt(itemlist1.getSelectedIndex()).getID()));
                            model1.removeElementAt(itemlist1.getSelectedIndex()); //deleting from list
                            break;
                        case 1:
                            if (itemlist2.isSelectionEmpty()) break;
                            dep = Store.getInstance().getDepartment(id2);
                            dep.removeItem(model2.elementAt(itemlist2.getSelectedIndex()));
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.REMOVE, dep.getId(), model2.elementAt(itemlist2.getSelectedIndex()).getID()));
                            model2.removeElementAt(itemlist2.getSelectedIndex());
                            break;
                        case 3:
                            if (itemlist3.isSelectionEmpty()) break;
                            dep = Store.getInstance().getDepartment(id3);
                            dep.removeItem(model3.elementAt(itemlist3.getSelectedIndex()));
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.REMOVE, dep.getId(), model3.elementAt(itemlist3.getSelectedIndex()).getID()));
                            model3.removeElementAt(itemlist3.getSelectedIndex());
                            break;
                        case 4:
                            if (itemlist4.isSelectionEmpty()) break;
                            dep = Store.getInstance().getDepartment(id4);
                            dep.removeItem(model4.elementAt(itemlist4.getSelectedIndex()));
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.REMOVE, dep.getId(), model4.elementAt(itemlist4.getSelectedIndex()).getID()));
                            model4.removeElementAt(itemlist4.getSelectedIndex());
                            break;
                    }
                }
                if (button.getText().equals("  Add   ")) {
                    Item item = null;
                    Department dep = null;
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            if (txt1.getText().isEmpty() || txt3.getText().isEmpty()) break;
                            item = new Item(txt1.getText(), Integer.parseInt(txt2.getText()), Double.parseDouble(txt3.getText()), new BookDepartment(id1));
                            dep = Store.getInstance().getDepartment(id1);  //adding item in the store
                            dep.addItem(item);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.ADD, dep.getId(), item.getID()));
                            model1.addElement(item); //adding item in the list
                            itemlist1.setModel(model1);
                            break;
                        case 1:
                            if (txt5.getText().isEmpty() || txt7.getText().isEmpty()) break;
                            item = new Item(txt5.getText(), Integer.parseInt(txt6.getText()), Double.parseDouble(txt7.getText()), new MusicDepartment(id2));
                            dep = Store.getInstance().getDepartment(id2);
                            dep.addItem(item);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.ADD, dep.getId(), item.getID()));
                            model2.addElement(item);
                            itemlist2.setModel(model2);
                            break;
                        case 2:
                            if (txt9.getText().isEmpty() || txt11.getText().isEmpty()) break;
                            item = new Item(txt9.getText(), Integer.parseInt(txt10.getText()), Double.parseDouble(txt11.getText()), new SoftwareDepartment(id3));
                            dep = Store.getInstance().getDepartment(id3);
                            dep.addItem(item);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.ADD, dep.getId(), item.getID()));
                            model3.addElement(item);
                            itemlist3.setModel(model3);
                            break;
                        case 3:
                            if (txt13.getText().isEmpty() || txt15.getText().isEmpty()) break;
                            item = new Item(txt13.getText(), Integer.parseInt(txt14.getText()), Double.parseDouble(txt15.getText()), new VideoDepartment(id4));
                            dep = Store.getInstance().getDepartment(id4);
                            dep.addItem(item);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.ADD, dep.getId(), item.getID()));
                            model4.addElement(item);
                            itemlist4.setModel(model4);
                            break;
                    }

                }
                if (button.getText().equals(" Modify")) {
                    Item item = null;
                    Department dep = null;
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            if (itemlist1.isSelectionEmpty()) break;
                            item = new Item(txt1.getText(), Integer.parseInt(txt2.getText()), Double.parseDouble(txt3.getText()), new BookDepartment(id1));
                            dep = Store.getInstance().getDepartment(id1);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.MODIFY, dep.getId(), item.getID()));
                            model1.setElementAt(item, itemlist1.getSelectedIndex());
                            ;
                            itemlist1.setModel(model1);
                            break;

                        case 1:
                            if (itemlist2.isSelectionEmpty()) break;
                            item = new Item(txt5.getText(), Integer.parseInt(txt6.getText()), Double.parseDouble(txt7.getText()), new MusicDepartment(id2));
                            dep = Store.getInstance().getDepartment(id2);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.MODIFY, dep.getId(), item.getID()));
                            model2.setElementAt(item, itemlist2.getSelectedIndex());
                            itemlist2.setModel(model2);
                            break;
                        case 2:
                            if (itemlist3.isSelectionEmpty()) break;
                            item = new Item(txt9.getText(), Integer.parseInt(txt10.getText()), Double.parseDouble(txt11.getText()), new SoftwareDepartment(id3));
                            dep = Store.getInstance().getDepartment(id3);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.MODIFY, dep.getId(), item.getID()));
                            model3.setElementAt(item, itemlist3.getSelectedIndex());
                            ;
                            itemlist3.setModel(model3);
                            break;
                        case 3:
                            if (itemlist4.isSelectionEmpty()) break;
                            item = new Item(txt13.getText(), Integer.parseInt(txt14.getText()), Double.parseDouble(txt15.getText()), new VideoDepartment(id4));
                            dep = Store.getInstance().getDepartment(id4);
                            dep.notifyAllObservers(new Notification(new Date(), NotificationType.MODIFY, dep.getId(), item.getID()));
                            model4.setElementAt(item, itemlist4.getSelectedIndex());
                            itemlist4.setModel(model4);
                            break;
                    }

                }
                if (button.getText().equals("Get customers")) {
                    dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setSize(new Dimension(500, 500));
                    dialog.setBackground(new Color(189, 214, 240));
                    JPanel tmp = new JPanel();
                    tmp.setPreferredSize(new Dimension(450, 450));
                    tmp.setBackground(new Color(189, 214, 240));
                    dialog.setVisible(true);

                    DefaultListModel<String> tmpmod = new DefaultListModel<String>();
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            for (int i = 0; i < Store.getInstance().getDepartment(id1).getCustomers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id1).getCustomers().elementAt(i).getName());
                            }
                            break;
                        case 1:
                            for (int i = 0; i < Store.getInstance().getDepartment(id2).getCustomers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id2).getCustomers().elementAt(i).getName());
                            }
                            break;
                        case 3:
                            for (int i = 0; i < Store.getInstance().getDepartment(id3).getCustomers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id3).getCustomers().elementAt(i).getName());
                            }
                            break;
                        case 4:
                            for (int i = 0; i < Store.getInstance().getDepartment(id4).getCustomers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id4).getCustomers().elementAt(i).getName());
                            }
                            break;
                    }
                    JList<String> list = new JList<String>(tmpmod);
                    JScrollPane jscroll = new JScrollPane(list);
                    jscroll.setPreferredSize(new Dimension(200, 400));
                    tmp.add(jscroll);
                    JButton okButton = new JButton("OK");
                    okButton.setVerticalTextPosition(AbstractButton.BOTTOM);
                    okButton.setHorizontalTextPosition(AbstractButton.LEADING);
                    okButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                        }

                    });
                    tmp.add(okButton);
                    dialog.add(tmp);
                }
                if (button.getText().equals("Get observers")) {
                    dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setSize(new Dimension(500, 500));
                    dialog.setBackground(new Color(255, 128, 0));
                    JPanel tmp = new JPanel();
                    tmp.setPreferredSize(new Dimension(450, 450));
                    tmp.setBackground(new Color(255, 128, 0));
                    dialog.setVisible(true);

                    DefaultListModel<String> tmpmod = new DefaultListModel<String>();
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            for (int i = 0; i < Store.getInstance().getDepartment(id1).getObservers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id1).getObservers().elementAt(i).getName());
                            }
                            break;
                        case 1:
                            for (int i = 0; i < Store.getInstance().getDepartment(id2).getObservers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id2).getObservers().elementAt(i).getName());
                            }
                            break;
                        case 3:
                            for (int i = 0; i < Store.getInstance().getDepartment(id3).getObservers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id3).getObservers().elementAt(i).getName());
                            }
                            break;
                        case 4:
                            for (int i = 0; i < Store.getInstance().getDepartment(id4).getObservers().size(); i++) {
                                tmpmod.addElement(Store.getInstance().getDepartment(id4).getObservers().elementAt(i).getName());
                            }
                            break;
                    }
                    JList<String> list = new JList<String>(tmpmod);
                    JScrollPane jscroll = new JScrollPane(list);
                    jscroll.setPreferredSize(new Dimension(200, 400));
                    tmp.add(jscroll);
                    JButton okButton = new JButton("OK");
                    okButton.setVerticalTextPosition(AbstractButton.BOTTOM);
                    okButton.setHorizontalTextPosition(AbstractButton.LEADING);
                    okButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                        }

                    });
                    tmp.add(okButton);
                    dialog.add(tmp);
                }
                if (button.getText().equals("Most expensive")) {
                    double maxx = 0;
                    Item item = null;
                    switch (pane1.getSelectedIndex()) {
                        case 0:
                            for (int i = 0; i < Store.getInstance().getDepartment(id1).getItems().size(); i++) {
                                double price = Store.getInstance().getDepartment(id1).getItems().elementAt(i).getPrice();
                                if (price > maxx) {
                                    maxx = price;
                                    item = Store.getInstance().getDepartment(id1).getItems().elementAt(i);
                                }
                            }
                            break;
                        case 1:
                            for (int i = 0; i < Store.getInstance().getDepartment(id2).getItems().size(); i++) {
                                double price = Store.getInstance().getDepartment(id2).getItems().elementAt(i).getPrice();
                                if (price > maxx) {
                                    maxx = price;
                                    item = Store.getInstance().getDepartment(id2).getItems().elementAt(i);
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0; i < Store.getInstance().getDepartment(id3).getItems().size(); i++) {
                                double price = Store.getInstance().getDepartment(id3).getItems().elementAt(i).getPrice();
                                if (price > maxx) {
                                    maxx = price;
                                    item = Store.getInstance().getDepartment(id3).getItems().elementAt(i);
                                }
                            }
                            break;
                        case 4:
                            for (int i = 0; i < Store.getInstance().getDepartment(id4).getItems().size(); i++) {
                                double price = Store.getInstance().getDepartment(id4).getItems().elementAt(i).getPrice();
                                if (price > maxx) {
                                    maxx = price;
                                    item = Store.getInstance().getDepartment(id4).getItems().elementAt(i);
                                }
                            }
                            break;
                    }
                    dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setSize(new Dimension(500, 500));
                    dialog.setBackground(new Color(255, 128, 0));
                    JPanel tmp = new JPanel();
                    tmp.setPreferredSize(new Dimension(450, 450));
                    tmp.setBackground(new Color(255, 128, 0));
                    dialog.setVisible(true);
                    JTextArea area1 = new JTextArea("The most expensive item from the");
                    JTextArea area2 = new JTextArea("department is " + item);
                    area1.setFont(new Font("Serif", Font.BOLD, 16));
                    area2.setFont(new Font("Serif", Font.BOLD, 16));
                    tmp.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 10));
                    tmp.add(area1);
                    tmp.add(area2);
                    JButton okButton = new JButton("OK");
                    okButton.setVerticalTextPosition(AbstractButton.BOTTOM);
                    okButton.setHorizontalTextPosition(AbstractButton.LEADING);
                    okButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                        }

                    });
                    tmp.add(okButton);
                    dialog.add(tmp);

                }
            }
        }


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        switch (pane1.getSelectedIndex()) {
            case 0: {
                if (itemlist1.isSelectionEmpty())
                    return;
                if (e.getValueIsAdjusting() == false) {
                    if (itemlist1.getSelectedIndex() != -1) {
                        Item item2 = model1.getElementAt(itemlist1.getSelectedIndex());
                        txt1.setText(item2.getName());
                        txt2.setText(String.valueOf(item2.getID()));
                        txt3.setText(String.valueOf(item2.getPrice()));
                        txt4.setText(item2.getDepartment().toString());
                    }
                }
                break;
            }
            case 1: {
                if (itemlist2.isSelectionEmpty())
                    return;
                if (e.getValueIsAdjusting() == false) {
                    if (itemlist2.getSelectedIndex() != -1) {
                        Item item2 = model2.getElementAt(itemlist2.getSelectedIndex());
                        txt5.setText(item2.getName());
                        txt6.setText(String.valueOf(item2.getID()));
                        txt7.setText(String.valueOf(item2.getPrice()));
                        txt8.setText(item2.getDepartment().toString());
                    }
                }
                break;
            }
            case 2: {
                if (itemlist3.isSelectionEmpty())
                    return;
                if (e.getValueIsAdjusting() == false) {
                    if (itemlist3.getSelectedIndex() != -1) {
                        Item item2 = model3.getElementAt(itemlist3.getSelectedIndex());
                        txt9.setText(item2.getName());
                        txt10.setText(String.valueOf(item2.getID()));
                        txt11.setText(String.valueOf(item2.getPrice()));
                        txt12.setText(item2.getDepartment().toString());
                    }
                }
                break;
            }
            case 3: {
                if (itemlist4.isSelectionEmpty())
                    return;
                if (e.getValueIsAdjusting() == false) {
                    if (itemlist4.getSelectedIndex() != -1) {
                        Item item2 = model4.getElementAt(itemlist4.getSelectedIndex());
                        txt13.setText(item2.getName());
                        txt14.setText(String.valueOf(item2.getID()));
                        txt15.setText(String.valueOf(item2.getPrice()));
                        txt16.setText(item2.getDepartment().toString());
                    }
                }
                break;
            }
        }
    }

}