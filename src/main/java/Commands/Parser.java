package Commands;

import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

public class Parser {
    public static void parseTodoFromFile(String line, ArrayList<Task> taskArray){
        String splitLine = line.substring(line.lastIndexOf("]")+1);

        Todo todo = new Todo(splitLine.trim());
        taskArray.add(todo);

        if (line.contains("[X]")){
            todo.markAsDone();
        }
    }

    public static void parseDeadlineFromFile(String line, ArrayList<Task> taskArray) {
        int indexOfBy = line.indexOf("(by:");
        String activity = line.substring(line.lastIndexOf("]")+2, indexOfBy-1);
        String by = line.substring(indexOfBy + 4, line.length()-1);

        Deadline deadline = new Deadline(activity.trim(), by.trim());

        taskArray.add(deadline);

        if (line.contains("[X]")){
            deadline.markAsDone();
        }
    }

    public static void parseEventFromFile(String line, ArrayList<Task> taskArray){
        int indexOfFrom = line.indexOf("(from:");
        int indexOfTo = line.lastIndexOf("to");

        String activity = line.substring(line.lastIndexOf("]")+2, indexOfFrom-1);
        String by = line.substring(indexOfFrom + 6, indexOfTo-1);
        String to = line.substring(indexOfTo+3, line.length()-1);

        Events event = new Events(activity.trim(), by.trim(), to.trim());
        taskArray.add(event);

        if (line.contains("[X]")){
            event.markAsDone();
        }
    }

    public static String parseFirstWord(String input){
        String[] splitString = input.split(" ");
        return splitString[0];
    }

    public static int parseForMarkAndUnmarkCommand(String input){
        String[] splitInput;
        int indexOfTaskInList;

        splitInput = input.split(" ");
        indexOfTaskInList = Integer.parseInt(splitInput[1]) - 1;
        return indexOfTaskInList;
    }

    public static String parseForAddTodo(String input){
        if (input.trim().length() == "todo".length()){
            throw new EmptyTaskEntry();
        }

        String activityName = input.split(" ", 2)[1];
        return activityName.trim();
    }

    public static String[] parseForAddDeadline(String input){
        String[] deadlineInfo = new String[2];
        final int STRLENGTH_DEADLINE = 9;
        final int STRLENGTH_BY = 3;

        if (input.trim().length() == "deadline".length()){
            throw new EmptyTaskEntry();
        }
        if (!input.contains("by")){
            throw new StringIndexOutOfBoundsException();
        }

        String activityName = input.substring(input.indexOf("deadline") + STRLENGTH_DEADLINE, input.indexOf("by")).trim();
        String deadline = input.substring(input.indexOf("by") + STRLENGTH_BY).trim();

        deadlineInfo[0] = activityName;
        deadlineInfo[1] = deadline;

        return deadlineInfo;
    }

    public static String[] parseForAddEvent(String input){
        String[] eventInfo = new String[3];
        final int STRLENGTH_TO = 3;
        final int STRLENGTH_FROM = 5;
        final int STRLENGTH_EVENT = 6;

        int indexFrom = input.indexOf("from");
        int indexTo = input.indexOf("to");

        if (input.trim().length() == "event".length()){
            throw new EmptyTaskEntry();
        }
        if (indexFrom == -1 || indexTo == -1){
            throw new StringIndexOutOfBoundsException();
        }
        String activityName = input.substring(input.indexOf("event") + STRLENGTH_EVENT, indexFrom).trim();
        String from = input.substring(indexFrom + STRLENGTH_FROM, indexTo).trim();
        String to = input.substring(indexTo + STRLENGTH_TO).trim();

        eventInfo[0] = activityName;
        eventInfo[1] = from;
        eventInfo[2] = to;

        return eventInfo;
    }

    public static int parseForDelete(String input){
        String[] splitInput = input.split(" ");
        int indexToDelete = Integer.parseInt(splitInput[1]) - 1;

        return indexToDelete;
    }
}
