public class ContactBookApp {
    public static void main(String[] arg) {
        System.out.println("\n");
        ContactBook contactBook = new ContactBook();
        contactBook.printContactsWithLastNameInitial('C');
        contactBook.printContactsWithLastNameInitial('U');
    }
}

class ContactBook {
    private Contact[] contacts;
    public Contact[] getContacts() {
        return contacts;
    }

    public ContactBook() {
        initializeContactsArray(100);
        fillArrayWithGenericContacts(10);
    }

    private void initializeContactsArray(int capacityOfArray) {
        contacts = new Contact[capacityOfArray];
    }

    // METHODS FOR MANIPULATING CONTACT OBJECTS IN ARRAY

    public void addContact(Contact contact) {
        this.contacts[contact.getContactID()] = contact;        
    }    
    
    public void removeContact(Contact contact) {
        this.contacts[contact.getContactID()] = null;
    }
    
    public void removeContact(int contactID) {
        this.contacts[contactID] = null;
    }
    
    // METHODS FOR SORTING CONTACTS 
    
    public void printContactsWithLastNameInitial(char initial) {
        for (int i = 0; i <= Contact.getNumberOfContacts(); i ++) {
            if (contacts[i] == null) {
                continue;
            }
            if (contacts[i].lastName.charAt(0) == initial) {
                System.out.println(contacts[i]);
            }

        }
    }
    
    // METHODS FOR PRINTING
    
    public void printContactBook() {
        for(int i = 0; i < Contact.getNumberOfContacts(); i++) {
            if (this.contacts[i] == null) {
                continue;
            }
            System.out.println(this.contacts[i]);
        } 
    }   
    
    public void printContact(int contactID) {
        System.out.println(this.getContacts()[contactID]);
    
    }
    
    // METHODS INTENDED FOR TESTING PURPOSES
    
    private void fillArrayWithGenericContacts(int limitOfContacts) {
        int i = 0;
        Contact[] genericContactsArray = this.genericContacts();
        while (i <= limitOfContacts-1) {
            Contact contact = genericContactsArray[i];
            this.contacts[contact.getContactID()] = contact;
            i++;
        }
    }
    
    private Contact[] genericContacts() {
        int i = 0;
        Contact[] contactArray = new Contact[10];
        while (i <= 9) {
            contactArray[i] = new Contact.Builder()
                .lastName(this.genericSurnames()[i])
                .firstName("Generyk")
                .phoneNumber("666-666-666")
                .dateOfBirth("13/12/1990")
                .addressStreet("Rajska")
                .addressHouse("2")
                .addressFlat("96")
                .addressPostcode("02-972")
                .addressCity("Warszawa")
                .addressCountry("Polska")
                .email("generyk@generyk.gn")
                .build();
            i++;    
        }
        return contactArray;
    }
    
    // METHODS INTENDED FOR TESTING
    private long genericPhoneNumber = 444222113;
    
    private String[] genericSurnames() {
        String[] stringArray = new String[10];
        stringArray[0] = "Abrowski";
        stringArray[1] = "Boniewski";
        stringArray[2] = "Czarnobylski";
        stringArray[3] = "Czekowski";
        stringArray[4] = "Frajowski";
        stringArray[5] = "Szakajowski";
        stringArray[6] = "Bombalakowski";
        stringArray[7] = "Maksinski";
        stringArray[8] = "Goleniowski";
        stringArray[9] = "Umbajski";
        return stringArray;
    }

}









class Contact {
    private static int numberOfContacts = 0;
    public static int getNumberOfContacts () {
        return numberOfContacts;
    }

    private final int contactID;
    public int getContactID() {
        return contactID;
    }
    public final String firstName;
    public final String lastName;
    public final String dateOfBirth;
    public final String addressStreet;
    public final String addressHouse;
    public final String addressFlat;
    public final String addressPostcode; 
    public final String addressCity;
    public final String addressCountry;
    public final String phoneNumber;
    public final String email;

    public static class Builder {
        //Required parameters
        private final int contactID;

        //Optional parameters and their defaults
        private String firstName = "";
        private String lastName = "";
        private String dateOfBirth = "";
        private String addressStreet = "";
        private String addressHouse = "";
        private String addressFlat = "";
        private String addressPostcode = "";
        private String addressCity = "";
        private String addressCountry = "";
        private String phoneNumber = "";
        private String email = "";

        public Builder () {
            contactID = Contact.numberOfContacts;
            Contact.numberOfContacts++;
        }

        public Builder firstName (String value) {
            firstName = value;
            return this;
        }

        public Builder lastName (String value) {
            lastName = value;
            return this;
        }

        public Builder dateOfBirth (String value) {
            dateOfBirth = value;
            return this;
        }

        public Builder addressStreet (String value) {
            addressStreet = value;
            return this;
        }

        public Builder addressHouse (String value) {
            addressHouse = value;
            return this;
        }

        public Builder addressFlat (String value) {
            addressFlat = value;
            return this;
        }

        public Builder addressPostcode (String value) {
            addressPostcode = value;
            return this;
        }

        public Builder addressCity (String value) {
            addressCity = value;
            return this;
        }

        public Builder addressCountry (String value) {
            addressCountry = value;
            return this;
        }

        public Builder phoneNumber (String value) {
            phoneNumber = value;
            return this;
        }

        public Builder email (String value) {
            email = value;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    private Contact (Builder builder) {
        contactID = builder.contactID;
        firstName = builder.firstName;
        lastName = builder.lastName;
        dateOfBirth = builder.dateOfBirth;
        addressStreet = builder.addressStreet;
        addressHouse = builder.addressHouse;
        addressFlat = builder.addressFlat;
        addressPostcode = builder.addressPostcode; 
        addressCity = builder.addressCity;
        addressCountry = builder.addressCountry;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
    }

    public String toString() {
        return 
            "Contact ID: " + contactID + ",\n" +
            "First Name: " + firstName + ",\n" +
            "Last Name: " + lastName + ",\n" +
            "Date of Birth: " + dateOfBirth + ",\n" +
            "Address:" + addressStreet + " " + addressHouse + "/" + addressFlat + ",\n" +
            "         " + addressPostcode + " " + addressCity + ", " + addressCountry + ",\n" +
            "Phone number: " + phoneNumber + ",\n" +
            "E-mail: " + email + "\n";
    }
}