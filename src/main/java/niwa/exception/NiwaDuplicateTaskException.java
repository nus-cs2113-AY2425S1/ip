package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

public class NiwaDuplicateTaskException extends RuntimeException {
    public NiwaDuplicateTaskException() {
        super(NiwaExceptionMessages.MESSAGE_DUPLICATE_TASK);
    }
}
