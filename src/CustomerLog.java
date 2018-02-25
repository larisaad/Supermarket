import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerLog extends JFrame implements ListSelectionListener {

    JList<String> list;
    DefaultListModel<String> model;
    JScrollPane scroll;
    JPanel panel;

    CustomerLog() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(new Color(189, 214, 240));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        model = new DefaultListModel<>();
        for (int i = 0; i < Store.getInstance().getCustomers().size(); i++) {
            model.addElement(Store.getInstance().getCustomers().elementAt(i).getName());
        }
        list = new JList<>(model);
        list.addListSelectionListener(this);
        scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(100, 400));
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(189, 214, 240));
        getContentPane().add(Box.createRigidArea(new Dimension(0, 5)));
        JLabel lb = new JLabel("Please select who you are ^_^", JLabel.CENTER);
        add(lb);
        panel.add(scroll);
        add(panel);
        show();
        pack();

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (list.isSelectionEmpty())
            return;
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() != -1) {
                new CustomerFrame(model.getElementAt(list.getSelectedIndex())).setVisible(true);
                this.dispose();
            }
        }
    }


}
