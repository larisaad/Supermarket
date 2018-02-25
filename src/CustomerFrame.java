import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends JFrame implements ActionListener, ListSelectionListener {
    private JTabbedPane pane;
    private JComponent panel1, panel2, panel3;
    private JPanel profile, big1, big2;
    private Customer c;
    private ShoppingCart cart;
    private WishList w;
    private JList<Item> list1, list2;
    private DefaultListModel<Item> model1, model2;
    private JScrollPane scroll1, scroll2;
    private JButton add, delete;
    private JDialog dialog;
    JLabel lb, lb3;

    CustomerFrame(String customer) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(550, 550));
        getContentPane().setBackground(new Color(189, 214, 240));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        getContentPane().add(Box.createVerticalGlue());

        c = Store.getInstance().getCustomer(customer);
        cart = c.getShoppingCart();
        w = c.getWishList();
        pane = new JTabbedPane();
        pane.setPreferredSize(new Dimension(550, 450));
        panel1 = makeTextPanel("ShoppingCart");
        panel1.setPreferredSize(new Dimension(250, 450));
        panel1.setBackground(new Color(153, 51, 255));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        JButton buy = new JButton("BUY");
        lb = new JLabel(String.format("%.2f", c.getShoppingCart().getBudget()));
        JLabel lb2 = new JLabel("Total");
        lb3 = new JLabel(String.format("%.2f", c.getShoppingCart().getPrice()));
        panel1.add(lb);
        panel1.add(Box.createRigidArea(new Dimension(30, 10)));
        ImageIcon icontotal = createImageIcon("/Images/total.png", "a pretty but meaningless splat");
        ImageIcon icontot = new ImageIcon(getScaledImage(icontotal.getImage(), 50, 50));
        JLabel imag = new JLabel(icontot);
        panel1.add(imag);
        panel1.add(Box.createRigidArea(new Dimension(30, 10)));
        panel1.add(lb2);
        panel1.add(lb3);
        panel1.add(Box.createRigidArea(new Dimension(30, 10)));
        panel1.add(buy);
        model1 = new DefaultListModel<>();
        ListIterator<Item> ite = cart.listIterator();
        while (ite.hasNext()) {
            model1.addElement(ite.next());
        }
        System.out.println(model1);
        list1 = new JList<>(model1);
        list1.addListSelectionListener(this);
        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list1.setVisibleRowCount(-1);
        scroll1 = new JScrollPane(list1);
        scroll1.setPreferredSize(new Dimension(100, 50));

        JPanel aux = new JPanel();
        aux.setPreferredSize(new Dimension(200, 400));
        aux.setBackground(new Color(153, 51, 255));
        aux.setLayout(new BoxLayout(aux, BoxLayout.PAGE_AXIS));
        aux.add(Box.createRigidArea(new Dimension(100, 50)));
        aux.add(scroll1);
        aux.add(Box.createRigidArea(new Dimension(100, 300)));

        big1 = new JPanel();
        big1.setPreferredSize(new Dimension(550, 450));
        big1.setBackground(new Color(127, 0, 255));
        big1.setLayout(new GridLayout(1, 2));
        big1.add(panel1);
        big1.add(aux);

        pane.addTab("ShoppingCart", null, big1, "Does nothing at all");
        pane.setMnemonicAt(0, KeyEvent.VK_0);


        panel2 = makeTextPanel("WishList");
        panel2.setPreferredSize(new Dimension(250, 450));
        panel2.setBackground(new Color(255, 108, 0));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.add(Box.createRigidArea(new Dimension(30, 100)));

        model2 = new DefaultListModel<>();
        ite = w.listIterator();
        while (ite.hasNext()) {
            model2.addElement(ite.next());
        }
        list2 = new JList<>(model2);
        list2.addListSelectionListener(this);
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list2.setVisibleRowCount(-1);
        scroll2 = new JScrollPane(list2);
        scroll2.setPreferredSize(new Dimension(100, 50));
        JPanel auxx = new JPanel();
        auxx.setPreferredSize(new Dimension(200, 400));
        auxx.setBackground(new Color(255, 108, 0));
        auxx.setLayout(new BoxLayout(auxx, BoxLayout.PAGE_AXIS));
        auxx.add(Box.createRigidArea(new Dimension(100, 50)));
        auxx.add(scroll2);
        auxx.add(Box.createRigidArea(new Dimension(100, 300)));
        big2 = new JPanel();
        big2.setPreferredSize(new Dimension(550, 450));
        big2.setBackground(new Color(255, 108, 0));
        big2.setLayout(new GridLayout(1, 2));
        big2.add(panel2);
        big2.add(auxx);

        pane.addTab("WishList", null, big2, "Does nothing at all");
        pane.setMnemonicAt(1, KeyEvent.VK_1);

        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(250, 450));
        panel3.setBackground(new Color(153, 51, 255));
        panel3.setLayout(new FlowLayout());
        pane.addTab("Notifications", null, panel3, "You could see your notifications");
        pane.setMnemonicAt(2, KeyEvent.VK_2);
        JTable table;
        DefaultTableModel model;
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Type");
        model.addColumn("Item ID");
        model.addColumn("Department ID");
        for (int i = 0; i < Store.getInstance().getCustomer(c.getName()).getNotifications().size(); i++) {
            String prop[] = new String[4];
            prop[0] = Store.getInstance().getCustomer(c.getName()).getNotifications().elementAt(i).date.toString();
            prop[1] = Store.getInstance().getCustomer(c.getName()).getNotifications().elementAt(i).type.toString();
            prop[2] = String.valueOf(Store.getInstance().getCustomer(c.getName()).getNotifications().elementAt(i).itemID);
            prop[3] = String.valueOf(Store.getInstance().getCustomer(c.getName()).getNotifications().elementAt(i).departmentID);
            model.addRow(prop);
        }
        table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(table);
        panel3.add(scrollpane);
        profile = new JPanel();
        profile.setLayout(new GridLayout(1, 3));
        profile.setPreferredSize(new Dimension(550, 100));
        profile.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        profile.setBackground(new Color(189, 214, 240));
        ImageIcon icon = createImageIcon("/Images/girl.png", "a pretty but meaningless splat");
        Image icon2 = getScaledImage(icon.getImage(), 80, 80);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel image = new JLabel(icon3);
        profile.add(image);
        JLabel name = new JLabel("Hello, " + c.getName() + "! ");
        profile.add(name);
        add(profile);
        add(pane);
        show();
        pack();

    }

    protected JComponent makeTextPanel(String text) {

        JPanel panel = new JPanel(false);
        panel.setPreferredSize(new Dimension(250, 450));

        add = new JButton("  Add   ");
        delete = new JButton("Delete");
        add.addActionListener(this);
        delete.addActionListener(this);

        panel.add(Box.createRigidArea(new Dimension(10, 60)));
        panel.add(add);
        panel.add(Box.createRigidArea(new Dimension(10, 5)));
        panel.add(delete);
        if (text.equals("ShoppingCart")) {

            panel.add(Box.createRigidArea(new Dimension(10, 25)));
            ImageIcon labelIcon = createImageIcon("/Images/budget.png", "a pretty but meaningless splat");
            ImageIcon labelIconPrime = new ImageIcon(getScaledImage(labelIcon.getImage(), 50, 50));
            JLabel budget = new JLabel(labelIconPrime);
            budget.setHorizontalAlignment(JLabel.CENTER);
            JLabel budgetText = new JLabel("Budget: ");
            budgetText.setHorizontalAlignment(JLabel.CENTER);
            panel.add(budget);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(budgetText);
            panel.add(Box.createRigidArea(new Dimension(10, 5)));

        } else {
        }
        return panel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Delete")) {
                switch (pane.getSelectedIndex()) {
                    case 0:
                        if (list1.isSelectionEmpty()) return;
                        Store.getInstance().getCustomer(c.getName()).getShoppingCart().remove(model1.elementAt(list1.getSelectedIndex()));
                        cart.remove(model1.elementAt(list1.getSelectedIndex()));
                        model1.removeElementAt(list1.getSelectedIndex());
                        lb.setText(String.format("%.2f", Store.getInstance().getCustomer(c.getName()).getShoppingCart().getBudget()));
                        lb3.setText(String.format("%.2f", Store.getInstance().getCustomer(c.getName()).getShoppingCart().getPrice()));

                        break;
                    case 1:
                        if (list2.isSelectionEmpty()) return;
                        Store.getInstance().getCustomer(c.getName()).getShoppingCart().remove(model2.elementAt(list2.getSelectedIndex()));
                        cart.remove(model2.elementAt(list2.getSelectedIndex()));
                        model2.removeElementAt(list2.getSelectedIndex());
                        break;

                }
            }
            if (button.getText().equals("  Add   ")) {
                Vector<Item> items;
                JList<Item> list;
                DefaultListModel<Item> model;
                JScrollPane listScroller;
                JLabel label1;
                JLabel label2;
                JPanel panel1;
                dialog = new JDialog();
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setSize(new Dimension(500, 500));
                dialog.setVisible(true);
                dialog.setBackground(new Color(189, 214, 240));
                items = new Vector<>();
                Vector<Department> departments = Store.getInstance().getDepartments();
                for (int i = 0; i < departments.size(); i++) {
                    items.addAll(departments.elementAt(i).getItems());
                }
                model = new DefaultListModel<>();
                for (int i = 0; i < items.size(); i++)
                    model.addElement(items.elementAt(i));

                list = new JList<>(model);
                list.addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (list.isSelectionEmpty())
                            return;
                        if (e.getValueIsAdjusting() == false) {
                            if (list.getSelectedIndex() != -1) {

                                switch (pane.getSelectedIndex()) {
                                    case 0:

                                        Store.getInstance().getCustomer(c.getName()).getShoppingCart().add(model.getElementAt(list.getSelectedIndex()));
                                        model1.addElement(model.getElementAt(list.getSelectedIndex()));
                                        list1.setModel(model1);
                                        lb.setText(String.format("%.2f", Store.getInstance().getCustomer(c.getName()).getShoppingCart().getBudget()));
                                        lb3.setText(String.format("%.2f", Store.getInstance().getCustomer(c.getName()).getShoppingCart().getPrice()));

                                        break;
                                    case 1:
                                        Store.getInstance().getCustomer(c.getName()).getWishList().add(model.getElementAt(list.getSelectedIndex()));
                                        model2.addElement(model.getElementAt(list.getSelectedIndex()));
                                        list2.setModel(model2);
                                        break;

                                }
                            }
                        }
                    }
                });
                list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                list.setVisibleRowCount(-1);
                listScroller = new JScrollPane(list);
                listScroller.setPreferredSize(new Dimension(700, 500));
                label1 = new JLabel("Store Items", JLabel.CENTER);
                label1.setVerticalTextPosition(JLabel.CENTER);
                label1.setHorizontalTextPosition(JLabel.CENTER);
                label2 = new JLabel("<Please select one item to add to your ShoppingCart>", JLabel.CENTER);
                JButton okButton = new JButton("OK");
                okButton.setVerticalTextPosition(AbstractButton.BOTTOM);
                okButton.setHorizontalTextPosition(AbstractButton.LEADING);
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                    }

                });
                panel1 = new JPanel(new GridLayout());
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(label1);
                panel1.add(label2);
                panel1.add(listScroller, BorderLayout.CENTER);
                panel1.add(okButton);
                dialog.add(panel1);

            }
            if (button.getText().equals("BUY")) {
                double newbudget = c.getShoppingCart().getBudget() - c.getShoppingCart().getPrice();
                c.setShoppingCart(new ShoppingCart(newbudget));
                model1.removeAllElements();
            }

        }
    }

    protected ImageIcon createImageIcon(String path, String description) {

        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file");
            return null;
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
