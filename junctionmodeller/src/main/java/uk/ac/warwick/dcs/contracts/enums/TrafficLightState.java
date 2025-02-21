package uk.ac.warwick.dcs.contracts.enums;

/**
 * Enum used to specify the current state of a <code>TrafficLight</code>
 * object.
 * NOTE: might abstract to <code>RED</code> and <code>GREEN</code>
 * only, as it simplifies the model.
 */
public enum TrafficLightState {
    RED,
    AMBER,
    GREEN,
    REDAMBER // might be useful
}
