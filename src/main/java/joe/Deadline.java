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

    public static String extractDescription(String input) {
        String fullDescription = extractDescription(input, "deadline");
        int indexOfDateSignaler = fullDescription.indexOf("/by");
        return fullDescription.substring(0, indexOfDateSignaler);
    }

    public static Optional<String> extractDeadlineDate(String input) {
        int indexDateSignaler = input.indexOf("/by") + 3;
        if (indexDateSignaler > 0) {
            String deadlineDate = input.substring(indexDateSignaler, input.length()).strip();
            return Optional.of(deadlineDate);
        } else {
            return Optional.empty(); //Use optional to be stable if no date is passed in
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
