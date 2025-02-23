
package uk.ac.warwick.dcs.ui.panels;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

/**
 * Panel used to load a file from the system via the file path.
 * @author eyhli
 */
public class LoadingPanel extends CustomPanel implements IReadablePanel<String>{
    private JTextField filePathField;
    private JButton submitButton;

    public LoadingPanel(Font headingFont, Font labelFont){
        super(headingFont,labelFont);
        setUp();
    }
    @Override
    protected void setUp(){
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        filePathField = new JTextField();
        filePathField.setFont(labelFont);
        filePathField.setToolTipText("Enter file path containing input configuration");
        submitButton = new JButton("Load Configuration");
        submitButton.setFont(labelFont);

        add(filePathField);
        add(submitButton);
    }
    
    public void setSubmissionAction(ActionListener l){
        submitButton.addActionListener(l);
    }

    @Override
    public String getValue(){
        return filePathField.getText().trim();
    }
}
