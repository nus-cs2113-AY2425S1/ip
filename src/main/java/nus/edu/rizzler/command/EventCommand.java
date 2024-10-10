package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Face;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private Face face = new Face();
    public static final String COMMAND_WORD = "event";

    private String taskName;
    private String from;
    private String to;

    /**
     * Constructs an {@code EventCommand} with the specified task name, start time, and end time.
     *
     * @param taskName The name of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the event task to the task list.
     *
     * @param tasks The task list to which the event task will be added.
     * @return A string representation of the added task.
     */
    protected String addTask(TaskList tasks) {
        Boolean isDone = false;
        return tasks.addEvent(taskName, isDone, from, to);
    }

    /**
     * Executes the command by adding the event task to the task list,
     * displaying a confirmation message, and updating storage if needed.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Let's make it happen %s! I've added this task:%n  %s%n" +
                "Now you have %d tasks in the list.", face.getBlushingFace(), taskString, taskSize);
        userInterface.displayMessage(message);
    }
}

