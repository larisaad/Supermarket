
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StoreFrame extends JFrame implements ActionListener {

    private JPanel panel1;
    private JLabel title;
    private JButton store;
    private JButton customers;
    private JButton next;
    private JFileChooser fc1, fc2;
    private JTextArea txt1, txt2;
    private File file1, file2;

    public StoreFrame() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 500));
        getContentPane().setBackground(new Color(167, 219, 232));
        setLayout(new FlowLayout());

        panel1 = new JPanel();
        panel1.setBackground(new Color(189, 214, 240));
        panel1.setPreferredSize(new Dimension(450, 450));
        add(panel1);
        ImageIcon icon = createImageIcon("/Images/store.png", "a pretty but meaningless splat");
        Image icon2 = getScaledImage(icon.getImage(), 90, 90);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel image = new JLabel(icon3);
        panel1.add(image);
        title = new JLabel("WELCOME!", JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(new Color(0, 0, 51));
        title.setFont(new Font("Serif", Font.BOLD, 16));
        panel1.add(title);

        store = new JButton("Store");
        store.setBackground(new Color(255, 128, 0));
        store.setForeground(new Color(0, 0, 51));
        store.setFont(new Font("Serif", Font.BOLD, 12));

        customers = new JButton("Customers");
        customers.setBackground(new Color(255, 128, 0));
        customers.setForeground(new Color(0, 0, 51));
        customers.setFont(new Font("Serif", Font.BOLD, 12));
        next = new JButton("Enter Store");
        next.setBackground(new Color(255, 41, 0));
        next.setForeground(new Color(0, 0, 51));
        next.setFont(new Font("Serif", Font.BOLD, 12));
        store.addActionListener(this);
        customers.addActionListener(this);
        next.addActionListener(this);
        panel1.add(store);
        panel1.add(customers);
        panel1.add(next);
        txt1 = new JTextArea();
        txt1.setFont(new Font("Serif", Font.CENTER_BASELINE, 12));
        txt1.setLineWrap(true);
        txt1.setWrapStyleWord(true);
        txt2 = new JTextArea();
        txt2.setFont(new Font("Serif", Font.CENTER_BASELINE, 12));
        txt2.setLineWrap(true);
        txt2.setWrapStyleWord(true);
        panel1.add(txt1);

        GroupLayout layout = new GroupLayout(panel1);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(75)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(image)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(15)
                                                                .addComponent(title))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(txt1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(store, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                .addGap(50)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(txt2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(customers, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(next, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(image)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(41)
                                                .addComponent(title)))
                                .addGap(65)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(store, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(customers, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(40)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(txt2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                                .addComponent(next, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(50))
        );
        panel1.setLayout(layout);


        show();
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            JButton tmpButton = (JButton) e.getSource();
            if (tmpButton.getText().equals("Store")) {
                if (fc1 == null) {
                    fc1 = new JFileChooser(); //set up file chooser
                    int returnVal = fc1.showDialog(this, "Attach");
                    if (returnVal == JFileChooser.APPROVE_OPTION) { //process the result
                        file1 = fc1.getSelectedFile();
                        txt1.setText(file1.getName());
                    } else {
                        txt1.setText("Cancelled");
                    }
                    txt1.setCaretPosition(txt1.getDocument().getLength());
                    fc1.setSelectedFile(null); //reset the file chooser for the next time it's shown
                    fc1 = null;
                }
            } else {
                if (tmpButton.getText().equals("Customers")) {
                    if (fc2 == null) {
                        fc2 = new JFileChooser(); //set up file chooser
                        int returnVal = fc2.showDialog(this, "Attach");
                        if (returnVal == JFileChooser.APPROVE_OPTION) { //process the result
                            file2 = fc2.getSelectedFile();
                            txt2.append(file2.getName());
                        } else {
                            txt2.append("Cancelled");
                        }
                        txt2.setCaretPosition(txt1.getDocument().getLength());
                        fc2.setSelectedFile(null); //reset the file chooser for the next time it's shown
                        fc2 = null;
                    }
                } else {
                    if (tmpButton.getText().equals("Enter Store")) {
                        try {
                            validate(file1, file2);
                            try {
                                Store store = Test.parseStore(file1);
                                try {
                                    Test.parseCustomers(store, file2);
                                    Test.parseEvents(store);
                                    new LoginFrame().setVisible(true);
                                    this.dispose();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } catch (InvalidFile e3) {
                            System.out.println("Please select files.");
                        }

                    }
                }
            }
        }

    }

    protected ImageIcon createImageIcon(String path, String description) {

        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file ");
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

    private void validate(File f, File g) throws InvalidFile {
        if (file1 == null || file2 == null)
            throw new InvalidFile("you haven't selected a file");
    }
}

class InvalidFile extends Exception {
    InvalidFile(String text) {
        super(text);
    }

}