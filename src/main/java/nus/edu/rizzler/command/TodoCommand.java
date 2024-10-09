package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "todo";

    private String taskName;

    /**
     * Constructs a {@code TodoCommand} with the specified task name.
     *
     * @param taskName The name of the todo task.
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Adds the todo task to the task list.
     *
     * @param tasks The task list to which the todo task will be added.
     * @return A string representation of the added task.
     */
    public String addTask(TaskList tasks) {
        Boolean isDone = false;
        return tasks.addTodo(taskName, isDone);
    }

    /**
     * Executes the command by adding the todo task to the task list,
     * displaying a confirmation message, and updating storage if needed.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying messages.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Let's make it happen!%s I've added this task:%n  %s%n" +
                "Now you have %d tasks in the list.", emoji.getFireEmoji(), taskString, taskSize);
        userInterface.displayMessage(message);
    }
}
