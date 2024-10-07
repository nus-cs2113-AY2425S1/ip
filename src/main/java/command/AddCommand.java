package command;

import datahandling.Storage;
import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;
import task.TaskList;
import ui.UserInteraction;

public class AddCommand extends Command {
    private final String taskDescription;

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskDescription == null || taskDescription.isEmpty()) {
            ui.showMessage("Task description cannot be empty.");
            return;
        }

        String[] parts = taskDescription.split(" ", 2);
        String typeoftask = parts[0];
        String taskDetails = parts.length > 1 ? parts[1] : "";

        // Guard clause: Ensure taskDetails is provided
        if (taskDetails.isEmpty()) {
            ui.showMessage("The description of a " + typeoftask + " cannot be empty.");
            return;
        }

        Task newTask = createTask(typeoftask, taskDetails);
        if (newTask == null) {
            ui.showMessage("Invalid task type.");
            return;
        }

        tasks.addTask(newTask);
        ui.showMessage("Added: " + newTask);
        storage.saveTasksToFile(tasks.getTasks());
    }

    /**
     * Factory method to create a Task based on the task type.
     */
    private Task createTask(String typeoftask, String taskDetails) {
        switch (typeoftask) {
            case TODO:
                return new ToDo(taskDetails);
            case DEADLINE:
                return createDeadlineTask(taskDetails);
            case EVENT:
                return createEventTask(taskDetails);
            default:
                return null;
        }
    }

    private Task createDeadlineTask(String taskDetails) {
        String[] deadlineParts = taskDetails.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            return null;  // Invalid deadline format
        }
        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }

    private Task createEventTask(String taskDetails) {
        String[] eventParts = taskDetails.split(" /from | /to ", 3);
        if (eventParts.length < 3) {
            return null;  // Invalid event format
        }
        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }
}

