package gui;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class ViewContact extends JFrame {

    private Contact contact;

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



    private void setComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.weightx = 1;
        c.weighty = 0.9;
        add(new JLabel(presentContactInfo(contact)), c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new EditButton(), c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new RemoveButton(), c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 5;
        c.weighty = 0.1;
        add(new BackButton(), c);
    }





    private String presentContactInfo (Contact source) {
        return
        "<html>" +
        "Contact ID: " + source.getContactID() + ",<br>" +
        "First Name: " + source.getFirstName() + ",<br>" +
        "Last Name: " + source.getLastName() + ",<br>" +
        "Date of Birth: " + source.getDateOfBirth() + ",<br>" +
        "Address: " + source.getAddressLine1() + ",<br>" +
        "         " + source.getAddressLine2() + ",<br>" +
        "Phone number: " + source.getPhoneNumber() + ",<br>" +
        "E-mail: " + source.getEmail() +
        "</html>";
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
}