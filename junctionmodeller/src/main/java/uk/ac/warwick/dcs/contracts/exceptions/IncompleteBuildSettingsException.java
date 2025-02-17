package uk.ac.warwick.dcs.contracts.exceptions;

public class IncompleteBuildSettingsException extends Exception {
    public IncompleteBuildSettingsException(String fieldName, String functionName) {
        super("The field " + fieldName + " was not configured in the builder. Use function " + functionName);
    }
}
