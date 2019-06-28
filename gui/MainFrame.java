package gui;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class MainFrame extends JFrame {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private static Contact[] contactLabels = new Contact[Config.CONTACT_BOOK_CAPACITY];
    private ContactLabelsList labelsList;

    public MainFrame() {
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setUndecorated(false);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // GraphicalUserInterface.getContactBook().printContactBook();

        makeContactLabels();
        // GraphicalUserInterface.getContactBook().printContactBook();

        setComponents();
        setVisible(true);
    }

    private void setComponents () {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton createContact;
        JTextField search;
        JScrollPane scrollPane;

        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }

        createContact = new CreateContactButton();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        add(createContact, c);

        search = new SearchField();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 5;
        c.weightx = 0.9;
        c.weighty = 0.1;
        add(search, c);

        labelsList = new ContactLabelsList(contactLabels);
        scrollPane = new JScrollPane(labelsList);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 9;
        c.gridwidth = 6;
        c.weightx = 1;
        c.weighty = 0.9;
        add(scrollPane, c);

    }

    @SuppressWarnings("unchecked")
    private class CreateContactButton extends JButton implements ActionListener {
        CreateContactButton() {
            super("+");
            this.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            EditContact edit = new EditContact();
            dispose();
        }
    }

    @SuppressWarnings("unchecked")
    private class SearchField extends JTextField implements DocumentListener {

        SearchField() {
            this.getDocument().addDocumentListener(this);
        }

        public void insertUpdate(DocumentEvent e) {
            labelsList.setListData(filterContactsByString(this.getText()));
        }
        public void removeUpdate(DocumentEvent e) {
            labelsList.setListData(filterContactsByString(this.getText()));
        }
        public void changedUpdate(DocumentEvent e) {

        }
    }

    private boolean containsString (Contact contact, String string) {
        string = string.toLowerCase();
        if (contact.getFirstName().toLowerCase().contains(string)) {return true;}
        if (contact.getLastName().toLowerCase().contains(string)) {return true;}
        return false;
    }

    private Contact[] filterContactsByString (String string) {
        Contact[] temp = new Contact[contactLabels.length];
        int resultCount = 0;
        for (int i = 0; i < contactLabels.length; i++) {
            if (contactLabels[i] != null && containsString(contactLabels[i], string)) {
                temp[i] = contactLabels[i];
                resultCount++;
            }
        }
        Contact[] results = new Contact[resultCount];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                results[index] = temp[i];
                index++;
            }
        }
        return results;
    }

    private void makeContactLabels() {

        Contact[] temp = GraphicalUserInterface.getContactBook().sortContactsLexicographically();
        Contact[] results = new Contact[GraphicalUserInterface.getContactBook().getNumberOfContacts()];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                results[index] = temp[i];
                index++;
            }
        }
        contactLabels = results;
       
    }

    @SuppressWarnings("unchecked")
    private class ContactLabelsList extends JList implements ListSelectionListener {
      
        ContactLabelsList(Contact[] array) {
            super(array);
            this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.addListSelectionListener(this);

        }

        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && this.getSelectedIndex() != -1) {
                Contact contact = (Contact) this.getSelectedValue();
                int id = contact.getContactID();                
                System.out.println("Trying to view contact with ID: " + id);
                ViewContact view = new ViewContact(GraphicalUserInterface.getContactBook().getContactByID(id));
                dispose();
            }
        }



    
    }
}