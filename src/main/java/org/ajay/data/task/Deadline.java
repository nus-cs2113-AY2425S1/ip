package org.ajay.data.task;

import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.InvalidCommandFormatException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public final static String COMMAND_WORD = "deadline";
    final static String BY_KEYWORD = "/by";
    public final static String TASK_ID = "D";
    public final static int TASK_LENGTH = 4;

    public static final int BY_LOAD_INDEX = 3;

    protected String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param task Description of the deadline.
     *
     * @throws EmptyArgumentException        If the description is empty.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    public Deadline(String task) throws EmptyArgumentException, InvalidCommandFormatException {
        super(getDescriptionInput(task));
        setBy(getByKeywordInput(task));

    }

    /**
     * Constructor for the Deadline class.
     *
     * @param task Description of the deadline.
     * @param by   By date.
     * @throws EmptyArgumentException If the description is empty.
     */
    public Deadline(String task, String by) throws EmptyArgumentException {
        super(task);
        setBy(by);
    }

    /**
     * Constructor for the Deadline class.
     *
     * @param isDone Done state of the deadline.
     * @param task   Description of the deadline.
     * @param by     By date.
     * @throws EmptyArgumentException If the description is empty.
     */
    public Deadline(boolean isDone, String task, String by) throws EmptyArgumentException {
        super(task);
        setBy(by);
        super.setDoneState(isDone);
    }

    /**
     * Get the by date.
     *
     * @return By date.
     */
    public String getBy() {
        return by;
    }

    /**
     * Set the by date.
     *
     * @param by By date.
     */
    private void setBy(String by) {
        this.by = by;
    }

    /**
     * Get the by date from the input.
     *
     * @param input Input string.
     * @return By date.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    public static String getByKeywordInput(String input) throws InvalidCommandFormatException {

        int indexOfBy = input.indexOf(BY_KEYWORD);
        int indexAfterBy;

        // Check if the by keyword is missing
        if (indexOfBy == -1) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + BY_KEYWORD + "] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
            indexAfterBy = indexOfBy + BY_KEYWORD.length();
        }

        /** Check if the by date is empty */
        if (indexAfterBy == input.length()) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }

        return input.substring(indexAfterBy).trim();
    }

    /**
     * Get the description from the input.
     *
     * @param input Input string.
     * @return Description.
     */
    public static String getDescriptionInput(String input) {

        int indexOfBy = input.indexOf(BY_KEYWORD);

        if (indexOfBy != -1) {
            input = input.substring(0, indexOfBy);
        }
        return input.trim();
    }

    /**
     * Saves a deadline task to a string.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String saveTaskString() {
        return TASK_ID + " | " + (super.getDoneState() ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Loads a deadline task from a string.
     *
     * @param isDone Done state of the deadline.
     * @param task   Description of the deadline.
     * @param by     By date.
     * @return Deadline task.
     * @throws EmptyArgumentException If the description is empty.
     */
    public static Deadline loadTaskString(boolean isDone, String task, String by) throws EmptyArgumentException {
        Deadline deadline = new Deadline(isDone, task, by);
        return deadline;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + TASK_ID + "]" + super.toString() + " (by: " + by + ")";
    }
}
