package ui;

import javax.swing.*;

public class MainForm extends JFrame {
    public MainForm() {
        // set up the UI elements
        setUp();
    }

    /**
     * Set up UI elements for the form.
     */
    private void setUp() {
        // adapted from: https://www.geeksforgeeks.org/introduction-to-java-swing/
        // Creating instance of JButton
        JButton button = new JButton("GFG WebSite Click");
        button.setBounds(150, 200, 220, 50);

        // adding button in JFrame
        this.add(button);

        // 400 width and 500 height
        this.setSize(500, 600);

        // using no layout managers
        this.setLayout(null);
    }
}
