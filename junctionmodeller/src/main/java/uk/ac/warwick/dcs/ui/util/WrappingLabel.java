package uk.ac.warwick.dcs.ui.util;

import javax.swing.*;

public class WrappingLabel extends JTextArea {
    public WrappingLabel(String text) {
        super(text);
        setWrapStyleWord(true);
        setLineWrap(true);
        setOpaque(false); // Make it look like a label
        setEditable(false);
        setFocusable(false);
    }
}
