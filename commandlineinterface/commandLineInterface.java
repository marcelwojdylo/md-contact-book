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
        while (!quit) {
            System.out.println(
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░ MD Contact Book ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"
            );
            System.out.print(">");
            command = scanner.next();
            switch (command) {
                case ("quit"):
                    quit();
                    break;
                case ("printhelp"):
                    System.out.println(Instructions.print);
                    break;
                case ("printContactBook"):
                    contactBook.printContactsSortedByLastNameInitial();
                    break;
                default: 
                    errorCommandNotFound();
            }
        }
        scanner.close();
        System.out.println("Program terminated.");
    }

    private static void quit() {
        quit = true;
    }

    private static class Instructions {
        static final String print = 
            "'print' command HELP:\n" +
            "\n" +
            "Prints specified component to console.\n" +
            "\n" +
            "Possible usage:\n" +
            "printContactBook - prints entire contact book,\n" +
            "printContact[1] - prints contact with ID number 1,\n" +
            "printContactBook --s - prints contact book sorted alphabetically by last name initial.";
    }

    private static void errorCommandNotFound() {
        System.out.println("Command not found: " + command);
    }
}


