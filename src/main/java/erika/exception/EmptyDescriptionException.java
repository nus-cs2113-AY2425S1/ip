package erika.exception;
/**
 * Represents the exception thrown when a Command has an empty description
 */
public class EmptyDescriptionException extends ErikaException{
    /**
     * @param taskType A <code>String</code> representing the type of task (i.e Todo, Event, Deadline etc)
     */
    public EmptyDescriptionException(String taskType) {
        super("Error: " + taskType + " cannot have empty description");
    }
}
