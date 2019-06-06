package commandlineinterface;
import java.util.Scanner;
import system.*;

public class CommandLineInterface {
    private static Scanner scanner;
    private static boolean quit = false;
    private static String command = "";
    private static ContactBook contactBook = new ContactBook();
    public static void main(String[] args) {
        initializeScanner();
        run();
    }

    private static void initializeScanner() {
        scanner = new Scanner(System.in);
    }

    private static void run() {
        printBanner();

        while (!quit) {
            System.out.print(">");
            command = scanner.nextLine();
            switch (command) {
                case (Commands.PRINT_CONTACT):
                    printIDQuery();
                    int contactID = scanner.nextInt();
                    if (ContactBook.isPresent(contactID)) {
                        contactBook.printContact(contactID);
                        break;
                    } else {
                        errorContactIDNotFound();
                        break;
                    }
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

    private static void printIDQuery() {
        System.out.println(Color.makeWhite("Kindly input contact ID:"));
    }

    private static void printBanner() {
        System.out.println(Color.makeWhite("░░░░░░░░░░░░░░░░░░░░░░░░░░░░ MD Contact Book ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"));
    }

    private static void printExitMessage(){
        System.out.println(Color.makeYellow("Program terminated."));
    }

    private static void errorCommandNotFound() {
        System.out.println(Color.makeRed("Command not found: " + command));
    }

    private static void errorContactIDNotFound() {
        System.out.println(Color.makeRed("Contact ID was not found."));
    }
}


