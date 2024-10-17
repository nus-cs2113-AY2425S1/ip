package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

/**
 * The class {@code NiwaInvalidSyntaxException} is a custom exception that indicates
 * that an invalid syntax has been provided in a command.
 */
public class NiwaInvalidSyntaxException extends NiwaException {

    /**
     * Constructs a {@code NiwaInvalidSyntaxException} with a default message
     * indicating that the provided syntax is invalid.
     */
    public NiwaInvalidSyntaxException() {
        super(NiwaExceptionMessages.MESSAGE_INVALID_SYNTAX);
    }
}
