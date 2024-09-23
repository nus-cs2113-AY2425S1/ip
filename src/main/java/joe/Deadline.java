package joe;

import java.util.Optional;

public class Deadline extends Task {

    // attributes
    private String deadlineDate;

    //behaviour
    public Deadline(String itemDescription, String deadlineDate) {
        super(itemDescription);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String itemDescription, String deadlineDate, boolean isToDo) {
        super(itemDescription, isToDo);
        this.deadlineDate = deadlineDate;
    }

    public static String extractDescription(String input) throws EmptyTaskException {
        String fullDescription = extractDescription(input, "deadline");
        if (fullDescription.length() > 0) {
            int indexOfDateSignaler = fullDescription.indexOf("/by");
            return fullDescription.substring(0, indexOfDateSignaler);
        } else {
            throw new EmptyTaskException();
        }
    }

    public static Optional<String> extractDeadlineDate(String input) {
        int indexDateSignaler = input.indexOf("/by") + 3;
        String deadlineDate = input.substring(indexDateSignaler, input.length()).strip();
        if (deadlineDate.length() > 0) {
            return Optional.of(deadlineDate);
        } else {
            return Optional.empty();
        }
    }

    public static Deadline readInDeadline(String line) {
        String itemDescription;
        String deadlineDate;
        boolean isToDo;

        if (line.contains("[not done]")) {
            isToDo = false;
        } else {
            isToDo = true;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        int startDateIndex = line.indexOf("(by:");
        itemDescription = line.substring(startDescriptionIndex, startDateIndex).strip();
        deadlineDate = line.substring(startDateIndex + "(by:".length(), line.length() - 1).strip();

        return new Deadline(itemDescription, deadlineDate, isToDo);
    }

    @Override
    public String toString() {
        String checkBox;
        if (this.isToDo()) {
            checkBox = " [" + "not done" + "]";
            } else {
            checkBox = " [" + "done" + "]";
        }
        return "[D]" + checkBox + " " +
                this.getItemDescription() +
                " (by: " + deadlineDate + ")";
    }
}
