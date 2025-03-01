package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ErrorsPanel extends CustomPanel {
    private List<JLabel> errorLbls;

    public ErrorsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        errorLbls = new LinkedList<>();
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(BorderFactory.createTitledBorder("Configuration Errors"));
    }

    private void updateErrors() {
        for (JLabel lbl : errorLbls) {
            add(lbl);
        }
        updateUI();
    }

    public void setErrors(List<String> newErrors) {
        // remove old errors
        for (JLabel errorLbl : errorLbls) {
            remove(errorLbl);
        }
        errorLbls = new LinkedList<>();

        // map to new error labels
        errorLbls = newErrors.stream().map(err -> {
           JLabel errorLbl = new JLabel(err);
           errorLbl.setForeground(Color.RED);
           return errorLbl;
        }).toList();

        updateErrors();
    }
}
