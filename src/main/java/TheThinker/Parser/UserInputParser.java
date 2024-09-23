package TheThinker.Parser;

import TheThinker.Exceptions.FormattingException;
import TheThinker.Tasks.Deadline;
import TheThinker.Tasks.Event;
import TheThinker.Tasks.Task;
import TheThinker.Tasks.Todo;

import java.util.Scanner;

public class UserInputParser {

    public static String userInput;
    public static final Scanner scanner = new Scanner(System.in);
    public static final int LENGTH_OF_TODO = 4;
    public static final int LENGTH_OF_EVENT = 5;
    public static final int LENGTH_OF_DEADLINE = 8;
    public static final int LENGTH_OF_SLASH_FROM = 5;
    public static final int LENGTH_OF_SLASH_TO = 3;
    public static final int LENGTH_OF_SLASH_BY = 3;
    public static final String EVENT_FORMAT = "Please follow format : event [task] /from [start time] /to [end time]";
    public static final String DEADLINE_FORMAT = "Please follow format : deadline [task] /by [time]";
    public static final String TODO_FORMAT = "Please follow format : todo [task]";
    public static final String GET_FORMAT = "Please follow format : get [dd/mm/yyyy]";
    public static final String FIND_FORMAT = "Please follow format : find [keyword]";

    /**
     * Parse user input based on the format of To-do and use the result to create To-do object
     *
     * @return To-do Object
     * @throws FormattingException if task description is not provided in user input
     */
    public static Task parseTodo() throws FormattingException {

        String taskDescription = userInput.substring(LENGTH_OF_TODO).trim();

        if(taskDescription.isEmpty()){
            throw new FormattingException("The task to do is missing. " + TODO_FORMAT);
        }

        return new Todo(taskDescription);
    }

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

        String startTime = Date.convertDateFormat(remainingTaskDescription.substring(0, indexOfSecondSlash).trim());

        if(startTime.isEmpty()){
            throw new FormattingException("start time is missing. " + EVENT_FORMAT);
        }

        String endTime = Date.convertDateFormat(remainingTaskDescription.substring(indexOfSecondSlash + LENGTH_OF_SLASH_TO).trim());

        if(endTime.isEmpty()){
            throw new FormattingException("end time is missing. " + EVENT_FORMAT);
        }

        return new Event(taskDescription , startTime, endTime);
    }

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
            throw new FormattingException("task to do is missing. " + DEADLINE_FORMAT);
        }

        String deadline = Date.convertDateFormat(remainingTaskDescription.substring(indexOfSlash + LENGTH_OF_SLASH_BY).trim());

        if(deadline.isEmpty()){
            throw new FormattingException("deadline is missing. " + DEADLINE_FORMAT);
        }

        return new Deadline(taskDescription , deadline);
    }

    public static String getUserInput(){
        userInput = scanner.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    public static String parseUserAction(){
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

    /**
     * Parses the task number after unmark or mark command
     *
     * @return Task number
     * @throws NumberFormatException If task number string provided is not a number.
     * @throws FormattingException If no task or multiple task number is provided.
     */
    public static int parseNumberAfterTask() throws NumberFormatException , FormattingException{
        String[] parsedInputs = userInput.split(" ");

        if(parsedInputs.length == 1){
            throw new FormattingException("Task number is not indicated. Please follow format : mark/unmark [number]");
        }

        if(parsedInputs.length > 2){
            throw new FormattingException("Multiple tasks to mark is indicated in a single command. Please mark them one by one.");
        }

        String numberToMark = parsedInputs[1].trim();
        return Integer.parseInt(numberToMark);
    }

    /**
     * Parse date after get command
     *
     * @return date in dd/MM/yyyy format
     * @throws FormattingException If date is missing or in the wrong format
     */
    public static String parseDateAfterGet() throws FormattingException{
        String[] parsedInputs = userInput.split(" ");
        if(parsedInputs.length != 2){
            throw new FormattingException("specified date is missing. " + GET_FORMAT);
        }
        if(!Date.isDateOnlyFormat(parsedInputs[1].trim())){
            throw new FormattingException("date is in the wrong format. " + GET_FORMAT);
        }
        return parsedInputs[1].trim();
    }

    public static String parseKeywordAfterFind() throws FormattingException{
        String[] parsedInputs = userInput.split(" ");
        if(parsedInputs.length != 2){
            throw new FormattingException("keyword is missing. " + FIND_FORMAT);
        }

        return parsedInputs[1].trim();
    }
}
