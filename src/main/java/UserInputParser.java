import java.util.Scanner;

public class UserInputParser {
    public static String userInput;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int LENGTH_OF_TODO = 4;
    public static final int LENGTH_OF_EVENT = 5;
    public static final int LENGTH_OF_DEADLINE = 8;
    public static final int LENGTH_OF_SLASH_FROM = 5;
    public static final int LENGTH_OF_SLASH_TO = 3;
    public static final int LENGTH_OF_SLASH_BY = 3;
    public static final String EVENT_FORMAT = "Please follow format : event [task] /from [start time] /to [end time]";
    public static final String DEADLINE_FORMAT = "Please follow format : deadline [task] /by [time]";
    public static final String TODO_FORMAT = "Please follow format : todo [task]";

    public static Task parseTodo() throws FormattingError{

        String taskType = userInput.substring(0 , LENGTH_OF_TODO);
        String taskDescription = userInput.substring(LENGTH_OF_TODO).trim();
        if(taskDescription.isEmpty()){
            throw new FormattingError("The task to do is missing. " + TODO_FORMAT);
        }
        return new Todo(taskDescription);
    }

    public static Event parseEvent() throws FormattingError{

        String remainingTaskDescription = userInput.substring(LENGTH_OF_EVENT).trim();
        int indexOfFirstSlash = remainingTaskDescription.indexOf("/from");

        if(indexOfFirstSlash == -1){
            throw new FormattingError("/from is missing. " + EVENT_FORMAT);
        }

        String taskDescription = remainingTaskDescription.substring(0, indexOfFirstSlash).trim();

        if(taskDescription.isEmpty()){
            throw new FormattingError("task to do is missing. " + EVENT_FORMAT);
        }

        remainingTaskDescription = remainingTaskDescription.substring(indexOfFirstSlash + LENGTH_OF_SLASH_FROM).trim();
        int indexOfSecondSlash = remainingTaskDescription.indexOf("/to");

        if(indexOfSecondSlash == -1){
            throw new FormattingError("/to is missing. " + EVENT_FORMAT);
        }

        String startTime = remainingTaskDescription.substring(0, indexOfSecondSlash).trim();

        if(startTime.isEmpty()){
            throw new FormattingError("start time is missing. " + EVENT_FORMAT);
        }

        String endTime = remainingTaskDescription.substring(indexOfSecondSlash + LENGTH_OF_SLASH_TO).trim();
        if(endTime.isEmpty()){
            throw new FormattingError("end time is missing. " + EVENT_FORMAT);
        }

        return new Event(taskDescription , startTime, endTime);
    }

    public static Deadline parseDeadline() throws FormattingError{

        String remainingTaskDescription = userInput.substring(LENGTH_OF_DEADLINE).trim();
        int indexOfSlash = remainingTaskDescription.indexOf("/by");
        if(indexOfSlash == -1){
            throw new FormattingError("/by is missing. " + DEADLINE_FORMAT);
        }
        String taskDescription = remainingTaskDescription.substring(0, indexOfSlash).trim();
        if(taskDescription.isEmpty()){
            throw new FormattingError("task to do is missing. " + DEADLINE_FORMAT);
        }
        String deadline = remainingTaskDescription.substring(indexOfSlash + LENGTH_OF_SLASH_BY).trim();
        if(deadline.isEmpty()){
            throw new FormattingError("deadline is missing. " + DEADLINE_FORMAT);
        }
        return new Deadline(taskDescription , deadline);
    }

    public static char getCorrespondingCharForTaskType(String type){
        switch(type){
        case "todo" :
            return 'T';
        case "event" :
            return 'E';
        case "deadline" :
            return 'D';
        default:
            return ' ';
        }
    }

    public static String parseUserAction(){
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

    public static String getUserInput(){
        userInput = SCANNER.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    public static int parseMarkAndUnmarkTask() throws NumberFormatException , FormattingError{
        String[] parsedInputs = userInput.split(" ");
        if(parsedInputs.length == 1){
            throw new FormattingError("Task number is not indicated. Please follow format : mark/unmark [number]");
        }
        if(parsedInputs.length > 2){
            throw new FormattingError("Multiple tasks to mark is indicated in a single command. Please mark them one by one.");
        }
        String numberToMark = parsedInputs[1].trim();
        return Integer.parseInt(numberToMark);
    }
}
