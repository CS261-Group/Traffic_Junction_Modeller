package uk.ac.warwick.dcs.ui.formdata;

public record ConfigurationData(String configName, DirectionData[] directionData, TrafficLightData trafficLightData, boolean showVisualisation) {
}
