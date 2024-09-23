package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

public class NiwaInvalidArgumentException extends NiwaException{
    public NiwaInvalidArgumentException() {
        super(NiwaExceptionMessages.MESSAGE_INVALID_ARGUMENT);
    }
    public NiwaInvalidArgumentException(String correctSyntax) {
        super(String.format(NiwaExceptionMessages.MESSAGE_INVALID_ARGUMENT_CORRECTION, correctSyntax));
    }
}
