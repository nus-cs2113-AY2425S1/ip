package org.ajay.data.task;

import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.InvalidCommandFormatException;
import org.ajay.ui.TextUi;

public class Deadline extends Task {
    public final static String COMMAND_STRING = "deadline";
    final static String BY_KEYWORD_STRING = "/by";
    protected String by;
    public final static String TASK_STRING = "D";

    /**
     * Constructor for the Deadline class.
     *
     * @param description description of the deadline
     * @throws EmptyArgumentException
     *
     * @throws InvalidCommandFormatException
     */
    public Deadline(String description) throws EmptyArgumentException  {
        super(getDescriptionFromString(description));
        try {
            setBy(getDayFromString(description));
        } catch (InvalidCommandFormatException e) {
            TextUi.printExceptions(e.getMessage());
        }

    }


    /**
     * Constructor for the Deadline class.
     *
     * @param description description of the deadline
     * @param by          by date
     * @throws EmptyArgumentException
     */
    public Deadline(String description, String by) throws EmptyArgumentException  {
        super(description);
        setBy(by);

    }

    public Deadline(boolean isDone, String description, String by) throws EmptyArgumentException {
        super(description);
        setBy(by);
        super.setDoneState(isDone);
    }

    /**
     * Returns the by date.
     *
     * @return
     */
    public String getBy() {
        return by;
    }

    /**
     * Changes the by date.
     *
     * @param by
     */
    private void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the by date from the input string.
     *
     * @param input
     *
     * @return
     */
    public static String getDayFromString(String input) throws InvalidCommandFormatException {

        if (input == null) {
            throw new InvalidCommandFormatException("Invalid command format. Please include the by date. " + Error.INVAILD_COMMAND_FORMAT.toString());
        }

        int indexAfterBy = input.indexOf(BY_KEYWORD_STRING) + BY_KEYWORD_STRING.length();

        return input.substring(indexAfterBy).trim();
    }

    /**
     * Returns the description from the input string.
     *
     * @param input
     *
     * @return
     */
    public static String getDescriptionFromString(String input) {
        if (input == null) {
            return null;
        }

        int indexOfBy = input.indexOf(BY_KEYWORD_STRING);
        input = input.substring(0, indexOfBy);

        return input.trim();
    }

    @Override
    public String saveTaskString() {
        return TASK_STRING + " | " + (super.getDoneState() ? "1" : "0") + " | " + description + " | " + by;
    }

    public static Deadline loadTaskString(boolean isDone, String description, String by) throws EmptyArgumentException {
        Deadline deadline = new Deadline(isDone, description, by);
        return deadline;
    }


    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString() + " (by: " + by + ")";
    }
}
