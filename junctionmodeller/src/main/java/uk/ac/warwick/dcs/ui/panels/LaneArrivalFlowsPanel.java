package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.Constants;
import uk.ac.warwick.dcs.ui.interfaces.ILaneChangedSubscriber;
import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class LaneArrivalFlowsPanel extends CustomPanel implements ILaneChangedSubscriber, IReadablePanel<List<Integer>> {
    private final List<LaneArrivalFlowsSettingsPanel> lanes;

    public LaneArrivalFlowsPanel(Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        lanes = new ArrayList<>(Constants.MAX_LANES);
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel laneArrivalHeading = new JLabel("Lane arrival flows (in vehicles/hour)");
        laneArrivalHeading.setFont(headingFont);
        add(laneArrivalHeading);
    }

    @Override
    public void notify(int oldLanes, int newLanes, Direction direction) {
        if (oldLanes < newLanes) {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneArrivalFlowsSettingsPanel setting = new LaneArrivalFlowsSettingsPanel(headingFont, labelFont, lanes.size() + 1);
                add(setting);
                lanes.add(setting);
            }
        } else {
            while (lanes.size() != newLanes) {
                // create lanes
                LaneArrivalFlowsSettingsPanel removedSetting = lanes.remove(lanes.size() - 1); // remove last value
                remove(removedSetting);
            }
        }

        // update the UI to incorporate
        updateUI();
    }

    @Override
    public List<Integer> getValue() {
        return lanes.stream().map(LaneArrivalFlowsSettingsPanel::getValue).toList();
    }
}
