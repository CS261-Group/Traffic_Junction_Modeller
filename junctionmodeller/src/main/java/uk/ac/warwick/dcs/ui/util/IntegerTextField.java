package uk.ac.warwick.dcs.ui.util;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * JTextField child which only allows integers to be entered.
 */
public class IntegerTextField extends JTextField {
    private final int defaultValue;
    private static final int MAX_DIGIT_COLUMNS = 7;

    public IntegerTextField(int defaultValue) {
        super(MAX_DIGIT_COLUMNS);
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

    /**
     *
     * @return The integer entered in the text field.
     */
    public int getIntegerValue() {
    
        String text = getText().trim();  
        if (text.isEmpty()) {
            setText("0");  
            return 0;     
        }
    
        try {
            return Integer.parseInt(text);  
        } catch (NumberFormatException ex) {
            setText("0");  
            return 0;     
        }
    }
}
