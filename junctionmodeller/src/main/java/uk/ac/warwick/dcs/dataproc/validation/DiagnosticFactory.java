package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;

public class DiagnosticFactory implements IDiagnosticFactory {
    @Override
    public String createFieldErrorMessage(String field, String error) {
        return field + ": " + error;
    }

    @Override
    public String createInvalidFlowSumMessage(Direction direction, int incomingFlow, int[] outgoingFlows) {
        return "Sum of outgoing flows does not equal sum of incoming flows in direction: " + direction.toString();
    }

    @Override
    public String createInvalidPermittedDirectionsMessage(Direction direction, int laneNum) {
        return "The order of permitted lanes is invalid for lane #" + laneNum + " for direction: " + direction.toString();
    }

    @Override
    public String createInvalidNumLanesMessage(Direction direction, String type) {
        return "Invalid number of " + type + " lanes in direction: " + direction.toString();
    }

    @Override
    public String createOutgoingFlowErrorMessage(Direction direction, boolean[] validDirections) {
        assert validDirections.length == 4; // sanity check: this is just how it should be used

        // terrible code to get the directions in to the error message
        String directions = "";
        if (!validDirections[Direction.NORTH.ordinal()]) {
            directions += "N";
        }
        if (!validDirections[Direction.EAST.ordinal()]) {
            directions += "E";
        }
        if (!validDirections[Direction.SOUTH.ordinal()]) {
            directions += "S";
        }
        if (!validDirections[Direction.WEST.ordinal()]) {
            directions += "W";
        }

        return "No lane permits direction(s) {" + directions + "} from direction: " + direction.toString();
    }

    @Override
    public String createInvalidGroupNumMessage(int groupNum) {
        return "Invalid group number: " + groupNum;
    }
}
