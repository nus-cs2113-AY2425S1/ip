package XiaoMe.commands;

import XiaoMe.TaskList;

/**
 * Represents a command to exit the application.
 * The {@code ByeCommand} is used when the user wants to terminate the application.
 * It always returns a success message and sets the exit flag.
 */
public class ByeCommand extends Command {

    public final String MESSAGE_SUCCESS = "\tBye. Hope to see you again soon!";

    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Executes the {@code ByeCommand}, signaling the end of the application.
     *
     * @param tasks the list of tasks (not used in this command)
     * @return a goodbye message
     */
    @Override
    public String execute(TaskList tasks) {
        return MESSAGE_SUCCESS;
    }

}
