package org.ajay.data.task;

import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.InvalidCommandFormatException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public final static String COMMAND_WORD = "event"; // Command string for the Event class
    final static String FROM_KEYWORD = "/from"; // Keyword string for the from date
    final static String TO_KEYWORD = "/to"; // Keyword string for the to date
    public final static String TASK_ID = "E";

    public final static int TASK_LENGTH = 5;

    public static final int FROM_LOAD_INDEX = 3;
    public static final int TO_LOAD_INDEX = 4;

    protected String from;
    protected String to;

    /**
     * Constructor for the Event class.
     *
     * @param task Description of the event.
     *
     * @throws EmptyArgumentException        If the description is empty.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    public Event(String task) throws EmptyArgumentException, InvalidCommandFormatException {
        super(getDescriptionInput(task));
        setFrom(getFromKeywordInput(task));
        setTo(getToKeywordInput(task));
    }

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the event
     * @param from        From date
     * @param to          To date
     * @throws EmptyArgumentException If the description is empty.
     */
    public Event(String description, String from, String to) throws EmptyArgumentException {
        super(description);
        setFrom(from);
        setTo(to);
    }

    /**
     * Constructor for the Event class.
     *
     * @param isDone      Done state of the event
     * @param description Description of the event
     * @param from        From date
     * @param to          To date
     * @throws EmptyArgumentException If the description is empty.
     */
    public Event(boolean isDone, String description, String from, String to) throws EmptyArgumentException {
        super(description);
        super.setDoneState(isDone);
        setFrom(from);
        setTo(to);
    }

    /**
     * Gets date from the event.
     *
     * @return From date.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets date from the event.
     *
     * @param from From date.
     */
    private void setFrom(String from) {
        this.from = from;
    }

    /**
     * Get the to date.
     *
     * @return To date.
     */
    public String getTo() {
        return to;
    }

    /**
     * Set the to date.
     *
     * @param to To date.
     */
    private void setTo(String to) {
        this.to = to;
    }

    /**
     * Get the description from the input string.
     *
     * @param input User input string.
     * @return Description of the event.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    private static String getDescriptionInput(String input) throws InvalidCommandFormatException {

        int indexOfKeywordFrom = input.indexOf(FROM_KEYWORD);

        if (indexOfKeywordFrom != -1) {
            input = input.substring(0, indexOfKeywordFrom);
        }
        return input.trim();
    }

    /**
     * Get the from date from the input string.
     *
     * @param input User input string.
     * @return From date.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    private static String getFromKeywordInput(String input) throws InvalidCommandFormatException {

        int indexOfKeywordFrom = input.indexOf(FROM_KEYWORD);
        int indexOfKeywordTo = input.indexOf(TO_KEYWORD);
        int indexAfterKeywordFrom;

        boolean isFromBeforeTo = indexOfKeywordFrom < indexOfKeywordTo;

        if (indexOfKeywordFrom == -1 || indexOfKeywordTo == -1) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + FROM_KEYWORD + " & " + TO_KEYWORD + "] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else if (!isFromBeforeTo) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_INVALID_KEYWORK_ARRAGEMENT + " [" + FROM_KEYWORD + " then " + TO_KEYWORD + "] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
            indexAfterKeywordFrom = indexOfKeywordFrom + FROM_KEYWORD.length();
        }

        /** Check if the from date is empty */
        if (indexAfterKeywordFrom == indexOfKeywordTo - 1) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }
        return input.substring(indexAfterKeywordFrom, indexOfKeywordTo).trim();
    }

    /**
     * Get the to date from the input string.
     *
     * @param input User input string.
     * @return To date.
     * @throws InvalidCommandFormatException If the command format is invalid.
     */
    private static String getToKeywordInput(String input) throws InvalidCommandFormatException {

        int indexOfKeywordTo = input.indexOf(TO_KEYWORD);
        int indexAfterKeywordTo;

        if (indexOfKeywordTo == -1) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + FROM_KEYWORD + " & " + TO_KEYWORD + "] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
            indexAfterKeywordTo = indexOfKeywordTo + TO_KEYWORD.length();
        }

        /** Check if the from date is empty */
        if (indexAfterKeywordTo == input.length()) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }

        return input.substring(indexAfterKeywordTo).trim();
    }

    /**
     * Saves an event task to a string.
     *
     * @return String representation of the event task.
     */
    @Override
    public String saveTaskString() {
        return TASK_ID + " | " + (super.getDoneState() ? "1" : "0") + " | " + description + " | " + from + " | "
                + to;
    }

    /**
     * Loads an event task from a string.
     *
     * @param isDone      Done state of the event.
     * @param description Description of the event.
     * @param from        From date.
     * @param to          To date.
     * @return Event task.
     * @throws EmptyArgumentException If the description is empty.
     */
    public static Event loadTaskString(boolean isDone, String description, String from, String to)
            throws EmptyArgumentException {
        Event event = new Event(isDone, description, from, to);
        return event;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[" + TASK_ID + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
