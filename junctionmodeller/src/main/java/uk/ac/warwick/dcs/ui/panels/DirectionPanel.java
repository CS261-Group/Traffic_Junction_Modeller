package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.contracts.enums.Direction;
import uk.ac.warwick.dcs.ui.ILaneChangedSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DirectionPanel extends CustomPanel {
    private final Direction direction;
    private final int maxLanes;

    public DirectionPanel(Direction direction, int maxLanes, Font headingFont, Font labelFont) {
        super(headingFont, labelFont);
        this.direction = direction;
        this.maxLanes = maxLanes;
        setUp();
    }

    @Override
    protected void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(BorderFactory.createTitledBorder(direction.toString()));

        // custom panels required for each section
        LaneArrivalFlowsPanel laneArrivalFlows = new LaneArrivalFlowsPanel(headingFont, labelFont);
        LaneDirectionsPanel laneDirections = new LaneDirectionsPanel(headingFont, labelFont);
        LaneDeparturesPanel laneDepartures = new LaneDeparturesPanel(headingFont, labelFont);

        // although this is first, it needs to be instantiated last so all the other
        // objects (which are subscribers) can be registered
        List<ILaneChangedSubscriber> subscribers = new LinkedList<>();
        subscribers.add(laneArrivalFlows);
        subscribers.add(laneDirections);
        subscribers.add(laneDepartures);

        // first panel defines alignment for whole column, for some reason
        JPanel directionInputs = new DirectionInputsPanel(maxLanes, subscribers, headingFont, labelFont);
        directionInputs.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(directionInputs);
        add(laneArrivalFlows);
        add(laneDirections);
        add(laneDepartures);
    }
}
