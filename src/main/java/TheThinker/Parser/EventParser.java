package TheThinker.Parser;

import TheThinker.Exceptions.FormattingException;
import TheThinker.Tasks.Event;

public class EventParser extends UserInputParser{

    public static final int LENGTH_OF_EVENT = 5;

    public static final int LENGTH_OF_SLASH_FROM = 5;
    public static final int LENGTH_OF_SLASH_TO = 3;
    public static final String EVENT_FORMAT = "Please follow format : event [task] /from [start time] /to [end time]";

    /**
     * Parse user input based on the format of Event and use the result to create Event object.
     *
     * @return Event Object.
     * @throws FormattingException If /from , /to , task description , start time and end time
     * is missing from user input
     */
    public static Event parseEvent() throws FormattingException{

        String remainingTaskDescription = userInput.substring(LENGTH_OF_EVENT).trim();
        int indexOfFirstSlash = remainingTaskDescription.indexOf("/from");

        if(indexOfFirstSlash == -1){
            throw new FormattingException("/from is missing. " + EVENT_FORMAT);
        }

        String taskDescription = remainingTaskDescription.substring(0, indexOfFirstSlash).trim();

        if(taskDescription.isEmpty()){
            throw new FormattingException("task to do is missing. " + EVENT_FORMAT);
        }

        remainingTaskDescription = remainingTaskDescription.substring(indexOfFirstSlash + LENGTH_OF_SLASH_FROM).trim();
        int indexOfSecondSlash = remainingTaskDescription.indexOf("/to");

        if(indexOfSecondSlash == -1){
            throw new FormattingException("/to is missing. " + EVENT_FORMAT);
        }

        String startTime = DateParser.convertDateFormat(remainingTaskDescription.substring(0, indexOfSecondSlash).trim());

        if(startTime.isEmpty()){
            throw new FormattingException("start time is missing. " + EVENT_FORMAT);
        }

        String endTime = DateParser.convertDateFormat(remainingTaskDescription.substring(indexOfSecondSlash + LENGTH_OF_SLASH_TO).trim());

        if(endTime.isEmpty()){
            throw new FormattingException("end time is missing. " + EVENT_FORMAT);
        }

        return new Event(taskDescription , startTime, endTime);
    }
}
