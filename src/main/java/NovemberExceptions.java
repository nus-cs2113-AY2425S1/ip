public class NovemberExceptions extends Exception{
    private static final String DEADLINE_DUE_DATE = "/by";
    private static final String EVENT_START = "/from";
    private static final String EVENT_END = "/to";

    private static final int DEADLINE_DUE_DATE_LENGTH = DEADLINE_DUE_DATE.length();
    private static final int EVENT_START_LENGTH = EVENT_START.length();
    private static final int EVENT_END_LENGTH = EVENT_END.length();

    public static Todo validTodo(String description) {
        // If task description is not provided
        if (description.isBlank()) {
            throw new IllegalArgumentException("Please provide a task description.");
        }
        return new Todo(description);
    }

    public static Deadline validDeadline(String description) {
        int dueDateIndex = description.indexOf(DEADLINE_DUE_DATE);

        // If task description and due date marker are not provided
        if (description.isBlank()) {
            throw new IllegalArgumentException("Please provide a task description.");
        }

        // If due date marker is not provided
        if (dueDateIndex == -1) {
            throw new IllegalArgumentException("Please provide a due date marker.");
        }

        String deadlineDescription = description.substring(0, dueDateIndex);

        // If there is no task description before the due date marker
        if (deadlineDescription.isBlank()) {
            throw new IllegalArgumentException("Please provide a task description.");
        }

        String by = description.substring(dueDateIndex + DEADLINE_DUE_DATE_LENGTH);

        // If no date is provided after the due date marker
        if (by.isBlank()) {
            throw new IllegalArgumentException("Please provide a date after the due date marker.");
        }
        return new Deadline(deadlineDescription, by);
    }

    public static Event validEvent(String description) {
        int eventStartIndex = description.indexOf(EVENT_START);

        // If task description and event start marker are not provided
        if(description.isBlank()) {
            throw new IllegalArgumentException("Please provide a task description.");
        }

        // If event start marker is not provided
        if(eventStartIndex == -1) {
            throw new IllegalArgumentException("Please provide an event start marker.");
        }

        String eventDescription = description.substring(0, eventStartIndex);

        // If there is no task description before the due date marker.
        if (eventDescription.isBlank()) {
            throw new IllegalArgumentException("Please provide a task description.");
        }

        // If nothing is provided after the event start marker
        if (description.substring(eventStartIndex).isBlank()) {
            throw new IllegalArgumentException("Please provide a start date after the event start marker.");
        }

        int eventEndIndex = description.indexOf(EVENT_END);

        // If event end marker is not provided.
        if(eventEndIndex == -1) {
            throw new IllegalArgumentException("Please provide an event end marker.");
        }

        String startDate = description.substring(eventStartIndex + EVENT_START_LENGTH, eventEndIndex);

        // If there is no start date between the start date and end date markers.
        if (startDate.isBlank()) {
            throw new IllegalArgumentException("Please provide a start date after the event start marker.");
        }

        String endDate = description.substring(eventEndIndex + EVENT_END_LENGTH);

        // If there is no end date after the end date marker.
        if (endDate.isBlank()) {
            throw new IllegalArgumentException("Please provide an end date after the event end marker.");
        }

        return new Event(eventDescription, startDate, endDate);
    }
}