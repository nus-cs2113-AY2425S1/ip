package erika.exception;

/** Represents the exception thrown when a Command has an empty description. */
public class EmptyDescriptionException extends ErikaException {
    public EmptyDescriptionException(String taskType) {
        super("Error: " + taskType + " cannot have empty description");
    }
}
