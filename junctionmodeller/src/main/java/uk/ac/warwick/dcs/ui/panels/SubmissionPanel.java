package uk.ac.warwick.dcs.ui.panels;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.Font;

public class SubmissionPanel extends CustomPanel implements IReadablePanel<Boolean> {
    private JCheckBox showVisualisationCheckbox;

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
        add(showVisualisationCheckbox);
    }

    @Override
    public Boolean getValue() {
        return showVisualisationCheckbox.isSelected();
    }
}
