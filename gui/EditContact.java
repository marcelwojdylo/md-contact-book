package gui;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class EditContact extends JFrame {

    private Mode mode;

    private CrucialField firstNameInput;
    private JLabel firstNameLabel;
    private CrucialField lastNameInput;
    private JLabel lastNameLabel;
    private JTextField dateOfBirthInput;
    private JLabel dateOfBirthLabel;
    private JTextField addressLine1Input;
    private JLabel addressLine1Label;
    private JTextField addressLine2Input;
    private JTextField phoneNumberInput;
    private JLabel phoneNumberLabel;
    private JTextField emailInput;
    private JLabel emailLabel;
    private DoneButton doneButton;
    private BackButton backButton;

    private Contact contact;
    private String firstName = "";
    private String lastName = "";
    private String dateOfBirth = "";
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String phoneNumber = "";
    private String email = "";


    EditContact () {

        mode = Mode.NEW_CONTACT;
        setUpFrame();

    }

    EditContact (Contact c) {

        contact = c;
        firstName = c.getFirstName();
        lastName = c.getLastName();
        dateOfBirth = c.getDateOfBirth();
        addressLine1 = c.getAddressLine1();
        addressLine2 = c.getAddressLine2();
        phoneNumber = c.getPhoneNumber();
        email = c.getEmail();
        mode = Mode.EDIT_CONTACT;
        setUpFrame();

    }




    private enum Mode {
        NEW_CONTACT,
        EDIT_CONTACT;
    }




    private void setUpFrame() {
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

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        GridBagConstraints c = new GridBagConstraints();

        firstNameLabel = new JLabel("First name:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.weightx = 0.1;
        add(firstNameLabel, c);
        
        firstNameInput = new CrucialField(firstName);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        add(firstNameInput, c);

        lastNameLabel = new JLabel("Last name:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.1;
        add(lastNameLabel, c);

        lastNameInput = new CrucialField(lastName);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;

        add(lastNameInput, c);

        dateOfBirthLabel = new JLabel("Date of birth:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.1;

        add(dateOfBirthLabel, c);

        dateOfBirthInput = new JTextField(dateOfBirth);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(dateOfBirthInput, c);

        addressLine1Label = new JLabel("Address:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.1;

        add(addressLine1Label, c);

        addressLine1Input = new JTextField(addressLine1);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(addressLine1Input, c);

        addressLine2Input = new JTextField(addressLine2);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(addressLine2Input, c);

        phoneNumberLabel = new JLabel("Phone number:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.weightx = 0.1;

        c.gridheight = 1;
        add(phoneNumberLabel, c);

        phoneNumberInput = new JTextField(phoneNumber);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(phoneNumberInput, c);

        emailLabel = new JLabel("E-mail:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(emailLabel, c);

        emailInput = new JTextField(email);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 1;
        add(emailInput, c);   

        backButton = new BackButton();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(backButton, c);

        doneButton = new DoneButton();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(doneButton, c);

        if (mode == Mode.NEW_CONTACT) {
            doneButton.setEnabled(false);
        }     
    }




    private class CrucialField extends JTextField implements DocumentListener {

        CrucialField (String string) {
            super(string);
            this.getDocument().addDocumentListener(this);
        }

        public void changedUpdate(DocumentEvent e) {
            if (!firstNameInput.getText().equals("") || !lastNameInput.getText().equals("")) {
                doneButton.setEnabled(true);
            } else  {
                doneButton.setEnabled(false);
            }
        }
        public void removeUpdate(DocumentEvent e) {
            if (!firstNameInput.getText().equals("") || !lastNameInput.getText().equals("")) {
                doneButton.setEnabled(true);
            } else  {
                doneButton.setEnabled(false);
            }
        }
        public void insertUpdate(DocumentEvent e) {
            if (!firstNameInput.getText().equals("") || !lastNameInput.getText().equals("")) {
                doneButton.setEnabled(true);
            } else  {
                doneButton.setEnabled(false);
            }
        }
    }




    private class BackButton extends JButton implements ActionListener {
        BackButton () {
            super("Back");
            this.addActionListener(this);
        }

        public void actionPerformed (ActionEvent e) {
            new MainFrame();
            dispose();
        }
    }




    private class DoneButton extends JButton implements ActionListener {
        DoneButton () {
            super("Done");
            addActionListener(this);
        }
        public void actionPerformed (ActionEvent e) {
            if (mode == Mode.NEW_CONTACT) {
                createContactFromFields();
            } else if (mode == Mode.EDIT_CONTACT) {
                applyChangesToContact();
            }
            new MainFrame();
            dispose();
        }
    }




    private void applyChangesToContact() {
        contact.setFirstName(firstNameInput.getText());
        contact.setLastName(lastNameInput.getText());
        contact.setPhoneNumber(phoneNumberInput.getText());
        contact.setDateOfBirth(dateOfBirthInput.getText());
        contact.setAddressLine1(addressLine1Input.getText());
        contact.setAddressLine2(addressLine2Input.getText());
        contact.setEmail(emailInput.getText());
        contact.updateContactLabel();
        GraphicalUserInterface.getContactBook().updateContact(contact);
    }

    private void createContactFromFields () {
        Contact contact = new Contact.Builder()
            .firstName(firstNameInput.getText())
            .lastName(lastNameInput.getText())
            .phoneNumber(phoneNumberInput.getText())
            .dateOfBirth(dateOfBirthInput.getText())
            .addressLine1(addressLine1Input.getText())
            .addressLine2(addressLine2Input.getText())
            .email(emailInput.getText())
            .build();
            GraphicalUserInterface.getContactBook().addContact(contact);
    }
}