package gui;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class MainFrame extends JFrame {

    private static Contact[] contactLabels;
    private ContactLabelsList labelsList;
    private CreateContactButton createContact;
    private SearchField search;
    private JScrollPane scrollPane;



    public MainFrame() {
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setUndecorated(false);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeContactLabels();
        setComponents();
        setVisible(true);
    }




    private void setComponents () {
        
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        GridBagConstraints c = new GridBagConstraints();

        createContact = new CreateContactButton();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.gridwidth = 1;
        add(createContact, c);

        search = new SearchField();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 19;
        c.weightx = 0.99;
        c.weighty = 0.01;
        add(search, c);

        labelsList = new ContactLabelsList(contactLabels);
        scrollPane = new JScrollPane(labelsList);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 20;
        c.gridwidth = 20;
        c.weightx = 1;
        c.weighty = 0.99;
        add(scrollPane, c);

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

    private boolean containsString (Contact contact, String string) {
        string = string.toLowerCase();
        if (contact.getFirstName().toLowerCase().contains(string)) {return true;}
        if (contact.getLastName().toLowerCase().contains(string)) {return true;}
        return false;
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
                ViewContact view = new ViewContact(GraphicalUserInterface.getContactBook().getContactByID(id));
                dispose();
            }
        }    
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
}