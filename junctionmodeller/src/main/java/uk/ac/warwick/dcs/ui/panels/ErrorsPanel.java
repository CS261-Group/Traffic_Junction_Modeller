package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.util.WrappingLabel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ErrorsPanel extends CustomPanel {
    /**
     * Text to be displayed if a submitted configuration has no errors.
     */
    private static final String NO_ERRORS_TEXT = "No errors.";

    private List<WrappingLabel> errorLbls;

    public ErrorsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        errorLbls = new LinkedList<>();
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Configuration Errors"));

        // set default error message
        assert errorLbls.isEmpty(); // this should be run with an empty errorLbls list
        WrappingLabel noErrorsLbl = new WrappingLabel(NO_ERRORS_TEXT);
        noErrorsLbl.setFont(labelFont);
        errorLbls.add(noErrorsLbl); // add default message

        // update UI as required
        updateErrors();
    }

    private void updateErrors() {
        for (WrappingLabel lbl : errorLbls) {
            add(lbl);
        }
        updateUI();
    }

    public void setErrors(List<String> newErrors) {
        // remove old errors
        for (WrappingLabel errorLbl : errorLbls) {
            remove(errorLbl);
        }
        errorLbls = new LinkedList<>();

        // if there are no errors, we set the default message
        // otherwise, we display the errors
        if (newErrors.isEmpty()) { // no errors
            WrappingLabel noErrorsLbl = new WrappingLabel(NO_ERRORS_TEXT); // \n for clarity in UI
            noErrorsLbl.setFont(labelFont);
            errorLbls.add(noErrorsLbl);
        } else { // errors present
            // map to new error labels
            errorLbls = newErrors.stream().map(err -> {
                WrappingLabel errorLbl = new WrappingLabel(err + "\n"); // \n for clarity in UI
                errorLbl.setFont(labelFont);
                errorLbl.setForeground(Color.RED); // red to visually symbolise failure
                return errorLbl;
            }).toList();
        }

        updateErrors();
    }
}
