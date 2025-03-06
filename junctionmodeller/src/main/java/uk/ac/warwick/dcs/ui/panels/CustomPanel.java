package uk.ac.warwick.dcs.ui.panels;

import java.awt.Font;

import javax.swing.JPanel;

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
        setAlignmentX(LEFT_ALIGNMENT);
    }

    /**
     * Set up UI elements for the form.
     */
    protected abstract void setUp();
}
