package joe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Extends Tasks with an additional specification of when the Task is to be done.
 * Encapsulates methods for parsing and modifying Deadline objects.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm");
    private LocalDateTime deadlineDate;

    /**
     * Constructs a new Deadline Object with todo status set to true per default
     * @param itemDescription String describing the general task
     * @param deadlineDate LocalDateTime specifying the deadline of the task
     */
    public Deadline(String itemDescription, LocalDateTime deadlineDate) {
        super(itemDescription);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructs a new Deadline Object with the todo status being a modifyiable
     * @param itemDescription String describing the task
     * @param deadlineDate LocalDateTime specifying the deadline of the task
     * @param isToDo todo status of the task
     */
    public Deadline(String itemDescription, LocalDateTime deadlineDate, boolean isToDo) {
        super(itemDescription, isToDo);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Extracts the Deadline's description from the user input
     * @param input the full user input string
     * @return the Deadline's description
     * @throws EmptyTaskException
     */
    public static String extractDescription(String input) throws EmptyTaskException {

        String fullDescription = extractDescription(input, "deadline");
        if (fullDescription.length() > 0) {
            int indexOfDateSignaler = fullDescription.indexOf("/by");
            return fullDescription.substring(0, indexOfDateSignaler);
        } else {
            throw new EmptyTaskException();
        }

    }

    /**
     * Extracts the deadlineDate (due date) of the user input
     * @param input the full user input string
     * @return Optional<LocalDateTime></LocalDateTime> the parsed deadlineDate as specified in the user input
     * @throws DateTimeParseException if the provided user input cannot be parsed into a LocalDateTime object
     */
    public static Optional<LocalDateTime> extractDeadlineDate(String input) throws DateTimeParseException {

        Optional<LocalDateTime> deadlineDate;
        int indexDateSignaler = input.indexOf("/by") + 3;
        String deadlineString = input.substring(indexDateSignaler, input.length()).strip();

        if (deadlineString.length() > 0) {
            String[] dateSpecifiers = deadlineString.split(" ");
            if (dateSpecifiers.length > 1) {
                deadlineDate = TimeParser.extractDateTimeFromDouble(dateSpecifiers);
            } else {
                deadlineDate = TimeParser.extractDateTimeFromSingle(deadlineString);
            }
            return deadlineDate;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses strings specifying a Deadline into Deadline objects
     * @param line the String that is to be parsed into a Deadline object
     * @return Deadline the parsed Deadline object
     */
    public static Deadline readInDeadline(String line) {
        String itemDescription;
        String deadlineString;
        boolean isToDo;

        if (line.contains("[not done]")) {
            isToDo = true;
        } else {
            isToDo = false;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        int startDateIndex = line.indexOf("(by:");
        itemDescription = line.substring(startDescriptionIndex, startDateIndex).strip();
        deadlineString = line.substring(startDateIndex + "(by:".length(), line.length() - 1).strip();
        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString, formatter);

        return new Deadline(itemDescription, deadlineDate, isToDo);
    }

    /**
     * Determines whether this deadline object has is in the timeframe up to a given due date.
     * @param dueDate LocalDateTime against which the date of the deadline is compared
     * @return true if the deadline object is due previous or at the given dueDate. Returns false otherwise.
     */
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
