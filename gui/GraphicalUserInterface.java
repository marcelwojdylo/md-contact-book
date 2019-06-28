package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import system.*;

public class GraphicalUserInterface {

    private static ContactBook contactBook = new ContactBook();
    public static ContactBook getContactBook() {return contactBook;}

    public static void run() {
        new MainFrame();
    }


}