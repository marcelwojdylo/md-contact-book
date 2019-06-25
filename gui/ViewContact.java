package gui;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class ViewContact extends JFrame {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static Contact contact;

    public ViewContact (Contact contact) {
        this.contact = contact;
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setLayout(new GridBagLayout());
        setUndecorated(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setComponents();
        setVisible(true);
    }

    private class ContactInfoLabel extends JLabel {
        ContactInfoLabel () {
            super(getContactInfo(contact));
        }
    }

    private String getContactInfo (Contact contact) {
        return
        "<html>" +
        "Contact ID: " + contact.getContactID() + ",<br>" +
        "First Name: " + contact.getFirstName() + ",<br>" +
        "Last Name: " + contact.getLastName() + ",<br>" +
        "Date of Birth: " + contact.getDateOfBirth() + ",<br>" +
        "Address: " + contact.getAddressLine1() + ",<br>" +
        "         " + contact.getAddressLine2() + ",<br>" +
        "Phone number: " + contact.getPhoneNumber() + ",<br>" +
        "E-mail: " + contact.getEmail() +
        "</html>";
    }

    private void setComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.weightx = 1;
        c.weighty = 0.9;
        add(new ContactInfoLabel(), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new EditButton(), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new RemoveButton(), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new BackButton(), c);
    }

    private class EditButton extends JButton implements ActionListener {
        EditButton() {
            super("Edit");
            addActionListener(this);
        }
        
        public void actionPerformed (ActionEvent e) {
            EditContact edit = new EditContact(GraphicalUserInterface.getContactBook().getContactByID(contact.getContactID()));
            dispose();
        }
    }

    private class BackButton extends JButton implements ActionListener {
        BackButton() {
            super("Back");
            addActionListener(this);
        }
        
        public void actionPerformed (ActionEvent e) {
            MainFrame mainFrame = new MainFrame();
            dispose();
        }
    }

    private class RemoveButton extends JButton implements ActionListener {
        RemoveButton() {
            super("Remove");
            addActionListener(this);
        }
        
        public void actionPerformed (ActionEvent e) {
            GraphicalUserInterface.getContactBook().removeContact(contact.getContactID());
            MainFrame mainFrame = new MainFrame();
            dispose();
        }
    }
}