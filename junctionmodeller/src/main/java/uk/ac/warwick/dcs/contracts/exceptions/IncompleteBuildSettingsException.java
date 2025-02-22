package uk.ac.warwick.dcs.contracts.exceptions;

/**
 * Exception thrown if any compulsory setting is not set in a builder in the
 * <code>uk.ac.warwick.dcs.contracts.builders</code> package.
 */
public class IncompleteBuildSettingsException extends Exception {
    public IncompleteBuildSettingsException(String fieldName, String functionName) {
        super("The '" + fieldName + "' was not configured in the builder. Use function " + functionName);
    }
}
