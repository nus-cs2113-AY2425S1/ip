package command;

import datahandling.Storage;
import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;
import task.TaskList;
import ui.UserInteraction;
import java.time.format.DateTimeParseException;

/**
 * Command to add tasks (ToDo, Deadline, or Event) to task list.
 * Parses task type and details, create defined task, and adds it to list.
 */
public class AddCommand extends Command {
    private final String taskDescription;

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    /**
     * Constructs AddCommand with defined task description.
     * @param taskDescription represents the description of the task to add to list
     */
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes AddCommand by parsing task description and creating Task object.
     * Task added to Tasklist, and a confirmation message will be displayed.
     * @param tasks task list which task will be added to
     * @param ui User Interaction object for displaying messages
     * @param storage Storage handler used to save updated task list
     * @throws Exception if issue face during execution of command
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskDescription == null || taskDescription.isEmpty()) {
            ui.showMessage("Task description cannot be empty.");
            return;
        }

        String[] parts = taskDescription.split(" ", 2);
        String typeoftask = parts[0];
        String taskDetails = parts.length > 1 ? parts[1] : "";

        if (taskDetails.isEmpty()) {
            ui.showMessage("The description of a " + typeoftask + " cannot be empty.");
            return;
        }

        Task newTask = createTask(typeoftask, taskDetails, ui);
        if (newTask == null) {
            ui.showMessage("Invalid task type.");
            return;
        }

        tasks.addTask(newTask);
        ui.showMessage("Added: " + newTask);
        storage.saveTasksToFile(tasks.getTasks());
    }

    /**
     * Create Task object based on defined Task type.
     * @param typeoftask type of task (todo, deadline, event)
     * @param taskDetails description of task
     * @param ui user interaction object for displaying messages
     * @return new Task object created, else task type is invalid
     */
    private Task createTask(String typeoftask, String taskDetails, UserInteraction ui) {
        switch (typeoftask) {
            case TODO:
                return new ToDo(taskDetails);
            case DEADLINE:
                return createDeadlineTask(taskDetails, ui);
            case EVENT:
                return createEventTask(taskDetails, ui);
            default:
                ui.showMessage("Invalid task type.");
                return null;
        }
    }

    /**
     * Create deadline task
     * @param taskDetails description of deadline task
     * @param ui user interaction object for displauing messages
     * @return new deadline task created, or null if invalid
     */
    private Task createDeadlineTask(String taskDetails, UserInteraction ui) {
        String[] deadlineParts = taskDetails.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            ui.showMessage("Invalid deadline format. Use: deadline <task> /by <date>.");
            return null;
        }
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1]);
        } catch (DateTimeParseException e) {
            ui.showMessage("Invalid date format for deadline. Use: yyyy-MM-dd HHmm.");
            return null;
        }
    }

    /**
     * Create event task
     * @param taskDetails description of event and date-time information
     * @param ui user interaction object for displaying messages
     * @return new event task created, or null if invalid
     */
    private Task createEventTask(String taskDetails, UserInteraction ui) {
        String[] eventParts = taskDetails.split(" /from | /to ", 3);
        if (eventParts.length < 3) {
            ui.showMessage("Invalid event format. Use: event <task> /from <start> /to <end>.");
            return null;
        }
        try {
            return new Event(eventParts[0], eventParts[1], eventParts[2]);
        } catch (DateTimeParseException e) {
            ui.showMessage("Invalid date format for event. Use: yyyy-MM-dd HHmm.");
            return null;
        }
    }
}

