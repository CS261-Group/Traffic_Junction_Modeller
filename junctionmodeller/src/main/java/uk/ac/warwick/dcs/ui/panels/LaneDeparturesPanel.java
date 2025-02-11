package uk.ac.warwick.dcs.ui.panels;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class LaneDeparturesPanel extends CustomPanel implements ILaneChangedSubscriber, IReadablePanel<List<Integer>> {
    private final List<LaneDepartureSettingsPanel> lanes;

    public LaneDeparturesPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        lanes = new ArrayList<>(Constants.MAX_LANES);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel depFlowHeading = new JLabel("Lane departure flows (in vehicles/hour)");
        depFlowHeading.setFont(headingFont);
        depFlowHeading.setAlignmentX(LEFT_ALIGNMENT);
        add(depFlowHeading);
    }

    @Override
    public void notify(int oldLanes, int newLanes, Direction direction) {
        if (oldLanes < newLanes) {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneDepartureSettingsPanel setting = new LaneDepartureSettingsPanel(headingFont, labelFont, lanes.size() + 1);
                setting.setAlignmentX(LEFT_ALIGNMENT);
                add(setting);
                lanes.add(setting);
            }
        } else {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneDepartureSettingsPanel removedSetting = lanes.remove(lanes.size() - 1); // remove last value
                remove(removedSetting);
            }
        }

        // update the UI to incorporate
        updateUI();
    }

    @Override
    public List<Integer> getValue() {
        return lanes.stream().map(LaneDepartureSettingsPanel::getValue).toList();
    }
}
