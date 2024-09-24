package joe;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.time.LocalDate;

public class Deadline extends Task {

    // attributes
    private LocalDateTime deadlineDate;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm");

    //behaviour
    public Deadline(String itemDescription, LocalDateTime deadlineDate) {
        super(itemDescription);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String itemDescription, LocalDateTime deadlineDate, boolean isToDo) {
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

    public static Optional<LocalDateTime> extractDeadlineDate(String input) throws DateTimeParseException {

        Optional<LocalDateTime> deadlineDate;
        int indexDateSignaler = input.indexOf("/by") + 3;
        String deadlineString = input.substring(indexDateSignaler, input.length()).strip();

        if (deadlineString.length() > 0) {
            String[] dateSpecifiers = deadlineString.split(" ");
            if (dateSpecifiers.length > 1) {
                deadlineDate = TimeParser.extractDateWithTime(dateSpecifiers);
            } else {
                deadlineDate = TimeParser.extractDateWithoutTime(deadlineString);
            }
            return deadlineDate;
        } else {
            return Optional.empty();
        }
    }

    public static Deadline readInDeadline(String line) {
        String itemDescription;
        String deadlineString;
        boolean isToDo;

        if (line.contains("[not done]")) {
            isToDo = false;
        } else {
            isToDo = true;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        int startDateIndex = line.indexOf("(by:");
        itemDescription = line.substring(startDescriptionIndex, startDateIndex).strip();
        deadlineString = line.substring(startDateIndex + "(by:".length(), line.length() - 1).strip();
        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString, formatter);

        return new Deadline(itemDescription, deadlineDate, isToDo);
    }

    public boolean isDueBy(LocalDateTime dueDate) {
        return this.deadlineDate.isBefore(dueDate) || this.deadlineDate.isEqual(dueDate);
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
                " (by: " + deadlineDate.format(formatter) + ")";
    }
}
