package niwa.exception;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

public class NiwaTaskIndexOutOfBoundException extends NiwaException{
    public NiwaTaskIndexOutOfBoundException() {
        super(NiwaExceptionMessages.MESSAGE_INDEX_OUT_OF_BOUND);
    }
    public NiwaTaskIndexOutOfBoundException(int maxIndex) {
        super(NiwaExceptionMessages.MESSAGE_INDEX_OUT_OF_BOUND + " "
            + String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM, maxIndex));
    }
}
