package nell.command;

import nell.list.TaskList;
import nell.common.Messages;

/**
 * Represents an incorrect command
 */
public class IncorrectCommand extends Command {
    private String message;

    /**
     * Constructs a new IncorrectCommand object with a specified task list
     *
     * @param tasks The specified task list
     */
    public IncorrectCommand(TaskList tasks) {
        super(tasks);
        this.message = Messages.INVALID_COMMAND_MESSAGE;
    }

    /**
     * Constructs a new IncorrectCommand object with a specified task list and message
     *
     * @param tasks The specified task list
     * @param message The specified message
     */
    public IncorrectCommand(TaskList tasks, String message) {
        super(tasks);
        this.message = message;
    }

    /**
     * Executes command - prints message to user
     */
    @Override
    public void execute() {
        System.out.print(message);
    }
}
