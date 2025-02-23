package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * Panel containing the submit button and a checkbox for whether the user
 * wants to show the visualisation.
 */
public class SubmissionPanel extends CustomPanel implements IReadablePanel<Boolean> {
    private JCheckBox showVisualisationCheckbox;
    private JButton submitButton;

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
        showVisualisationCheckbox = new JCheckBox("Show visualisation");
        showVisualisationCheckbox.setFont(labelFont);

        submitButton = new JButton("Confirm and Analyse");
        submitButton.setFont(labelFont);

        add(showVisualisationCheckbox);
        add(submitButton);
    }

    /**
     * Used to set the action to occur when the submit button gets clicked.
     * @param l The action listener for what to do when the submit button gets clicked.
     */
    public void setSubmissionAction(ActionListener l) {
        submitButton.addActionListener(l);
    }

    @Override
    public Boolean getValue() {
        return showVisualisationCheckbox.isSelected();
    }
}
