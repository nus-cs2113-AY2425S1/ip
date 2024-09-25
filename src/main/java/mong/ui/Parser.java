package mong.ui;

import mong.exception.IllegalTaskTypeException;
import mong.task.TaskList;
import mong.task.TaskType;

public class Parser {
    /**
     * Returns the TaskType enum value of the first word in the input.
     */
    public static TaskType decipherTaskType(String input) throws IllegalTaskTypeException {
        try {
            return TaskType.fromCommand(input);
        } catch (IllegalTaskTypeException e) {
            throw new IllegalTaskTypeException(e.getMessage());
        }
    }


    /**
     * Adds tasks line by line from txt file.
     */
    public static void parseLineContents(String line) {
        String[] lineData = line.split(" \\| ");
        if (lineData[0].contentEquals("T")) {
            TaskList.addTodo(lineData[1], lineData[2]);
        } else if (lineData[0].contentEquals("D")) {
            TaskList.addDeadline(lineData[1], lineData[2], lineData[3]);
        } else if (lineData[0].contentEquals("E")) {
            TaskList.addEvent(lineData[1], lineData[2], lineData[3], lineData[4]);
        }
    }

    public static void runCommand(TaskType command, String input) {
        switch(command) {
        case LIST:
            // print items in an indexed list
            TaskList.handleListCommand(input);
            break;
        case MARK:
            TaskList.mark(input);
            break;
        case UNMARK:
            TaskList.unmark(input);
            break;
        case DEADLINE:
            TaskList.addDeadline(input);
            break;
        case TODO:
            TaskList.addTodo(input);
            break;
        case EVENT:
            TaskList.addEvent(input);
            break;
        case DELETE:
            TaskList.deleteTask(input);
            break;
        case FIND:
            TaskList.find(input);
            break;
        default:
            System.out.println("MooONG?! That's not a valid command...");
            break;
        }
    }
}

