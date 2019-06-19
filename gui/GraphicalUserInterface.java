package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import system.*;

public class GraphicalUserInterface {

    private static JFrame mainFrame;
    private static ContactBook contactBook = new ContactBook();

    public static void run() {
        mainFrame = new JFrame();
        mainFrame.setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        mainFrame.setLayout(null);

        JButton button = new JButton("There are " + contactBook.getNumberOfContacts() + " contacts.");
        button.setBounds(Dimensions.WINDOW_WIDTH/2, Dimensions.WINDOW_HEIGHT/2, 300, 100);
        JTextField textField = new JTextField();
        textField.setBounds(Dimensions.WINDOW_WIDTH/2, Dimensions.WINDOW_HEIGHT/2-200, 300, 100);
        JLabel label = new JLabel("What?");
        label.setBounds(100, 200, 200, 200);

        JOptionPane.showMessageDialog(mainFrame, "ASDASDASDASD!");


        ActionListener actionListener = new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setBounds(100, 100, 300, 100);
                textField.setText("Alalallala");
                label.setText("Nuthin");
            }
        };
        button.addActionListener(actionListener);

        mainFrame.add(button);
        mainFrame.add(label);
        mainFrame.add(textField);
        mainFrame.setVisible(true);
    }
}