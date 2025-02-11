package uk.ac.warwick.dcs.ui.formdata;

import uk.ac.warwick.dcs.contracts.enums.Direction;

import java.util.List;

public record LaneGroups(List<LaneGroup> laneGroups, Direction direction) {
}
