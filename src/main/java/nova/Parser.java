package nova;

import nova.command.*;

/**
 * Parses and processes user input commands.
 * The {@code Parser} directs commands to their respective command handlers
 * based on the input and manages task operations through the {@code TaskList}.
 */
public class Parser {

    /**
     * The task manager that handles task operations.
     */
    private TaskList taskManager;

    /**
     * Constructs a {@code Parser} with a task manager to handle user input commands.
     *
     * @param taskManager The task manager that manages the tasks.
     */
    public Parser(TaskList taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Handles user input by determining which command to execute based on the input string.
     * It matches the first element of the input array to a command and invokes the relevant
     * command execution method.
     *
     * @param inputs The array of input strings, where the first string indicates the command.
     * @return {@code true} if the command is an exit command, otherwise {@code false}.
     */
    public boolean handleInput(String[] inputs) {
        switch (inputs[0].toLowerCase()) {
        case ExitCommand.COMMAND_WORD:
            return ExitCommand.execute();

        case ListCommand.COMMAND_WORD:
            ListCommand.execute(inputs, taskManager);
            break;

        case MarkAndUnmarkCommand.COMMAND_WORD1:
        case MarkAndUnmarkCommand.COMMAND_WORD2:
            MarkAndUnmarkCommand.execute(inputs, taskManager);
            break;

        case TodoCommand.COMMAND_WORD:
            TodoCommand.execute(inputs, taskManager);
            break;

        case DeadlineCommand.COMMAND_WORD:
            DeadlineCommand.execute(inputs, taskManager);
            break;

        case EventCommand.COMMAND_WORD:
            EventCommand.execute(inputs, taskManager);
            break;

        case DeleteCommand.COMMAND_WORD:
            DeleteCommand.execute(inputs, taskManager);
            break;

        case FindCommand.COMMAND_WORD:
            FindCommand.execute(inputs, taskManager);
            break;

        default:
            Ui.displayInvalidInputMessage();
            break;
        }
        return false;
    }
}
