package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.util.WrappingLabel;

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

    public void addRow(String configName, double averageWaitTime, double averageQueueLength, double maxQueueLength){
        defaultTableModel.addRow(new Object[]{configName, averageWaitTime, averageQueueLength, maxQueueLength});
        updateUI();
    }
}
