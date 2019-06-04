
public class ContactBookApp {
    public static void main(String[] arg) {
        System.out.println("\n");
        ContactBook contactBook = new ContactBook();
        // contactBook.printContactBook();
        contactBook.printContactsSortedByLastNameInitial();
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
    public final int[] dateOfBirth;
    private static final int DAY_OF_BIRTH_INDEX = 0;
    private static final int MONTH_OF_BIRTH_INDEX = 1;
    private static final int YEAR_OF_BIRTH_INDEX = 2;
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
        private int[] dateOfBirth = new int[3];
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
            firstName = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder lastName (String value) {
            lastName = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder dateOfBirth (int day, int month, int year) {
            dateOfBirth[DAY_OF_BIRTH_INDEX] = day;
            dateOfBirth[MONTH_OF_BIRTH_INDEX] = month;
            dateOfBirth[YEAR_OF_BIRTH_INDEX] = year;
            return this;
        }

        public Builder addressStreet (String value) {
            addressStreet = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder addressHouse (String value) {
            addressHouse = value;
            return this;
        }

        public Builder addressHouse (int value) {
            String string = Integer.toString(value);
            addressHouse = string;
            return this;
        }

        public Builder addressFlat (String value) {
            addressFlat = value;
            return this;
        }

        public Builder addressFlat (int value) {
            String string = Integer.toString(value);
            addressFlat = string;
            return this;
        }

        public Builder addressPostcode (String value) {
            addressPostcode = value;
            return this;
        }

        public Builder addressCity (String value) {
            addressCity = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder addressCountry (String value) {
            addressCountry = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder phoneNumber (String value) {
            phoneNumber = ContactDataFormatter.formatPhoneNumber(value);
            return this;
        }

        public Builder phoneNumber (int value) {
            String string = Integer.toString(value);
            phoneNumber = ContactDataFormatter.formatPhoneNumber(string);
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

    // TODO: CONVERT TO LIST<STRING> TYPE OBJECT

    private static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }
        return result;
    }



    //METHODS FOR PRINTING

    public String toString() {
        String result = "";


        return 
            "Contact ID: " + contactID + ",\n" +
            "First Name: " + firstName + ",\n" +
            "Last Name: " + lastName + ",\n" +
            "Date of Birth: " + dateOfBirth[DAY_OF_BIRTH_INDEX] + "/" + dateOfBirth[MONTH_OF_BIRTH_INDEX] + "/" + dateOfBirth[YEAR_OF_BIRTH_INDEX] + ",\n" +
            "Address:" + addressStreet + " " + addressHouse + "/" + addressFlat + ",\n" +
            "         " + addressPostcode + " " + addressCity + ", " + addressCountry + ",\n" +
            "Phone number: " + phoneNumber + ",\n" +
            "E-mail: " + email + "\n";
    }
}
















class Constants {
    public final static String DIGITS = "1234567890";
    public final static String LETTERS = "aąbcćdeęfghijklłmnoópqrsśtuvwxyzżźAĄBCĆDEĘFGHIJKLŁMNOÓPQRSŚTUVWXYZŻŹ";
}











class ContactDataFormatter {








    public static String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = removeNonDigits(phoneNumber);
        return formattedNumber;
    }

    public static String formatPhoneNumber(int phoneNumber) {
        String formattedNumber = Integer.toString(phoneNumber);
        return formattedNumber;
    }


    
    private static String removeNonDigits(String number) {
        String result = "";
        for (int i = 0; i < number.length(); i++) {
            if(isDigit(number.charAt(i))) {
                result += number.charAt(i);
            }
        }
        return result;
    }

    private static String charRemoveAt(String string, int index) {
        return string.substring(0, index) + string.substring(index);
    }

    private static boolean isDigit(char character) {
        for (int i = 0; i < Constants.DIGITS.length(); i++) {
            if (character == Constants.DIGITS.charAt(i)) {
                return true;
            }
        }
        return false;
    }




    public static String formatProperName(String string) {
        String formattedProperName = removeNonLetters(string);
        formattedProperName = capitaliseString(formattedProperName);
        return formattedProperName;
    }


    private static String removeNonLetters(String string) {
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            if (isLetter(string.charAt(i))) {
                result += string.charAt(i);
            }
        }
        return result;
    }

    private static boolean isLetter(char character) {
        for (int i = 0; i < Constants.LETTERS.length(); i++) {
            if (character == Constants.LETTERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static String capitaliseString(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

}













