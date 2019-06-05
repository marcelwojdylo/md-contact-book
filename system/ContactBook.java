package system;





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

    public Contact[] sortContactsByLastNameInitial() {
        Contact[] result = new Contact[Contact.getNumberOfContacts()];
        int indexCounter = 0;

        for (int i = 0; i < Constants.LETTERS.length(); i++) {
            for (int j = 0; j < Contact.getNumberOfContacts(); j++) {
                if (contacts[j] == null) {
                    continue;
                }

                if (contacts[j].lastName.charAt(0) == Constants.LETTERS.charAt(i)) {
                    result[indexCounter] = contacts[j];
                    indexCounter++;
                }
            }
        }

        return result;
    }
    
    public Contact[] getContactsWithLastNameInitial(char initial) {
        Contact[] result = new Contact[Contact.getNumberOfContacts()];
        for (int i = 0; i <= Contact.getNumberOfContacts(); i ++) {
            if (contacts[i] == null) {
                continue;
            }
            if (contacts[i].lastName.charAt(0) == initial) {
                result[i] = contacts[i];
            }

        }

        return result;
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
                .phoneNumber("666-666-666oijdsfknadsf")
                .dateOfBirth(13, 12, 1990)
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
    
    // METHODS INTENDED FOR TESTING
    private long genericPhoneNumber = 444222113;
    
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

}






