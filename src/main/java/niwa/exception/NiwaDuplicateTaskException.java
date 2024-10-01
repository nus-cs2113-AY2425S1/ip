package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

/**
 * The class {@code NiwaDuplicateTaskException} is a custom runtime exception that indicates
 * an attempt to add a duplicate task to the task list within the {@code TaskList} task list.
 */
public class NiwaDuplicateTaskException extends RuntimeException {
    /**
     * Constructs a {@code NiwaDuplicateTaskException} with a default message
     * indicating that a duplicate task has been detected.
     */
    public NiwaDuplicateTaskException() {
        super(NiwaExceptionMessages.MESSAGE_DUPLICATE_TASK);
    }
}
