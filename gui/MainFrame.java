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

    private static Contact[] contacts = new Contact[Config.CONTACT_BOOK_CAPACITY];
    private ContactLabelsList labelsList;

    public MainFrame() {
        
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setLayout(new GridLayout(3, 1, 50, 50));
        setUndecorated(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeContactLabels();
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

        setLayout(new GridBagLayout());
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

        labelsList = new ContactLabelsList(contacts);
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
            System.out.println("Click!");
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
        if (contact.getAddressCity().contains(string)) {return true;}
        if (contact.getAddressCountry().contains(string)) {return true;}
        if (contact.getAddressStreet().contains(string)) {return true;}
        if (contact.getFirstName().contains(string)) {return true;}
        if (contact.getLastName().contains(string)) {return true;}
        if (contact.getEmail().contains(string)) {return true;}
        return false;
    }

    private Contact[] filterContactsByString (String string) {
        Contact[] temp = new Contact[contacts.length];
        int resultCount = 0;
        System.out.println(temp.length);
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null && containsString(contacts[i], string)) {
                temp[i] = contacts[i];
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

        Contact[] temp = GraphicalUserInterface.getContactBook().sortContactsByLastNameInitial();
        Contact[] results = new Contact[GraphicalUserInterface.getContactBook().getNumberOfContacts()];
        System.out.println(GraphicalUserInterface.getContactBook().getNumberOfContacts());
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                results[index] = temp[i];
                index++;
            }
        }
        contacts = results;
       
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
                int index = this.getSelectedIndex();
                System.out.println("Now selected: " + contacts[index]);
            }
        }



    
    }



    // private void setActions() {
    //     showContactByID.addActionListener(this);
    // }

    // public void actionPerformed(ActionEvent event) {
    //     if (event.getSource() == showContactByID) {
    //         try {
    //             contactInfo.setText(GraphicalUserInterface.getContactBook().getContacts()[Integer.parseInt(contactID.getText())].toString());
    //         } catch (ArrayIndexOutOfBoundsException exception) {
    //             contactInfo.setText("No such contact ID!");
    //         } catch (NullPointerException exception) {
    //             contactInfo.setText("No such contact ID!");
    //         } catch (NumberFormatException exception) {
                
    //         }
    //     }
    // }
}