package uk.ac.warwick.dcs.ui.formdata;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import java.util.List;

public record DirectionData(Direction direction, FlowData flowData, List<AvailableDirections> availableDirections, boolean busLane, boolean pedestrianCrossing) {
}
