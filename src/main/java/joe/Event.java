package joe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Extends Tasks with a start and end date
 * Encapsulates methods to parse and operate on Event objects
 */
public class Event extends Task{

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm");
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs a new Event object with default todo status
     * @param itemDescription String description of the task
     * @param startDate LocalDateTime specifying the start of this task
     * @param endDate LocalDateTime specifying the end of this task
     */
    public Event(String itemDescription, LocalDateTime startDate, LocalDateTime endDate) {
        super(itemDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a new Event object with modifiable todo status
     * @param itemDescription String description of the task
     * @param startDate LocalDateTime specifying the start of this task
     * @param endDate LocalDateTime specifying the end of this task
     * @param isToDo boolean todo status of the task
     */
    public Event(String itemDescription, LocalDateTime startDate, LocalDateTime endDate, boolean isToDo) {
        super(itemDescription, isToDo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Extracts the Event's description
     * @param input the user input
     * @return the task description
     * @throws EmptyTaskException if no Event description exists
     */
    public static String extractDescription(String input) throws EmptyTaskException {
        String fullDescription = extractDescription(input, "event");
        if(fullDescription.length() > 0) {
            int indexOfDateSignaller = fullDescription.indexOf("/from");
            return fullDescription.substring(0, indexOfDateSignaller);
        } else {
            throw new EmptyTaskException();
        }
    }

    /**
     * Extracts the Event's start date
     * @param input the user input
     * @return Optional<LocalDateTime></LocalDateTime> the time when the event begins
     */
    public static Optional<LocalDateTime> extractStartDate(String input){

        int indexOfStartSignaller = input.indexOf("/from") + 5;
        int indexOfEndSignaller = input.indexOf("/to");
        String startDateString = input.substring(indexOfStartSignaller, indexOfEndSignaller).strip();

        if (startDateString.length() > 0) {
            Optional<LocalDateTime> startDate;
            String[] startDateSpecifiers = startDateString.split(" ");
            if (startDateSpecifiers.length > 1) {
                startDate = TimeParser.extractDateTimeFromDouble(startDateSpecifiers);
            } else {
                startDate = TimeParser.extractDateTimeFromSingle(startDateString);
            }
            return startDate;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Extracts the Event's end date
     * @param input the user input
     * @return Optional<LocalDateTime></LocalDateTime> the time when the Event ends
     */
    public static Optional<LocalDateTime> extractEndDate(String input){
        int indexOfEndSignaller = input.indexOf("/to") + 3;
        String endDateString = input.substring(indexOfEndSignaller, input.length()).strip();
        if (endDateString.length() > 0) {
            Optional<LocalDateTime> endDate;
            String[] endDateSpecifiers = endDateString.split(" ");
            if (endDateSpecifiers.length > 1) {
                endDate = TimeParser.extractDateTimeFromDouble(endDateSpecifiers);
            } else {
                endDate = TimeParser.extractDateTimeFromSingle(endDateString);
            }
            return endDate;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a String specifying an event into an Event object
     * @param line the String specifying an event
     * @return the parse Event object
     */
    public static Event readInEvent(String line) {

        boolean isToDo;
        if (line.contains("[not done]")) {
            isToDo = true;
        } else {
            isToDo = false;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        int fromIndex = line.indexOf("(from:");
        int toIndex = line.indexOf("to:");
        String itemDescription = line.substring(startDescriptionIndex, fromIndex).strip();
        String startDateString = line.substring(
            fromIndex + "(from:".length(),
            toIndex
        ).strip();
        String endDateString = line.substring(
            toIndex + "to:".length(),
            line.length() - 1
        ).strip();

        LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);

        return new Event(itemDescription, startDate, endDate, isToDo);

    }

    /**
     * Computes whether the event begins previous or at a provided dueDate
     * @param dueDate LocalDateTime specifying the time for comparison
     * @return true if the event begins before/at dueDate, false otherwise
     */
    public boolean isDueBy(LocalDateTime dueDate) {
        return this.startDate.isBefore(dueDate) || this.startDate.isEqual(dueDate);
    }

    /**
     * Creates String representing the event
     * @return String
     */
    @Override
    public String toString() {
        String checkBox;
        if (this.isToDo()) {
            checkBox = " [" + "not done" + "]";
        } else {
            checkBox = " [" + "done" + "]";
        }

        return "[E]" + checkBox + " " +
                this.getItemDescription() +
                " (from: " + startDate.format(formatter) +
                " to: " + endDate.format(formatter) + ")";
    }
}
