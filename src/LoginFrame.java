import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFrame extends JFrame implements ActionListener {

    private JButton customer, manager;
    private JPanel panel1, tmp;
    private JLabel label;

    LoginFrame() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 500));
        getContentPane().setBackground(new Color(189, 214, 240));
        setLayout(new FlowLayout());

        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(450, 450));
        panel1.setBackground(new Color(189, 214, 240));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        ImageIcon icon = createImageIcon("/Images/lock.png", "a pretty but meaningless splat");
        Image icon2 = getScaledImage(icon.getImage(), 90, 90);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel image = new JLabel(icon3);

        label = new JLabel("Please log in.");
        label.setFont(new Font("Sherif", Font.BOLD, 16));
        label.setForeground(new Color(0, 0, 51));

        customer = new JButton("I'm a customer");
        customer.setBackground(new Color(255, 128, 0));
        customer.setForeground(new Color(0, 0, 51));
        customer.setFont(new Font("Serif", Font.BOLD, 16));
        customer.setPreferredSize(new Dimension(100, 50));
        customer.addActionListener(this);

        manager = new JButton("I'm the manager");
        manager.setBackground(new Color(0, 0, 51));
        manager.setForeground(new Color(255, 128, 0));
        manager.setFont(new Font("Serif", Font.BOLD, 16));
        manager.setPreferredSize(new Dimension(100, 50));
        manager.addActionListener(this);

        tmp = new JPanel();
        tmp.setPreferredSize(new Dimension(100, 200));
        tmp.setLayout(new GridLayout(2, 1));
        tmp.add(customer);
        tmp.add(manager);
        panel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 100));
        panel1.add(Box.createVerticalGlue());
        panel1.add(image);
        panel1.add(Box.createRigidArea(new Dimension(0, 10)));
        panel1.add(label);
        panel1.add(Box.createRigidArea(new Dimension(0, 50)));
        panel1.add(tmp, BorderLayout.CENTER);
        add(panel1);

        show();
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton tmp = (JButton) e.getSource();
            if (tmp.getText().equals("I'm a customer")) {
                new CustomerLog().setVisible(true);
                this.dispose();

            }
            if (tmp.getText().equals("I'm the manager")) {
                new SecondFrame().setVisible(true);
                this.dispose();
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
