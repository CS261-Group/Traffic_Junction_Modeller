package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.util.WrappingLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryPanel extends CustomPanel{
    final int TABLE_SIZE = 5;

    DefaultTableModel defaultTableModel;
    JTable table;
    JScrollPane scrollPane;

    public HistoryPanel(Font headingFont, Font labelFont){
        super(headingFont, labelFont);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Metrics History"));

        String[] headings = {"Config Name", "Average Wait", "Average Queue", "Maximum Queue"};

        // Create table model (with 0 initial rows)
        defaultTableModel = new DefaultTableModel(headings, 0);
        table = new JTable(defaultTableModel);
        // Wrap the table inside a JScrollPane to show column headings
        table.setEnabled(false);

        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        add(scrollPane);
        addRow();
    }

    public void addRow(){
        defaultTableModel.addRow(new Object[]{"name test", 12.1, 4, 20});
    }
}
