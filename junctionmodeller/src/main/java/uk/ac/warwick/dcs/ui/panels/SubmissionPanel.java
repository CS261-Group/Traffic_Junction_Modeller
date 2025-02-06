package uk.ac.warwick.dcs.ui.panels;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;

public class SubmissionPanel extends CustomPanel {
    public SubmissionPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        // grid layout
        setLayout(new GridLayout(0, 2));
        // add margins
        setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Show visualisation button
        JCheckBox showValues = new JCheckBox("Show visualisation");
        // Submission button
        JButton submitButton = new JButton("Confirm and Analyse");

        add(showValues);
        add(submitButton);
    }
}
