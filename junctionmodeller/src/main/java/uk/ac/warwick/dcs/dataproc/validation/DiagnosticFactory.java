package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;

class DiagnosticFactory implements IDiagnosticFactory {
    @Override
    public String createFieldErrorMessage(String field, String error) {
        return field + ": " + error;
    }

    @Override
    public String createInvalidFlowSumMessage(Direction direction, int incomingFlow, int[] outgoingFlows) {
        // sanity checks: although there is nothing stopping this condition, it should never be used in this way
        if (outgoingFlows.length != 4) {
            throw new RuntimeException("outgoingFlows parameter should have 4 elements, has " + outgoingFlows.length);
        }
        if (outgoingFlows[direction.ordinal()] != 0) {
            throw new RuntimeException("outgoingFlows parameter should have 0 flow on incoming direction: " +
                    direction + ", has " + outgoingFlows[direction.ordinal()]);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Incoming flow [")
                .append(incomingFlow)
                .append("] does not equal sum of outgoing flows [");

        int sum = 0; // I track sum separately since we ignore outgoing flow in incoming direction
        boolean pastFirst = false; //
        for (Direction outgoingDirections : Direction.values()) {
            // skip incoming directions
            if (outgoingDirections == direction) {
                continue;
            }

            // update prompt
            // separate this outgoing flow from previous if it exists
            if (pastFirst) {
                builder.append("; ");
            }
            builder.append(outgoingDirections.toString().charAt(0))
                    .append(": ")
                    .append(outgoingFlows[outgoingDirections.ordinal()]);

            // update sum
            sum += outgoingFlows[outgoingDirections.ordinal()];

            // ensure prompt correctly updated
            // we must use a separate one from checking if sum != 0
            // because we can have outgoing flows of 0.
            pastFirst = true;
        }
        builder.append("]=")
                .append(sum)
                .append(" in direction: ")
                .append(direction);
        return builder.toString();
    }

    @Override
    public String createInvalidPermittedDirectionsMessage(Direction direction, int laneNum) {
        return "The order of permitted lanes is invalid for lane #" + laneNum + " for direction: " + direction.toString();
    }

    @Override
    public String createInvalidNumLanesMessage(Direction direction, String type) {
        type = type.toLowerCase();
        if (!type.equals("incoming") && !type.equals("outgoing")) {
            throw new RuntimeException("Invalid parameter for lane type: " + type + ". Should be either 'incoming' or 'outgoing'");
        }
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

    @Override
    public String createInvalidLaneAssignmentMessage() {
        return "Not all of the junction's incoming lanes are assigned to a group.";
    }
}
