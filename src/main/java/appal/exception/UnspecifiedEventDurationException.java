package appal.exception;

/**
 * UnspecifiedEventDurationException handles the error when the user did not specify the event duration,
 * and shows a message to notify user.
 */
public class UnspecifiedEventDurationException extends AppalException {
    public static final String UNSPECIFIED_EVENT_DURATION_MESSAGE = "Remember to specify a start and end time, pal!" +
            "\nExample input: event [TASK] /from [START TIME] /to [END TIME]";

    /**
     * Class constructor.
     */
    public UnspecifiedEventDurationException() {
        super(UNSPECIFIED_EVENT_DURATION_MESSAGE);
    }
}
