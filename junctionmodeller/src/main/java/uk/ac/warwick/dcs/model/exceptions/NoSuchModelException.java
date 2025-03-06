package uk.ac.warwick.dcs.model.exceptions;

public class NoSuchModelException extends Exception {
    public NoSuchModelException(long modelId) {
        super("No running model exists with ID: " + modelId);
    }

    public NoSuchModelException(String modelConfigName) {
        super("No running model exists with name: " + modelConfigName);
    }
}
