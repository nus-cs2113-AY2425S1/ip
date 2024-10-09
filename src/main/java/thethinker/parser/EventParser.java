package thethinker.parser;

import thethinker.exceptions.FormattingException;
import thethinker.tasks.Event;

public class EventParser extends UserInputParser{

    public static final int LENGTH_OF_EVENT = 5;
    public static final int LENGTH_OF_SLASH_FROM = 5;
    public static final int LENGTH_OF_SLASH_TO = 3;
    public static final String EVENT_FORMAT = "Please follow format : event [task] /from [start time] /to [end time]";

    /**
     * Parse user input based on the format of Event and use the result to create Event object.
     *
     * @throws FormattingException If /from , /to , task description , start time and end time
     * is missing from user input. If /to is in front of /from.
     */
    public static Event parseEvent() throws FormattingException {

        String remainingTaskDescription = userInput.substring(LENGTH_OF_EVENT).trim();
        int indexOfFrom = remainingTaskDescription.indexOf("/from");
        int indexOfTo = remainingTaskDescription.indexOf("/to");

        if (indexOfFrom > indexOfTo) {
            throw new FormattingException("/to is in front of /from. " + EVENT_FORMAT);
        }
        if (indexOfFrom == -1) {
            throw new FormattingException("/from is missing. " + EVENT_FORMAT);
        }

        String taskDescription = remainingTaskDescription.substring(0, indexOfFrom).trim();

        if (taskDescription.isEmpty()) {
            throw new FormattingException("task to do is missing. " + EVENT_FORMAT);
        }

        int indexAfterFrom = indexOfFrom + LENGTH_OF_SLASH_FROM;
        remainingTaskDescription = remainingTaskDescription.substring(indexAfterFrom).trim();
        int indexOfSecondSlash = remainingTaskDescription.indexOf("/to");

        if (indexOfSecondSlash == -1) {
            throw new FormattingException("/to is missing. " + EVENT_FORMAT);
        }

        String startTime = remainingTaskDescription.substring(0, indexOfSecondSlash).trim();
        String startDate = DateParser.convertDateFormat(startTime);

        if (startDate.isEmpty()) {
            throw new FormattingException("start time is missing. " + EVENT_FORMAT);
        }

        String endTime = remainingTaskDescription.substring(indexOfSecondSlash + LENGTH_OF_SLASH_TO).trim();
        String endDate = DateParser.convertDateFormat(endTime);

        if (endDate.isEmpty()) {
            throw new FormattingException("end time is missing. " + EVENT_FORMAT);
        }

        return new Event(taskDescription , startDate, endDate);
    }
}
