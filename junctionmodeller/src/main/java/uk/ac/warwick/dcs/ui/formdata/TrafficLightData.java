package uk.ac.warwick.dcs.ui.formdata;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;

public record TrafficLightData(TrafficLightType type, int numGroups) {
}
