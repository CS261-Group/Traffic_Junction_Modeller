package uk.ac.warwick.dcs.ui.util;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

public class IntegerTextField extends JTextField {
    private final int defaultValue;

    public IntegerTextField(int columns, int defaultValue) {
        super(columns);
        assert defaultValue >= 0;

        this.defaultValue = defaultValue;

        // Restrict input to only digits
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore the event if it's not a digit
                }
            }
        });

        // default value
        setText(String.valueOf(defaultValue));
    }

    public int getIntegerValue() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }
}
