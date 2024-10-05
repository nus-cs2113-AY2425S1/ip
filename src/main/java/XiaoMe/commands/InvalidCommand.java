package XiaoMe.commands;

import XiaoMe.TaskList;

/**
 * Represents a command that is executed when the user input is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an InvalidCommand instance.
     */
    public InvalidCommand() {
        this.isExit = false;
    }

    /**
     * Executes the command and returns an error message for invalid input.
     *
     * @param tasks the list of tasks (not used in this command)
     * @return an error message indicating that the command is invalid
     */
    @Override
    public String execute(TaskList tasks) {
        return "Invalid command :(";
    }
}
