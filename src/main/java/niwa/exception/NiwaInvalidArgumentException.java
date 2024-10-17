package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

/**
 * The class {@code NiwaInvalidArgumentException} is a custom exception that indicates
 * an invalid argument has been provided in a command.
 */
public class NiwaInvalidArgumentException extends NiwaException {
    /**
     * Constructs a {@code NiwaInvalidArgumentException} with a default message
     * indicating that the argument provided is invalid.
     */
    public NiwaInvalidArgumentException() {
        super(NiwaExceptionMessages.MESSAGE_INVALID_ARGUMENT);
    }

    /**
     * Constructs a {@code NiwaInvalidArgumentException} with a message that includes
     * the correct syntax for the argument.
     *
     * @param correctSyntax The correct syntax for the expected argument.
     */
    public NiwaInvalidArgumentException(String correctSyntax) {
        super(String.format(NiwaExceptionMessages.MESSAGE_INVALID_ARGUMENT_CORRECTION, correctSyntax));
    }
}
