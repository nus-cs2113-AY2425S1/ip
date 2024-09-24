package joe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm");


    public Event(String itemDescription, LocalDateTime startDate, LocalDateTime endDate) {
        super(itemDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String itemDescription, LocalDateTime startDate, LocalDateTime endDate, boolean isToDo) {
        super(itemDescription, isToDo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static String extractDescription(String input) throws EmptyTaskException {
        String fullDescription = extractDescription(input, "event");
        if(fullDescription.length() > 0) {
            int indexOfDateSignaller = fullDescription.indexOf("/from");
            return fullDescription.substring(0, indexOfDateSignaller);
        } else {
            throw new EmptyTaskException();
        }
    }

    public static Optional<LocalDateTime> extractStartDate(String input){

        int indexOfStartSignaller = input.indexOf("/from") + 5;
        int indexOfEndSignaller = input.indexOf("/to");
        String startDateString = input.substring(indexOfStartSignaller, indexOfEndSignaller).strip();

        if (startDateString.length() > 0) {
            Optional<LocalDateTime> startDate;
            String[] startDateSpecifiers = startDateString.split(" ");
            if (startDateSpecifiers.length > 1) {
                startDate = TimeParser.extractDateWithTime(startDateSpecifiers);
            } else {
                startDate = TimeParser.extractDateWithoutTime(startDateString);
            }
            return startDate;
        } else {
            return Optional.empty();
        }
    }

    public static Optional<LocalDateTime> extractEndDate(String input){
        int indexOfEndSignaller = input.indexOf("/to") + 3;
        String endDateString = input.substring(indexOfEndSignaller, input.length()).strip();
        if (endDateString.length() > 0) {
            Optional<LocalDateTime> endDate;
            String[] endDateSpecifiers = endDateString.split(" ");
            if (endDateSpecifiers.length > 1) {
                endDate = TimeParser.extractDateWithTime(endDateSpecifiers);
            } else {
                endDate = TimeParser.extractDateWithoutTime(endDateString);
            }
            return endDate;
        } else {
            return Optional.empty();
        }
    }

    public static Event readInEvent(String line) {

        boolean isToDo;
        if (line.contains("[not done]")) {
            isToDo = false;
        } else {
            isToDo = true;
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

    public boolean isDueBy(LocalDateTime dueDate) {
        return this.startDate.isBefore(dueDate) || this.startDate.isEqual(dueDate);
    }

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
