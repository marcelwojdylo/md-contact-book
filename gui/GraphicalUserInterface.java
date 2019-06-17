package gui;

import javax.swing.*;

public class GraphicalUserInterface {

    private static JFrame mainFrame;

    public static void run() {
        initialiseMainFrame();
    }

    private static void initialiseMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
    }

    

}