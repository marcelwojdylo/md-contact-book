package commandlineinterface;
import java.util.Scanner;
import java.lang.Thread;
import system.*;

public class CommandLineInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean quit = false;
    private static String command = "";
    private static ContactBook contactBook = new ContactBook();
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        printBanner();

        while (!quit) {
            System.out.print(">");
            command = scanner.nextLine();
            pause(500);
            switch (command) {
                case (Commands.PRINT_CONTACT): //problem
                    printContactByID();
                    break;
                case (Commands.NEW_CONTACT): //problem
                    createNewContact();
                    break;
                case (Commands.PRINT_HELP):
                    System.out.println(Instructions.print);
                    break;
                case (Commands.PRINT_CONTACT_BOOK):
                    contactBook.printContactsSortedByLastNameInitial();
                    break;
                case (Commands.PRINT_CONTACT_BOOK_BY_ID):
                    contactBook.printContactBook();
                    break;
                case (Commands.QUIT):
                    quit();
                    break;
                default: 
                    errorCommandNotFound();
                    break;
            }
        }
        scanner.close();
        printExitMessage();
    }

    private static final class Commands {
        static final String PRINT_CONTACT = "print contact";
        static final String PRINT_HELP = "print help";
        static final String PRINT_CONTACT_BOOK = "print contactbook";
        static final String PRINT_CONTACT_BOOK_BY_ID = "print contactbook byID";
        static final String QUIT = "quit";
        static final String NEW_CONTACT = "new contact";
    }



    private static void quit() {
        quit = true;
    }

    private static class Instructions {
        static final String print = 
            Color.makeYellow("'print' command HELP:\n") +
            "\n" +
            "Prints specified component to console.\n" +
            "\n" +
            "Possible usage:\n" +
            "print contactbook - prints entire contact book sorted alphabetically by last name initial,\n" +
            "print contactbook byID - prints entire contact book sorted by ID number,\n" +
            "print contact 1 - prints contact with ID number 1,\n";
    }



    private static void printContactByID() {
        System.out.println(Color.makeWhite("Kindly input contact ID:"));
        int contactID = scanner.nextInt();
        if (ContactBook.isPresent(contactID)) {
            contactBook.printContact(contactID);
        } else {
            errorContactIDNotFound();
        }
    }

    private static void printBanner() {
        System.out.println(Color.makeWhite("░░░░░░░░░░░░░░░░░░░░░░░░░░░░ MD Contact Book ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"));
    }

    private static void printExitMessage(){
        System.out.println(Color.makeYellow("Program terminated."));
    }

    private static void createNewContact() {
        String lastName;
        String firstName;
        String phoneNumber;
        int[] dateOfBirth = new int[3];
        String addressStreet;
        String addressHouse;
        String addressFlat;
        String addressPostcode;
        String addressCity;
        String addressCountry;
        String email;


        pause(1000);
        System.out.println(Color.makeYellow("░░░ Contact Creation Wizard ░░░"));
        System.out.println(Color.makeWhite("Press 'return' to skip a field"));
        System.out.println(Color.makeBlue("Enter last name:"));
        lastName = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter first name:"));
        firstName = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter phone number:"));
        phoneNumber = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter day of birth:"));
        dateOfBirth[0] = scanner.nextInt();
        pause(500);
        System.out.println(Color.makeBlue("Enter month of birth:"));
        dateOfBirth[1] = scanner.nextInt();
        pause(500);
        System.out.println(Color.makeBlue("Enter year of birth:"));
        dateOfBirth[2] = scanner.nextInt();
        pause(500);
        System.out.println(Color.makeBlue("Enter address street:"));
        addressStreet = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter address house:"));
        addressHouse = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter address flat:"));
        addressFlat = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter address postcode:"));
        addressPostcode = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter address city:"));
        addressCity = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter address country:"));
        addressCountry = scanner.next();
        pause(500);
        System.out.println(Color.makeBlue("Enter e-mail:"));
        email = scanner.next();
        pause(500);

        Contact contact = new Contact.Builder()
        .lastName(lastName)
        .firstName(firstName)
        .phoneNumber(phoneNumber)
        .dateOfBirth(dateOfBirth[0], dateOfBirth[1], dateOfBirth[2])
        .addressStreet(addressStreet)
        .addressHouse(addressHouse)
        .addressFlat(addressFlat)
        .addressPostcode(addressPostcode)
        .addressCity(addressCity)
        .addressCountry(addressCountry)
        .email(email)
        .build();

        contactBook.addContact(contact);
        if (ContactBook.isPresent(contact.getContactID())) {
            System.out.println(Color.makeYellow("Contact with ID "+contact.getContactID()+" created successfully."));
        } else {
            System.out.println(Color.makeRed("Something went wrong, the contact was not created."));
        }
    }


    private static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(Color.makeRed("Pause interrupted!"));
        }
    }



    private static void errorCommandNotFound() {
        System.out.println(Color.makeRed("Command not found: " + command));
    }

    private static void errorContactIDNotFound() {
        System.out.println(Color.makeRed("Contact ID was not found."));
    }
}


