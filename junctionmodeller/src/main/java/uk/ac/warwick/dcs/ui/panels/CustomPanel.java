package uk.ac.warwick.dcs.ui.panels;

import javax.swing.JPanel;
import java.awt.Font;

abstract class CustomPanel extends JPanel {
    protected final Font labelFont;
    protected final Font headingFont;

    public CustomPanel(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    protected abstract void setUp();
}
