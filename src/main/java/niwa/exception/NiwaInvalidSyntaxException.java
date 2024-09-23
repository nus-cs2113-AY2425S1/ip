package niwa.exception;

import niwa.messages.NiwaExceptionMessages;

public class NiwaInvalidSyntaxException extends NiwaException{

    public NiwaInvalidSyntaxException() {
        super(NiwaExceptionMessages.MESSAGE_INVALID_SYNTAX);
    }

}
