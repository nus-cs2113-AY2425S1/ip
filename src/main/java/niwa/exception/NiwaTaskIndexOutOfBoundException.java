package niwa.exception;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

/**
 * The class {@code NiwaTaskIndexOutOfBoundException} is a custom exception that indicates
 * an attempt to access a task index that is out of bounds within the {@code TaskList} task list.
 */
public class NiwaTaskIndexOutOfBoundException extends NiwaException {
    /**
     * Constructs a {@code NiwaTaskIndexOutOfBoundException} with a default message
     * indicating that the task index is out of bounds.
     */
    public NiwaTaskIndexOutOfBoundException() {
        super(NiwaExceptionMessages.MESSAGE_INDEX_OUT_OF_BOUND);
    }

    /**
     * Constructs a {@code NiwaTaskIndexOutOfBoundException} with a message that includes
     * the maximum valid task index.
     *
     * @param maxIndex The maximum valid index for the task list.
     */
    public NiwaTaskIndexOutOfBoundException(int maxIndex) {
        super(NiwaExceptionMessages.MESSAGE_INDEX_OUT_OF_BOUND + " "
                + String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM, maxIndex));
    }
}
