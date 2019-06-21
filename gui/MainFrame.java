package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import system.*;

public class MainFrame extends JFrame implements ActionListener {

    private static JTextField contactID;
    private static JButton showContactByID;
    private static JTextArea contactInfo;

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public MainFrame() {
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setLayout(new GridLayout(3, 1, 50, 50));
        setUndecorated(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setComponents();
        // setActions();
        setVisible(true);
    }

    private void setComponents () {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton createContact;
        JTextField search;
        JScrollPane scrollPane;
        JList contacts;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }

        createContact = new JButton("+");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        add(createContact, c);

        search = new JTextField("Type to search");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 5;
        c.weightx = 0.9;
        c.weighty = 0.1;
        add(search, c);

        contacts = new JList<String>(GraphicalUserInterface.getContactBook().toArray());

        scrollPane = new JScrollPane(contacts);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 10;
        c.gridwidth = 6;
        c.weightx = 1;
        c.weighty = 0.9;
        add(scrollPane, c);

    }
    private void setActions() {
        showContactByID.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == showContactByID) {
            try {
                contactInfo.setText(GraphicalUserInterface.getContactBook().getContacts()[Integer.parseInt(contactID.getText())].toString());
            } catch (ArrayIndexOutOfBoundsException exception) {
                contactInfo.setText("No such contact ID!");
            } catch (NullPointerException exception) {
                contactInfo.setText("No such contact ID!");
            } catch (NumberFormatException exception) {
                
            }
        }
    }
}