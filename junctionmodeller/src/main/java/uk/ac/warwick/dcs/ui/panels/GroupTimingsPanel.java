package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.formdata.GroupTimings;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Panel used to determine whether we optimise signal timings and
 * setting signal timings if the user chooses not to optimise the
 * settings.
 */
public class GroupTimingsPanel extends CustomPanel implements IReadablePanel<GroupTimings> {
    private final List<GroupTimingSettingPanel> timingFields;
    private int numGroups;
    private JCheckBox optimiseCheckbox;

    public GroupTimingsPanel(Font headingFont, Font labelFont, int numGroups) {
        super(headingFont, labelFont);
        this.timingFields = new LinkedList<>();
        this.numGroups = numGroups;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel groupTimingsHeading = new JLabel("Group Light Timings");
        groupTimingsHeading.setFont(headingFont);
        add(groupTimingsHeading);

        optimiseCheckbox = new JCheckBox("Optimise signal timings");
        optimiseCheckbox.addItemListener(e -> {
            // if we check the checkbox, we don't want to specify signal timings
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setTimingsVisibility(false);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                setTimingsVisibility(true);
            }
        });
        add(optimiseCheckbox);


        JTextArea desc = new JTextArea("All groups have some (maximum) active time in seconds");
        desc.setWrapStyleWord(true);
        desc.setLineWrap(true);
        desc.setOpaque(false); // Make it look like a label
        desc.setEditable(false);
        desc.setFocusable(false);
        desc.setFont(labelFont);
        add(desc);

        // initially create correct number of groups
        assert timingFields.isEmpty(); // i.e., size() == 0
        changeNumGroups(0, this.numGroups);

        // this should trigger the visibility action
        // i.e., initially make the group timings invisible
        optimiseCheckbox.setSelected(true);
    }

    public void changeNumGroups(int oldNumGroups, int newNumGroups) {
        numGroups = newNumGroups;
        if (oldNumGroups < newNumGroups) {
            while (timingFields.size() != newNumGroups) {
                GroupTimingSettingPanel setting = new GroupTimingSettingPanel(headingFont, labelFont, timingFields.size() + 1);
                add(setting);
                timingFields.add(setting);
            }
        } else {
            while (timingFields.size() != newNumGroups) {
                GroupTimingSettingPanel removedSetting = timingFields.remove(timingFields.size() - 1);
                remove(removedSetting);
            }
        }
    }

    private void setTimingsVisibility(boolean visible) {
        for (GroupTimingSettingPanel setting : timingFields) {
            setting.setVisible(visible);
        }
    }

    @Override
    public GroupTimings getValue() {
        return new GroupTimings(
                optimiseCheckbox.isSelected(),
                timingFields.stream().map(GroupTimingSettingPanel::getValue).toList()
        );
    }
}
