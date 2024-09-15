package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

public class Deadline extends Task{
    protected String by;

    private static String getDeadlineDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        validityCheck(input);
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1);
    }

    private static void validityCheck(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        int indexOfBy = input.indexOf("/by");
        if (indexOfBy == 9) {
            throw new LeginEmptyTaskException();
        }
        if (indexOfBy == -1 || input.length() == indexOfBy + 3 || input.length() == indexOfBy + 4) {
            throw new LeginMissingParamsException();
        }
    }

    public Deadline(String input) throws LeginEmptyTaskException, LeginMissingParamsException {
        super(getDeadlineDescription(input));
        int startingIndexOfDueDate = input.indexOf("/by") + 4;
        this.by = input.substring(startingIndexOfDueDate);
    }

    public Deadline(String description, String by) throws LeginEmptyTaskException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
