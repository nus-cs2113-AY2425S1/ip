package org.ajay.data.task;

import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.InvalidCommandFormatException;

public class Event extends Task {
    public final static String COMMAND_STRING = "event"; // Command string for the Event class
    final static String FROM_KEYWORD_STRING = "/from"; // Keyword string for the from date
    final static String TO_KEYWORD_STRING = "/to"; // Keyword string for the to date
    public final static String TASK_STRING = "E";

    protected String from;
    protected String to;

    /**
     * Constructor for the Event class.
     *
     * @param task description of the event
     *
     * @throws EmptyArgumentException
     * @throws InvalidCommandFormatException
     */
    public Event(String task) throws EmptyArgumentException, InvalidCommandFormatException {
        super(getDescriptionFromString(task));
        setFrom(getFromFromString(task));
        setTo(getToFromString(task));

    }

    /**
     * Constructor for the Event class.
     *
     * @param description description of the event
     * @param from        from date
     * @param to          to date
     */
    public Event(String description, String from, String to) throws EmptyArgumentException {
        super(description);
        setFrom(from);
        setTo(to);

    }

    /**
     * Constructor for the Event class.
     *
     * @param description description of the event
     * @param from        from date
     * @param to          to date
     */
    public Event(boolean isDone, String description, String from, String to) throws EmptyArgumentException {
        super(description);
        super.setDoneState(isDone);
        setFrom(from);
        setTo(to);
    }

    /**
     * Returns the from date.
     *
     * @return from date
     */
    public String getFrom() {
        return from;
    }

    /**
     * Changes the from date.
     *
     * @param from
     */
    private void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the to date.
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     * Changes the to date.
     *
     * @param to
     */
    private void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the description from the input string.
     *
     * @param input input string
     *
     * @return description
     */
    public static String getDescriptionFromString(String input) throws InvalidCommandFormatException {

        int indexOfFrom = input.indexOf(FROM_KEYWORD_STRING);

        if (indexOfFrom != -1) {
            input = input.substring(0, indexOfFrom);
        }
        return input.trim();
    }

    /**
     * Returns the from date from the input string.
     *
     * @param input input string
     *
     * @return
     */
    public static String getFromFromString(String input) throws InvalidCommandFormatException {

        int indexOfFrom = input.indexOf(FROM_KEYWORD_STRING);
        int indexOfTo = input.indexOf(TO_KEYWORD_STRING);
        int indexAfterFrom;

        boolean isFromBeforeTo = indexOfFrom < indexOfTo;

        if (indexOfFrom == -1 || indexOfTo == -1 ) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + FROM_KEYWORD_STRING + " & " +  TO_KEYWORD_STRING +"] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else if (!isFromBeforeTo) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_INVALID_KEYWORK_ARRAGEMENT + " [" + FROM_KEYWORD_STRING + " then " +  TO_KEYWORD_STRING +"] "+ Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
             indexAfterFrom = indexOfFrom + FROM_KEYWORD_STRING.length();
        }

        // Check if the from date is empty
        if (indexAfterFrom == indexOfTo - 1) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }


        return input.substring(indexAfterFrom, indexOfTo).trim();
    }

    /**
     * Returns the to date from the input string.
     *
     * @param input input string
     *
     * @return
     */
    public static String getToFromString(String input) throws InvalidCommandFormatException {

        int indexOfTo = input.indexOf(TO_KEYWORD_STRING);
        int indexAfterTo;

        if (indexOfTo == -1) {
            throw new InvalidCommandFormatException(
                    Messages.MESSAGE_MISSING_KEYWORD + " [" + FROM_KEYWORD_STRING + " & " +  TO_KEYWORD_STRING +"] "
                            + Error.INVAILD_COMMAND_FORMAT.toString());
        } else {
             indexAfterTo = indexOfTo + TO_KEYWORD_STRING.length();
        }

        // Check if the to date is empty
        if (indexAfterTo == input.length()) {
            throw new InvalidCommandFormatException(Messages.MESSAGE_EMPTY_DATE + " " + Error.EMPTY_ARG.toString());
        }



        return input.substring(indexAfterTo).trim();
    }

    @Override
    public String saveTaskString() {
        return TASK_STRING + " | " + (super.getDoneState() ? "1" : "0") + " | " + description + " | " + from + " | "
                + to;
    }

    public static Event loadTaskString(boolean isDone, String description, String from, String to)
            throws EmptyArgumentException {
        Event event = new Event(isDone, description, from, to);
        return event;
    }

    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
