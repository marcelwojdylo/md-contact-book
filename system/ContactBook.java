package system;
import java.time.*;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

public class ContactBook {

    private Contact[] contacts;
    private static int numberOfContacts;




    public ContactBook() {
        initializeContactsArray(Config.CONTACT_BOOK_CAPACITY);
        loadContactsFromFile();
    }




    private void initializeContactsArray(int capacityOfArray) {
        contacts = new Contact[capacityOfArray];
    }

    private void loadContactsFromFile() {
        JSONArray contactsJSON = (JSONArray) JSONController.readJSONArrayFromFile();
        Contact[] contactsArray = JSONController.makeContactArrayFromJSONArray(contactsJSON);
        for (int i = 0; i < contactsArray.length; i++) {
            if (contactsArray[i] != null) {
                contacts[contactsArray[i].getContactID()] = contactsArray[i];
            }
        }
        updateNumberOfContacts();
    }

    private void saveContactsToFile() {
        JSONController.writeJSON(JSONController.makeJSONArrayFromContactBook(this));
    }




    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    private void updateNumberOfContacts() {
        numberOfContacts = 0;
        for (int i = 0; i < Config.CONTACT_BOOK_CAPACITY; i++) {
            if (contacts[i] != null) {numberOfContacts++;}
        }
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public Contact getContactByID(int id) {
        return contacts[id];
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
    
    public void removeContact(int contactID) {
        contacts[contactID] = null;
        updateNumberOfContacts();
        saveContactsToFile();
    }




    public Contact[] sortContactsLexicographically() {
        Contact[] result = new Contact[Config.CONTACT_BOOK_CAPACITY];
        for (int i = 0; i < contacts.length; i++) {
            result[i] = contacts[i];
        }

        for (int i = 0; i < result.length-1; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] != null && result[j] != null) {
                    String toCompareI;
                    String toCompareJ;
    
                    if (result[i].getLastName().isEmpty()) {
                        toCompareI = result[i].getFirstName();
                    } else {
                        toCompareI = result[i].getLastName();
                    }
    
                    if (result[j].getLastName().isEmpty()) {
                        toCompareJ = result[j].getFirstName();
                    } else {
                        toCompareJ = result[j].getLastName();
                    }
    
    
                    if (toCompareI.compareToIgnoreCase(toCompareJ) > 0 ) {
                        Contact temp = result[i];
                        result[i] = result[j];
                        result[j] = temp;
                    }
                }
            }
        }
        return result;
    }


    

    public void printContactBook() {
        for(int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null) {
                System.out.println("\nContact at index: " + i);
                System.out.println("Contact id: " + contacts[i].getContactID());
                System.out.println(contacts[i]);
            }
        } 
    }   
}






