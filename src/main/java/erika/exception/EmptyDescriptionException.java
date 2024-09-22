package erika.exception;

public class EmptyDescriptionException extends ErikaException{
    public EmptyDescriptionException(String taskType) {
        super("Error: " + taskType + " cannot have empty description");
    }
}
