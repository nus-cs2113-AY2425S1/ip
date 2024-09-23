package joe;

import java.util.Optional;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String itemDescription, String startDate, String endDate) {
        super(itemDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String itemDescription, String startDate, String endDate, boolean isToDo) {
        super(itemDescription, isToDo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static String extractDescription(String input) {
        String fullDescription = extractDescription(input, "event");
        int indexOfDateSignaller = fullDescription.indexOf("/from");
        return fullDescription.substring(0, indexOfDateSignaller);
    }

    public static Optional<String> extractStartDate(String input){
        int indexOfStartSignaller = input.indexOf("/from") + 5;
        int indexOfEndSignaller = input.indexOf("/to");
        String startDate = input.substring(indexOfStartSignaller, indexOfEndSignaller).strip();
        if (startDate.length() > 0) {
            return Optional.of(startDate);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> extractEndDate(String input){
        int indexOfEndSignaller = input.indexOf("/to") + 3;
        String endDate = input.substring(indexOfEndSignaller, input.length()).strip();
        if (endDate.strip().length() > 0) {
            return Optional.of(endDate);
        } else {
            return Optional.empty();
        }
    }

    public static Event readInEvent(String line) {
        String itemDescription;
        String startDate;
        String endDate;
        boolean isToDo;

        if (line.contains("[not done]")) {
            isToDo = false;
        } else {
            isToDo = true;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        int fromIndex = line.indexOf("(from:");
        int toIndex = line.indexOf("to:");

        itemDescription = line.substring(startDescriptionIndex, fromIndex).strip();

        startDate = line.substring(
            fromIndex + "(from:".length(),
            toIndex
        ).strip();

        endDate = line.substring(
            toIndex + "to:".length(),
            line.length() - 1
        ).strip();

        return new Event(itemDescription, startDate, endDate, isToDo);

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
                " (from: " + startDate +
                " to: " + endDate + ")";
    }
}
