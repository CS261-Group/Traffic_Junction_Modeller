package uk.ac.warwick.dcs.dataproc.validation;

import uk.ac.warwick.dcs.contracts.enums.Direction;

/**
 * Interface for a factory for generating diagnostic error strings to
 * be returned by the validators in a simple and reusable way.
 */
public interface IDiagnosticFactory {
    String createFieldErrorMessage(String field, String error);
    String createInvalidFlowSumMessage(Direction direction, int incomingFlow, int[] outgoingFlows);
    String createInvalidPermittedDirectionsMessage(Direction direction, int laneNum);
    String createInvalidNumLanesMessage(Direction direction, String type);
    String createOutgoingFlowErrorMessage(Direction direction, boolean[] validDirections);
    String createInvalidGroupNumMessage(int groupNum);
    String createInvalidLaneAssignmentMessage();
}
