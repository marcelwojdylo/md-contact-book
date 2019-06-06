import system.*;
import commandlineinterface.*;

import java.util.Scanner;


public class ContactBookApp {
    public static void main(String[] arg) {
        System.out.println("\n");
        ContactBook contactBook = new ContactBook();
        // contactBook.printContactBook();
        // contactBook.printContactsSortedByLastNameInitial();
        // contactBook.setLastName(contactBook.getContacts()[4], "Gundarkowski");
        // contactBook.printContactsSortedByLastNameInitial();
        contactBook.printContact(6);
        int[] date = {1, 2, 3};
        contactBook.setDateOfBirth(contactBook.getContacts()[6], date);
        contactBook.printContact(6);
        CommandLineInterface CLI = new CommandLineInterface();
        


    }
}




























































