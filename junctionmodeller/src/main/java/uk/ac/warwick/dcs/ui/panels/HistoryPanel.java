package uk.ac.warwick.dcs.ui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
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

        defaultTableModel = new DefaultTableModel(headings, 0);
        table = new JTable(defaultTableModel);
        table.setEnabled(false);
        table.getTableHeader().setFont(headingFont);
        table.setFont(labelFont);

        TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);

        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Use the default renderer for most cases
                Component component = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Ensure the component is a JLabel for manipulation
                if (component instanceof JLabel label) {
                    // Change font for a specific row (e.g., row 1)
                    if (row == 0) {
                        label.setFont(headingFont); // Set different font for row 1
                    } else {
                        label.setFont(labelFont); // Use default font for other rows
                    }
                }

                return component;  // Return the customized component
            }
        });

        // Get the TableColumnModel
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);

        add(table);
        defaultTableModel.addRow(new Object[]{"Configuration", "Average Wait", "Average Queue", "Maximum Queue"});
    }

    public void updateRow(String configName, double averageWaitTime, double averageQueueLength, double maxQueueLength){
        boolean found = false;
        // we iterate backwards (slight expected optimisation, same worst case)
        for (int row = defaultTableModel.getRowCount() - 1; row >= 0; row--) {
            String rowConfigName = (String)defaultTableModel.getValueAt(row, 0);

            // check if this row corresponds to this model
            // NOTE: in the case that a user deletes a model with a certain
            // from their file system, and creates a new model with the same
            // name, that model will be updated as if it were the one which's
            // file got deleted -- effectively bypassing the uniqueness case
            // this edge case will be ignored for the purposes of demonstration
            if (rowConfigName.equals(configName)) {
                defaultTableModel.setValueAt(averageQueueLength, row, 1);
                defaultTableModel.setValueAt(averageQueueLength, row, 2);
                defaultTableModel.setValueAt(maxQueueLength, row, 3);

                // if we have found a match
                found = true;
            }
        }

        // add new row if not previously in table
        if (!found) {
            defaultTableModel.addRow(new Object[]{configName, averageWaitTime, averageQueueLength, maxQueueLength});
        }

        // update UI in any case
        updateUI();
    }
}
