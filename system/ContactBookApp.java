public class ContactBookApp {
    public static void main(String[] arg) {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact.Builder().firstName("Daniel").build();
        System.out.println(contact);
        contactBook.addContact(contact);
        contactBook.printContact(0);
    }
}

class ContactBook {
    private Contact[] contacts;
    public Contact[] getContacts() {
        return contacts;
    }

    public ContactBook() {
        initializeContactsArray(100);
    }

    private void initializeContactsArray(int capacityOfArray) {
        contacts = new Contact[capacityOfArray];
    }

    //
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
    
    private void initializeContactList(int limitOfContacts) {
        int i = 0;
        Contact[] genericContactsArray = this.genericContacts();
        while (i <= limitOfContacts) {
            Contact contact = genericContactsArray[i];
            this.contacts[contact.getContactID()] = contact;
            i++;
            System.out.println(contact.getContactID());   
            System.out.println(i);
        }
    
        // this.contactList[i] = new Contact(genericBusiness(), genericAddress(), genericPhoneNumber);
    }
    
    private Contact[] genericContacts() {
        int i = 0;
        Contact[] contactArray = new Contact[9];
        while (i <= 9) {
            //
            i++;
            System.out.println("genericContacts: "+Contact.getNumberOfContacts());
    
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
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String addressStreet;
    private final String addressHouse;
    private final String addressFlat;
    private final String addressPostcode; 
    private final String addressCity;
    private final String addressCountry;
    private final String phoneNumber;
    private final String email;

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
            "E-mail: " + email;
    }
}