package uk.ac.warwick.dcs.ui.panels;

import javax.swing.JPanel;
import java.awt.Font;

/**
 * Abstraction over the <code>JPanel</code> class to require a separate
 * setup function and pass down the label and heading fonts.
 */
abstract class CustomPanel extends JPanel {
    /**
     * Font for labels and other non-heading text.
     */
    protected final Font labelFont;

    /**
     * Font for headings and section separating text.
     */
    protected final Font headingFont;

    public CustomPanel(Font headingFont, Font labelFont) {
        this.headingFont = headingFont;
        this.labelFont = labelFont;
    }

    /**
     * Set up UI elements for the form.
     */
    protected abstract void setUp();
}
