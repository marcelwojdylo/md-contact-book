package commandlineinterface;
import java.util.Scanner;
import java.lang.Thread;
import java.time.*;
import system.*;

public class CommandLineInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean quit = false;
    private static String command;
    private static ContactBook contactBook = new ContactBook();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        printBanner();
        while (!quit) {
            System.out.print(">");
            command = scanner.nextLine();
            switch (command) {
                case (Commands.PRINT_CONTACT):
                    printContactByID();
                    break;
                case (Commands.NEW_CONTACT):
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
        int contactID = Integer.parseInt(scanner.nextLine());
        try {
            contactBook.printContact(contactID);
        } catch (NullPointerException e) {
            errorContactIDNotFound();
        }
    }

    private static void printBanner() {
        System.out.println(Color.makeWhite("░░░░░░░░░░ MD Contact Book ░░░░░░░░░░"));
    }

    private static void printExitMessage(){
        System.out.println(Color.makeYellow("Program terminated."));
    }

    private static void createNewContact() {
        String lastName;
        String firstName;
        String phoneNumber;
        String dateOfBirth;
        String addressLine1;
        String addressLine2;
        String email;



        System.out.println(Color.makeYellow("░░░ Contact Creation Wizard ░░░"));

        System.out.println(Color.makeWhite("Press 'return' to skip a field"));

        System.out.println(Color.makeBlue("Enter last name:"));
        lastName = inputLetters();

        System.out.println(Color.makeBlue("Enter first name:"));
        firstName = inputLetters();
        
        System.out.println(Color.makeBlue("Enter phone number:"));
        phoneNumber = inputNumbers();
        
        System.out.println(Color.makeBlue("Enter date of birth (yyyy-mm-dd):"));
        dateOfBirth = inputDateOfBirth();
        
        System.out.println(Color.makeBlue("Enter address line 1:"));
        addressLine1 = inputLetters();

        System.out.println(Color.makeBlue("Enter address line 2:"));
        addressLine2 = inputLetters();
        
        System.out.println(Color.makeBlue("Enter e-mail:"));
        email = inputEmail();

        Contact contact = new Contact.Builder()
        .lastName(lastName)
        .firstName(firstName)
        .phoneNumber(phoneNumber)
        .dateOfBirth(dateOfBirth)
        .addressLine1(addressLine1)
        .addressLine2(addressLine2)
        .email(email)
        .build();

        contactBook.addContact(contact);
        System.out.println(Color.makeYellow("Contact with ID "+contact.getContactID()+" created successfully."));
    }

    private static String inputLetters() {
        String string = scanner.nextLine();
        while (!containsLettersOnly(string)) {
            System.out.println(Color.makeRed("Please use letters only."));
            string = scanner.nextLine(); 
        }
        return string;
    }

    private static String inputNumbers() {
        String string = scanner.nextLine();
        while (!containsNumbersOnly(string)) {
            System.out.println(Color.makeRed("Please use numbers only."));
            string = scanner.nextLine();
        }
        return string;
    }

    private static String inputEmail() {
        String string = scanner.nextLine();
        while (!string.matches(Config.Constants.EMAIL_CHECK_REGEX) && !string.isEmpty()) {
            System.out.println(Color.makeRed("Please enter a valid e-mail address."));
            string = scanner.nextLine();
        }
        return string;
    }

    private static String inputPostCode() {
        String string = scanner.nextLine();
        while (!string.matches(Config.Constants.POSTCODE_CHECK_REGEX) && !string.isEmpty()) {
            System.out.println(Color.makeRed("Please enter post code in 00-000 format."));
            string = scanner.nextLine();
        }
        return string;
    }

    private static String inputDateOfBirth() {
        boolean isValid = false;
        String dateOfBirth = "";

        while (!isValid) {
            dateOfBirth = scanner.nextLine();
            if (dateOfBirth.isEmpty()) {
                isValid = true;
                continue;
            }

            if (!dateOfBirth.matches(Config.Constants.DATE_OF_BIRTH_CHECK_REGEX)) {
                System.out.println(Color.makeRed("Please enter date of birth in yyyy-mm-dd format."));
                continue;
            }
            
            int year = Integer.parseInt(dateOfBirth.split("-")[0]);
            int month = Integer.parseInt(dateOfBirth.split("-")[1]);
            int day = Integer.parseInt(dateOfBirth.split("-")[2]);
            LocalDate tempDate;
            try {
                tempDate = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println(Color.makeRed("Please enter valid date."));
                continue;
            }
        

            if (tempDate.isAfter(LocalDate.now())) {
                System.out.println(Color.makeRed("Please enter a past date."));
                continue;
            } else {
                isValid = true;
            }
        }
        return dateOfBirth;
    }

    private static boolean containsNumbersOnly(String string) {
        boolean result = true;
        for (int i = 0; i < string.length(); i++) {
            if (!isDigit(string.charAt(i))) {
                result = false;
            }
        }
        return result;
    }

    private static boolean containsLettersOnly(String string) {
        boolean result = true;
        for (int i = 0; i < string.length(); i++) {
            if (!isLetter(string.charAt(i))) {
                result = false;
            }
        }
        return result;
    }

    private static boolean isDigit(char character) {
        for (int i = 0; i < Config.Constants.DIGITS.length(); i++) {
            if (character == Config.Constants.DIGITS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetter(char character) {
        for (int i = 0; i < Config.Constants.LETTERS.length(); i++) {
            if (character == Config.Constants.LETTERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }



    private static void errorCommandNotFound() {
        System.out.println(Color.makeRed("Command not found: " + command));
    }

    private static void errorContactIDNotFound() {
        System.out.println(Color.makeRed("Contact ID was not found."));
    }
}


