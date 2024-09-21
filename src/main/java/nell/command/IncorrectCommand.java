package nell.command;

import nell.TaskList;
import nell.common.Messages;

/**
 * Represents an incorrect command
 */
public class IncorrectCommand extends Command{
    /**
     * Constructs a new IncorrectCommand object with a specified task list
     *
     * @param tasks The specified task list
     */
    public IncorrectCommand(TaskList tasks) {
        super("incorrect", tasks);
    }

    /**
     * Executes command - informs user that input command is invalid
     */
    public void execute() {
        System.out.print(Messages.INVALID_COMMAND_MESSAGE);
    }
}
