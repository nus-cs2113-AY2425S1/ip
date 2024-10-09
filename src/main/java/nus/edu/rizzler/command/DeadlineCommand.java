package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "deadline";

    private String taskName;
    private String by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task name and due date.
     *
     * @param taskName The name of the deadline task.
     * @param by The due date for the task.
     */
    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * Adds the deadline task to the task list.
     *
     * @param tasks The task list to which the deadline task will be added.
     * @return A string representation of the added task.
     */
    protected String addTask(TaskList tasks) {
        Boolean isDone = false;
        return tasks.addDeadline(taskName, isDone, by);
    }

    /**
     * Executes the command by adding the deadline task to the task list,
     * displaying a confirmation message, and updating storage if needed.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Let's make it happen!%s I've added this task:%n  %s%n" +
                "Now you have %d tasks in the list.", emoji.getFireEmoji(), taskString, taskSize);
        userInterface.displayMessage(message);
    }
}

