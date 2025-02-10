package uk.ac.warwick.dcs.ui.formdata;

import java.util.List;

public record DirectionData(List<Integer> arrivalFlows, List<AvailableDirections> availableDirections, List<Integer> departureFlows) {
}
