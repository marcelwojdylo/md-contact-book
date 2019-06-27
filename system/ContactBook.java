package system;
import java.time.*;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

public class ContactBook {

    private static Contact[] contacts;
    public Contact[] getContacts() {
        return contacts;
    }

    private int contactBookCapacity = Config.CONTACT_BOOK_CAPACITY;
    public int getContactBookCapacity() {
        return contactBookCapacity;
    }

    private static int numberOfContacts = 0;
    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    private void updateNumberOfContacts() {
        numberOfContacts = 0;
        for (int i = 0; i < Config.CONTACT_BOOK_CAPACITY; i++) {
            if (contacts[i] != null) {numberOfContacts++;}
        }
    }





    public ContactBook() {
        initializeContactsArray(contactBookCapacity);
        // Testing.addGenericContacts(this, 10);
        loadContactsFromFile();
    }



    private void initializeContactsArray(int capacityOfArray) {
        contacts = new Contact[capacityOfArray];
    }



    public void addContact(Contact contact) {
        contacts[contact.getContactID()] = contact;        
        updateNumberOfContacts();
        saveContactsToFile();
    }    

    public void updateContact(Contact contact) {
        contacts[contact.getContactID()] = contact;
        saveContactsToFile();
    }
    
    public void removeContact(Contact contact) {
        contacts[contact.getContactID()] = null;
        updateNumberOfContacts();
        saveContactsToFile();

    }

    public Contact getContactByID(int id) {
        return contacts[id];
    }
    
    public void removeContact(int contactID) {
        contacts[contactID] = null;
        updateNumberOfContacts();
        saveContactsToFile();

    }



    public void printContactBook() {
        for(int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null) {
                System.out.println(contacts[i]);
            }
        } 
    }   

    public void printContactsSortedByLastNameInitial() {
        for (Contact c : sortContactsByLastNameInitial()) {
            if (c != null) {
                System.out.println(c.toStringCLI());
            }
        }
    }

    public void printContactsWithLastNameInitial(char initial) {
        for (Contact c : getContactsWithLastNameInitial(initial)) {
            if (c != null) {
                System.out.println(c.toStringCLI());
            }
        }
    }
    
    public void printContact(int contactID) {
        System.out.println(getContacts()[contactID]);
    
    }



    public Contact[] sortContactsByLastNameInitial() {
        Contact[] result = new Contact[contacts.length];
        int indexCounter = 0;

        for (int i = 0; i < Config.Constants.LETTERS.length(); i++) {
            for (int j = 0; j < contacts.length; j++) {
                if (contacts[j] == null) {
                    continue;
                }

                if (contacts[j].getLastName().charAt(0) == Config.Constants.LETTERS.charAt(i)) {
                    result[indexCounter] = contacts[j];
                    indexCounter++;
                }
            }
        }

        return result;
    }
    
    public Contact[] getContactsWithLastNameInitial(char initial) {
        Contact[] result = new Contact[contacts.length];
        for (int i = 0; i <= contacts.length; i ++) {
            if (contacts[i] != null && contacts[i].getLastName().charAt(0) == initial) {
                result[i] = contacts[i];
            }
        }
        return result;
    }
    
    public String[] toArray() {
        String[] string = new String[Config.CONTACT_BOOK_CAPACITY];
        int i = 0;
        for (Contact c : contacts) {
            if (c != null) {
                string[i] = c.toString();
            }
            i++;
        }
        return string;
    }

    private void loadContactsFromFile() {
        JSONArray contactsJSON = (JSONArray) JSONController.readJSONArrayFromFile();
        Contact[] contactsArray = JSONController.makeContactArrayFromJSONArray(contactsJSON);
        contacts = contactsArray;
        updateNumberOfContacts();
    }

    private void saveContactsToFile() {
        JSONController.writeJSON(JSONController.makeJSONFromContactBook(this));
    }
}






