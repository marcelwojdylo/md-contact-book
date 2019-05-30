public class ContactBookApp {
    public static void main(String[] arg) {
        ContactBook contactBook = new ContactBook();
        contactBook.printContactBook();
        // System.out.println(Contact.numberOfContacts);
    }
}

class ContactBook{
    private int contactListCapacity = 100;
    private Contact[] contactList = new Contact[contactListCapacity];
    public Contact[] getContactList(){
        return this.contactList;
    }

    public ContactBook() {
        initializeContactList(9);
    }s

    // METHODS FOR MANIPULATING CONTACTS IN THE ARRAY

    public void addContact(Contact contact) {
        this.contactList[contact.getContactID()] = contact;        
    }    

    public void removeContact(Contact contact) {
        this.contactList[contact.getContactID()] = null;
    }

    public void removeContact(int contactID) {
        this.contactList[contactID] = null;
    }

    // METHODS FOR SORTING CONTACTS

    


    // METHODS FOR PRINTING

    public void printContactBook() {
        for(int i = 0; i < Contact.numberOfContacts; i++) {
            if (this.contactList[i] == null) {
                continue;
            }
            System.out.println(this.contactList[i]);
        } 
    }   

    public void printContact(int contactID) {
        System.out.println(this.getContactList()[contactID]);

    }

    // METHODS INTENDED FOR TESTING PURPOSES

    private void initializeContactList(int limitOfContacts) {
        int i = 0;
        Contact[] genericContactsArray = this.genericContacts();
        while (i <= limitOfContacts) {
            Contact contact = genericContactsArray[i];
            this.contactList[contact.getContactID()] = contact;
            i++;
            System.out.println(contact.getContactID());   
            System.out.println(i);
        }

        // this.contactList[i] = new Contact(genericBusiness(), genericAddress(), genericPhoneNumber);
    }

    private Contact[] genericContacts() {
        int i = 0;
        Contact[] contactArray = new Contact[10];
        while (i <= 9) {
            contactArray[i] = new Contact(genericPeople()[i], genericAddress(), genericPhoneNumber);
            i++;
            System.out.println("genericContacts: "+Contact.numberOfContacts);

        }
        return contactArray;
    }

    private Person[] genericPeople() {
        Person[] personArray = new Person[10];
        for (int i=0 ; i<=9; i++) {
            personArray[i] = new Person("Generyk", genericSurnames()[i], true);
            System.out.println("genericPeople: "+Contact.numberOfContacts);
        }
        return personArray;
    }

    private Business genericBusiness() {
        return new Business("Genericon LTD");
    }

    private Address genericAddress() {
        return new Address("Generic Str", 1, 2, "30-332", "GenCity", "GenCountry");
    }

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


// HUGE TODO: Create a Builder class for Contact objects
// TODO: Create a better data structure for storing contacts



class Contact {

    

    private int contactID;
    public int getContactID() {
        return this.contactID;
    }
    public static int numberOfContacts = 0;
    
    private long phoneNumber;
    public long getPhoneNumber() {
        return phoneNumber;
    }

    private Person person;
    private Business business;
    private Address address;
    private String photoDir;

    public Contact (
        Person person,
        Address address,
        long phoneNumber
    ) {
        this.person = person;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.makeContactID();
    }

    public Contact (
        Person person,
        Address address,
        long phoneNumber,
        boolean skipCreatingID
    ) {
        this.person = person;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.makeContactID();
    }

    public Contact (
        Business business,
        Address address,
        long phoneNumber
    ) {
        this.business = business;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.makeContactID();
    }
    public Contact (
        Business business,
        Address address,
        long phoneNumber,
        boolean skipCreatingID
    ) {
        this.business = business;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.makeContactID();
    }    

    private void makeContactID() {
        this.contactID = numberOfContacts;    
        numberOfContacts++;
    }

    @Override
    public String toString() {
        if (this.person != null) {
            return "Contact id: " + this.contactID + ",\n" + 
            "Name: " + this.person.getFirstName() + ",\n" +
            "Surname: " + this.person.getLastName() + ",\n" +
            "Address: " + this.address + ",\n" +
            "Phone number: "+ this.phoneNumber + "\n";    
        } else if (this.business != null) {
            return "Contact id: " + this.contactID + ",\n" + 
            "Name: " + this.business.getBusinessName() + ",\n" +
            "Address: " + this.address + ",\n" +
            "Phone number: "+ this.phoneNumber + "\n";              
        } else {
            return "ERROR: This contact is neither a person nor a business.";
        }
    }
}

class Business {
    private String businessName;
    public String getBusinessName() {
        return businessName;
    }
    public Business (String businessName) {
        this.businessName = businessName;
    }
}

class Person {
    private String firstName;
    public String getFirstName(){
        return firstName;
    }

    private String lastName;
    public String getLastName() {
        return lastName;
    }
    // private enum Sex {
    //     MALE, FEMALE;
    // }
    private boolean isSingle;

    public Person(
        String firstName,
        String lastName,
        boolean isSingle
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSingle = isSingle;
    }
}

class Address {
    private String streetName;
    private int houseNumber;
    private int flatNumber;
    private String postcode;
    private String city;
    private String country;

    public Address (
        String streetName,
        int houseNumber,
        int flatNumber,
        String postcode,
        String city,
        String country
    ) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String toString() {
        return this.streetName + " " + this.houseNumber + "/" + this.flatNumber + ", \n"+
        this.postcode+", "+this.city+", "+this.country;
    }
}