package commands;

import task.TaskList;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import UI.Ui;
import storage.Storage;
import exception.LiaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private final String taskDetails;

    /**
     * Constructs an AddCommand with the specified task details.
     *
     * @param taskDetails The details of the task to be added.
     */
    public AddCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface for interactions.
     * @param storage The storage for saving and loading tasks.
     * @throws LiaException if an error occurs during task creation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LiaException {
        Task newTask;

        if (taskDetails.startsWith("todo")) {
            newTask = new ToDo(taskDetails.substring(5).trim());
        } else if (taskDetails.startsWith("deadline")) {
            String[] details = taskDetails.split(" /by ");
            LocalDateTime by = parseDateTime(details[1].trim());
            newTask = new Deadline(details[0].substring(9).trim(), by);
        } else if (taskDetails.startsWith("event")) {
            String[] details = taskDetails.split(" /from ");
            String[] timeDetails = details[1].split(" /to ");
            LocalDateTime start = parseDateTime(timeDetails[0].trim());
            LocalDateTime end = parseDateTime(timeDetails[1].trim());
            newTask = new Event(details[0].substring(6).trim(), start, end);
        } else {
            throw new LiaException("Invalid task type.");
        }

        tasks.add(newTask);
        ui.addTaskAndPrint(newTask, tasks.size());
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to parse.
     * @return The corresponding LocalDateTime object.
     * @throws LiaException if the date/time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws LiaException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (Exception e) {
            throw new LiaException("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }
}
