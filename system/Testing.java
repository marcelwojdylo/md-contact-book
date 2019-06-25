package system;

public class Testing {

    public static void addGenericContacts(ContactBook contactBook, int limitOfContacts) {
        int i = 0;
        Contact[] genericContactsArray = createGenericContacts();
        while (i < limitOfContacts) {
            Contact contact = genericContactsArray[i];
            contactBook.addContact(contact);
            i++;
        }
    }
    
    private static Contact[] createGenericContacts() {
        int i = 0;
        Contact[] contactArray = new Contact[10];
        while (i <= 9) {
            contactArray[i] = new Contact.Builder()
                .lastName(genericSurnames()[i])
                .firstName("Generyk")
                .phoneNumber("666-666-666")
                .dateOfBirth("1990-13-12")
                .addressLine1("Helclów 21/1")
                .addressLine2("02-927 Warszawa, Polska")
                .email("generyk@generyk.gn")
                .build();
            i++;    
        }
        return contactArray;
    }
        
    private static String[] genericSurnames() {
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