public class UnspecifiedEventDurationException extends AppalException {
    public static final String UNSPECIFIED_EVENT_DURATION_MESSAGE = "Remember to specify a start and end time, pal!" +
            "\nExample input: event [TASK] /from [START TIME] /to [END TIME]";

    public UnspecifiedEventDurationException() {
        super(UNSPECIFIED_EVENT_DURATION_MESSAGE);
    }
}
