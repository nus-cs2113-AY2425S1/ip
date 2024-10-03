package TheThinker.Parser;

import TheThinker.Exceptions.FormattingException;
import TheThinker.Tasks.Deadline;

public class DeadlineParser extends UserInputParser{
    public static final int LENGTH_OF_SLASH_BY = 3;
    public static final int LENGTH_OF_DEADLINE = 8;

    public static final String DEADLINE_FORMAT = "Please follow format : deadline [task] /by [time]";

    /**
     * Parse user input based on the format of Deadline and use the result to create Deadline object.
     *
     * @return Deadline Object.
     * @throws FormattingException If /by , task description , deadline is missing from user input
     */
    public static Deadline parseDeadline() throws FormattingException{

        String remainingTaskDescription = userInput.substring(LENGTH_OF_DEADLINE).trim();
        int indexOfSlash = remainingTaskDescription.indexOf("/by");

        if(indexOfSlash == -1){
            throw new FormattingException("/by is missing. " + DEADLINE_FORMAT);
        }

        String taskDescription = remainingTaskDescription.substring(0, indexOfSlash).trim();

        if(taskDescription.isEmpty()){
            throw new FormattingException("task description is missing. " + DEADLINE_FORMAT);
        }

        String deadline = DateParser.convertDateFormat(remainingTaskDescription.substring(indexOfSlash + LENGTH_OF_SLASH_BY).trim());

        if(deadline.isEmpty()){
            throw new FormattingException("deadline is missing. " + DEADLINE_FORMAT);
        }

        return new Deadline(taskDescription , deadline);
    }
}
