import java.util.Optional;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String itemDescription, String startDate, String endDate) {
        super(itemDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static String extractDescription(String input) {
        String fullDescription = extractDescription(input, "event");
        int indexOfDateSignaller = fullDescription.indexOf("/from");
        if (indexOfDateSignaller > 0) {
            return fullDescription.substring(0, indexOfDateSignaller);
        } else {
            return fullDescription;
        }
    }

    public static Optional<String> extractStartDate(String input){
        int indexOfStartSignaller = input.indexOf("/from") + 5;
        int indexOfEndSignaller = input.indexOf("/to");
        if (indexOfEndSignaller > 0 && indexOfStartSignaller > 0) {
            String startDate = input.substring(indexOfStartSignaller, indexOfEndSignaller).strip();
            return Optional.of(startDate);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> extractEndDate(String input){
        int indexOfEndSignaller = input.indexOf("/to") + 3;
        if (indexOfEndSignaller > 0) {
            String endDate = input.substring(indexOfEndSignaller, input.length()).strip();
            return Optional.of(endDate);
        } else {
            return Optional.empty();
        }
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
                "(from: " + startDate +
                " to: " + endDate + ")";
    }
}
