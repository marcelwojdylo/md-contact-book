package system;
import java.time.*;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;
@SuppressWarnings("unchecked")

public class ContactBook {

    private static Contact[] contacts;
    public Contact[] getContacts() {
        return contacts;
    }

    private static int contactBookCapacity = 100;
    public static int getContactBookCapacity() {
        return contactBookCapacity;
    }

    private static int numberOfContacts = 0;
    public int getNumberOfContacts() {
        return numberOfContacts;
    }





    public ContactBook() {
        initializeContactsArray(contactBookCapacity);
        // fillArrayWithGenericContacts(10);
        loadContactsFromFile();
    }



    private void initializeContactsArray(int capacityOfArray) {
        contacts = new Contact[capacityOfArray];
    }



    public void addContact(Contact contact) {
        contacts[contact.getContactID()] = contact;        
        numberOfContacts++;
        saveContactsToFile();
    }    
    
    public void removeContact(Contact contact) {
        contacts[contact.getContactID()] = null;
        numberOfContacts--;
        saveContactsToFile();

    }
    
    public void removeContact(int contactID) {
        contacts[contactID] = null;
        numberOfContacts--;
        saveContactsToFile();

    }

    public static boolean isPresent (int contactID) {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i].getContactID() == contactID) {
                return true;
            }
        }
        return false;
    }



    public void setFirstName(Contact contact, String string) {
        contact.setFirstName(string);
    }

    public void setLastName(Contact contact, String string) {
        contact.setLastName(string);
    }

    public void setAddressStreet(Contact contact, String string) {
        contact.setAddressStreet(string);
    }

    public void setAddressHouse(Contact contact, String string) {
        contact.setAddressHouse(string);
    }

    public void setAddressFlat(Contact contact, String string) {
        contact.setAddressFlat(string);
    }

    public void setAddressPostcode(Contact contact, String string) {
        contact.setAddressPostcode(string);
    }

    public void setAddressCity(Contact contact, String string) {
        contact.setAddressCity(string);
    }

    public void setAddressCountry(Contact contact, String string) {
        contact.setAddressCountry(string);
    }

    public void setPhoneNumber(Contact contact, String string) {
        contact.setPhoneNumber(string);
    }

    public void setEmail(Contact contact, String string) {
        contact.setEmail(string);
    }

    public void setDateOfBirth(Contact contact, String date) {
        contact.setDateOfBirth(date);
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
            if (c == null) {continue;}
            System.out.println(c);
        }
    }

    public void printContactsWithLastNameInitial(char initial) {
        for (Contact c : getContactsWithLastNameInitial(initial)) {
            if (c == null) {continue;}
            System.out.println(c);
        }
    }
    
    public void printContact(int contactID) {
        System.out.println(getContacts()[contactID]);
    
    }



    public Contact[] sortContactsByLastNameInitial() {
        Contact[] result = new Contact[contacts.length];
        int indexCounter = 0;

        for (int i = 0; i < Constants.LETTERS.length(); i++) {
            for (int j = 0; j < contacts.length; j++) {
                if (contacts[j] == null) {
                    continue;
                }

                if (contacts[j].getLastName().charAt(0) == Constants.LETTERS.charAt(i)) {
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
    
    
    
    // METHODS INTENDED FOR TESTING PURPOSES
    
    private void fillArrayWithGenericContacts(int limitOfContacts) {
        int i = 0;
        Contact[] genericContactsArray = genericContacts();
        while (i <= limitOfContacts-1) {
            Contact contact = genericContactsArray[i];
            contacts[contact.getContactID()] = contact;
            i++;
        }
    }
    
    private Contact[] genericContacts() {
        int i = 0;
        Contact[] contactArray = new Contact[10];
        while (i <= 9) {
            contactArray[i] = new Contact.Builder()
                .lastName(genericSurnames()[i])
                .firstName("Generyk")
                .phoneNumber("666-666-666oijdsfknadsf")
                .dateOfBirth("1990-13-12")
                .addressStreet("Rajska")
                .addressHouse("2")
                .addressFlat(1293)
                .addressPostcode("02-972")
                .addressCity("warszawa")
                .addressCountry("polska")
                .email("generyk@generyk.gn")
                .build();
            i++;    
        }
        return contactArray;
    }
        
    private String[] genericSurnames() {
        String[] stringArray = new String[10];
        stringArray[0] = "Abrowski";
        stringArray[1] = "Boniewski";
        stringArray[2] = "Czarnobylski";
        stringArray[3] = "czeKOws00::::::ki";
        stringArray[4] = "Frajowski";
        stringArray[5] = "Szakajowski";
        stringArray[6] = "Bombalakowski";
        stringArray[7] = "Maksinski";
        stringArray[8] = "Goleniowski";
        stringArray[9] = "Umbajski";
        return stringArray;
    }

    private void loadContactsFromFile() {
        JSONArray contactsJSON = (JSONArray) JSONController.readJSONArrayFromFile();
        Contact[] contactsArray = JSONController.makeContactArrayFromJSONArray(contactsJSON);
        contacts = contactsArray;
    }

    private void saveContactsToFile() {
        JSONController.writeJSON(JSONController.makeJSONFromContactBook(this));
    }

}






