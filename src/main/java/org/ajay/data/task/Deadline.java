package org.ajay.data.task;

import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.InvalidCommandFormatException;

public class Deadline extends Task {
    public final static String COMMAND_STRING = "deadline";
    final static String BY_KEYWORD_STRING = "/by";
    public final static String TASK_STRING = "D";

    protected String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param task description of the deadline
     * @throws EmptyArgumentException
     *
     * @throws InvalidCommandFormatException
     */
    public Deadline(String task) throws EmptyArgumentException, InvalidCommandFormatException {
        super(getDescriptionFromString(task));
        setBy(getDayFromString(task));

    }

    /**
     * Constructor for the Deadline class.
     *
     * @param task description of the deadline
     * @param by   by date
     * @throws EmptyArgumentException
     */
    public Deadline(String task, String by) throws EmptyArgumentException {
        super(task);
        setBy(by);

    }

    public Deadline(boolean isDone, String task, String by) throws EmptyArgumentException {
        super(task);
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

        int indexOfBy = input.indexOf(BY_KEYWORD_STRING);
        int indexAfterBy;

        // Check if the by keyword is missing
        if (indexOfBy == -1) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + BY_KEYWORD_STRING + "] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
            indexAfterBy = indexOfBy + BY_KEYWORD_STRING.length();
        }

        // Check if the by date is empty
        if (indexAfterBy == input.length()) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }

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

        int indexOfBy = input.indexOf(BY_KEYWORD_STRING);

        if (indexOfBy != -1) {
            input = input.substring(0, indexOfBy);
        }
        return input.trim();
    }

    @Override
    public String saveTaskString() {
        return TASK_STRING + " | " + (super.getDoneState() ? "1" : "0") + " | " + description + " | " + by;
    }

    public static Deadline loadTaskString(boolean isDone, String task, String by) throws EmptyArgumentException {
        Deadline deadline = new Deadline(isDone, task, by);
        return deadline;
    }

    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString() + " (by: " + by + ")";
    }
}
