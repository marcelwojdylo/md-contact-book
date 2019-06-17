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

    public static void run() {
        printBanner();
        while (!quit) {
            System.out.print(">");
            command = scanner.nextLine();
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
        int contactID = Integer.parseInt(scanner.nextLine());
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
        LocalDate dateOfBirth;
        String addressStreet;
        String addressHouse;
        String addressFlat;
        String addressPostcode;
        String addressCity;
        String addressCountry;
        String email;


        // pause(1000);

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
        
        System.out.println(Color.makeBlue("Enter address street:"));
        addressStreet = inputLetters();
        
        System.out.println(Color.makeBlue("Enter address house:"));
        addressHouse = inputNumbers();
        
        System.out.println(Color.makeBlue("Enter address flat:"));
        addressFlat = inputNumbers();
        
        System.out.println(Color.makeBlue("Enter address postcode:"));
        addressPostcode = inputPostCode();
        
        System.out.println(Color.makeBlue("Enter address city:"));
        addressCity = inputLetters();
        
        System.out.println(Color.makeBlue("Enter address country:"));
        addressCountry = inputLetters();
        
        System.out.println(Color.makeBlue("Enter e-mail:"));
        email = inputEmail();

        Contact contact = new Contact.Builder()
        .lastName(lastName)
        .firstName(firstName)
        .phoneNumber(phoneNumber)
        .dateOfBirth(dateOfBirth)
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
        while (!string.matches(Constants.EMAIL_CHECK_REGEX)) {
            System.out.println(Color.makeRed("Please enter a valid e-mail address."));
            string = scanner.nextLine();
        }
        return string;
    }

    private static String inputPostCode() {
        String string = scanner.nextLine();
        while (!string.matches(Constants.POSTCODE_CHECK_REGEX)) {
            System.out.println(Color.makeRed("Please enter post code in 00-000 format."));
            string = scanner.nextLine();
        }
        return string;
    }

    private static LocalDate inputDateOfBirth() {
        boolean isValid = false;
        LocalDate dateOfBirth = LocalDate.now();

        while (!isValid) {
            String DateOfBirthInput = scanner.nextLine();
            if (!DateOfBirthInput.matches(Constants.DATE_OF_BIRTH_CHECK_REGEX)) {
                System.out.println(Color.makeRed("Please enter date of birth in yyyy-mm-dd format."));
                continue;
            }
            
            int year = Integer.parseInt(DateOfBirthInput.split("-")[0]);
            int month = Integer.parseInt(DateOfBirthInput.split("-")[1]);
            int day = Integer.parseInt(DateOfBirthInput.split("-")[2]);
            try {
                dateOfBirth = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println(Color.makeRed("Please enter valid date."));
                continue;
            }
        

            if (dateOfBirth.isAfter(LocalDate.now())) {
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
        for (int i = 0; i < Constants.DIGITS.length(); i++) {
            if (character == Constants.DIGITS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetter(char character) {
        for (int i = 0; i < Constants.LETTERS.length(); i++) {
            if (character == Constants.LETTERS.charAt(i)) {
                return true;
            }
        }
        return false;
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


