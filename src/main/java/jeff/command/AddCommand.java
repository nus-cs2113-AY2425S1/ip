package jeff.command;

import jeff.exception.InvalidFormatException;
import jeff.exception.TaskDescriptionException;
import jeff.exception.TaskFieldException;
import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.Todo;

import java.io.IOException;

public class AddCommand extends Command {

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int SLASH_BY_LENGTH = 3;
    private static final int SLASH_FROM_LENGTH = 5;
    private static final int SLASH_TO_LENGTH = 3;

    public AddCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    //Returns the description of a task
    public static String processTaskDescription(String line , int start, int end){
        //If the task fields are empty, set the end number to the length of the line
        if(start > end){
            end = line.length();
        }
        //Remove the empty space before the description
        return line.substring(start, end).trim();
    }

    //Returns the field of the task, returns an empty string otherwise
    public static String processTaskField(String line, int fieldIndex, int start, int end){
        if(fieldIndex == -1){
            return "";
        }
        if(start > end){
            end = line.length();
        }
        String field = line.substring(start, end).trim();
        if(field.isEmpty()){
            return "";
        }
        //If field is valid, remove the empty space before the field
        return field;
    }

    //Instantiates Todo object
    public static Todo processTodo(String line) throws TaskDescriptionException {
        String description = processTaskDescription(line, TODO_LENGTH, line.length());
        if(description.isEmpty()) {
            throw new TaskDescriptionException("todo");
        }
        return new Todo(description);
    }

    //Instantiates Deadline object
    public static Deadline processDeadline(String line) throws TaskDescriptionException,
            TaskFieldException, InvalidFormatException {
        int byIndex = line.indexOf("/by");
        String description = processTaskDescription(line, DEADLINE_LENGTH, byIndex);
        if(description.isEmpty()) {
            throw new TaskDescriptionException("deadline");
        }
        String by = processTaskField(line, byIndex, byIndex + SLASH_BY_LENGTH, line.length());
        if(by.isEmpty()) {
            throw new TaskFieldException("'by'");
        }
        return new Deadline(description, by);
    }

    //Creates the Exception message for Event fields (if any)
    public static String eventExceptionMessage(boolean from, boolean to){
        StringBuilder errMsg = new StringBuilder();
        if (from) {
            errMsg.append("'from'");
        }

        if (from && to) {
            errMsg.append(" and ");
        }

        if (to) {
            errMsg.append("'to'");
        }
        return errMsg.toString();
    }

    //Instantiates Event object
    public static Event processEvent(String line) throws TaskDescriptionException,
            TaskFieldException, InvalidFormatException {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.lastIndexOf("/to");
        String description = processTaskDescription(line, EVENT_LENGTH, fromIndex - 1);
        if(description.isEmpty()) {
            throw new TaskDescriptionException("event");
        }
        String from = processTaskField(line, fromIndex, fromIndex + SLASH_FROM_LENGTH, toIndex - 1);
        String to = processTaskField(line, toIndex, toIndex + SLASH_TO_LENGTH, line.length());
        String errMsg = eventExceptionMessage(from.isEmpty(), to.isEmpty());
        if(!errMsg.isEmpty()) {
            throw new TaskFieldException(errMsg);
        }
        return new Event(description, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task;
        try {
            switch (firstWord) {
            case "todo":
                task = processTodo(line);
                break;
            case "deadline":
                task = processDeadline(line);
                break;
            case "event":
                task = processEvent(line);
                break;
            default:
                task = null;
                break;
            }

            //Adding the different tasks to taskList once processed.
            tasks.addTask(task);
            storage.writeFileTask(tasks);

            //Prints the following text if the user inputs the task with the correct fields
            System.out.print("Haiyaa the following task needs to be done:" + System.lineSeparator()
                    + "  " + task + System.lineSeparator() +
                    "Now you have " + tasks.getCount() + " task(s) in ur list");

            //Otherwise it will print what the user has done wrong, and how to rectify it
        } catch (TaskDescriptionException errorMessage) {
            System.out.print("Oi!!! Your " + errorMessage.getMessage() + " must have a description la...");
        } catch (TaskFieldException errorMessage) {
            System.out.print("walao eh... your task is missing " + errorMessage.getMessage()
                    + " " + "field(s) la..." + " follow the format can anot");
        } catch (InvalidFormatException errorMessage){
            System.out.print(errorMessage.getMessage());
        }
    }
}
