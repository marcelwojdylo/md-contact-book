package gui;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import system.*;

public class CreateContact extends JFrame {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static Contact contact;

    CreateContact () {
        setBackground(Color.BLACK);
        setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        setUndecorated(false);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setComponents();
        setVisible(true);
    }

    private void setComponents() {

    }

    private class FirstNameInput extends JTextField {

    }

    private class FirstNameLabel extends JLabel {

    }

    private class SecondNameInput extends JTextField {
        
    }

    private class SecondNameLabel extends JLabel {
        
    }

    private class DateOfBirthInput extends JTextField {

    }

    private class DateOfBirthLabel extends JLabel {
        
    }

    private class AddressStreetInput extends JTextField {

    }

    private class AddressStreetLabel extends JLabel {
        
    }

    private class AddressFlatInput extends JTextField {

    }
    
    private class AddressFlatLabel extends JLabel {
        
    }

    private class AddressPostcodeInput extends JTextField {
        
    }

    private class AddressPostcodeLabel extends JLabel {
        
    }

    private class AddressCityInput extends JTextField {
        
    }

    private class AddressCityLabel extends JLabel {
        
    }

    private class AddressCountryInput extends JTextField {
        
    }

    private class PhoneNumberInput extends JTextField {
        
    }

    private class EmailInput extends JTextField {
        
    }

    private class CreateContactButton extends JButton implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            
        }
    }
}