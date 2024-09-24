package jeff.command;

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


/**
 * Represents a Command to add tasks to the task list.
 * The <code>AddCommand</code> adds "todo", "deadline", or "event" tasks to the task list based on user input.
 */
public class AddCommand extends Command {

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int SLASH_BY_LENGTH = 3;
    private static final int SLASH_FROM_LENGTH = 5;
    private static final int SLASH_TO_LENGTH = 3;

    /**
     * Constructs an AddCommand with the specified params.
     *
     * @param firstWord The first word of the command (e.g., "todo", "deadline", "event").
     * @param line The full line of user input.
     */
    public AddCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    /**
     * Extracts and returns the description of a task from the user input.
     * If the start index is greater than the end index, sets the end index to the length of the line.
     * Returns an empty String if no valid description is in the input.
     *
     * @param line The full line of user input.
     * @param start The starting index of the description.
     * @param end The ending index of the description.
     * @return The processed task description.
     */
    private String processTaskDescription(String line , int start, int end){
        //If the task fields are empty, set the end number to the length of the line
        if(start > end){
            end = line.length();
        }
        //Remove the empty space before the description
        return line.substring(start, end).trim();
    }

    /**
     * Extracts and returns a task field from the user input.
     * Returns an empty string if the field is invalid.
     *
     * @param line The full line of user input.
     * @param fieldIndex The starting index of the field delimiter. It is -1 if it does not exist.
     * @param start The starting index of the field.
     * @param end The ending index of the field.
     * @return The processed task field or an empty string if invalid.
     */
    private String processTaskField(String line, int fieldIndex, int start, int end){
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

    /**
     * Creates a Todo object from the user input.
     *
     * @param line The full line of user input.
     * @return A Todo object with the provided description.
     * @throws TaskDescriptionException If the description is empty.
     */
    private Todo processTodo(String line) throws TaskDescriptionException {
        String description = processTaskDescription(line, TODO_LENGTH, line.length());
        if(description.isEmpty()) {
            throw new TaskDescriptionException("todo");
        }
        return new Todo(description);
    }

    /**
     * Creates a Deadline object from the user input.
     *
     * @param line The full line of user input.
     * @return A Deadline object with the provided description and "by" field.
     * @throws TaskDescriptionException If the description is empty.
     * @throws TaskFieldException If the "by" field is empty or missing.
     */
    private Deadline processDeadline(String line) throws TaskDescriptionException,
            TaskFieldException {
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

    /**
     * Constructs an exception message indicating missing fields for an event.
     *
     * @param from Indicates whether the "from" field is missing.
     * @param to Indicates whether the "to" field is missing.
     * @return A message indicating which fields are missing.
     */
    private String eventExceptionMessage(boolean from, boolean to){
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

    /**
     * Creates an Event object from the user input.
     *
     * @param line The full line of user input.
     * @return An Event object with the provided description, "from", and "to" fields.
     * @throws TaskDescriptionException If the description is empty.
     * @throws TaskFieldException If the "from" or "to" fields are empty or missing.
     */
    private Event processEvent(String line) throws TaskDescriptionException,
            TaskFieldException{
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

    /**
     * Executes the AddCommand, which processes the user input and adds a new task to the task list.
     * Saves the task to storage and provides feedback to the user via the UI.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui for user interaction.
     * @param storage The Storage object to handle saving tasks.
     * @throws IOException If an I/O error occurs when saving the task.
     */
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
        }
    }
}
