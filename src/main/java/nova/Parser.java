package nova;

import nova.command.ListCommand;
import nova.command.MarkCommand;
import nova.command.TodoCommand;
import nova.command.DeadlineCommand;
import nova.command.DeleteCommand;
import nova.command.EventCommand;
import nova.command.FindCommand;
import nova.command.UnmarkCommand;

/**
 * Parses user input.
 */
public class Parser {

    /** The task manager that handles task operations. */
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
     * Parses user input and execute it.
     *
     * @param inputs The array of input strings, where the first string indicates the command.
     */
    public void handleInput(String[] inputs) {
        switch (inputs[0].toLowerCase()) {
        case ListCommand.COMMAND_WORD:
            ListCommand.execute(inputs, taskManager);
            break;

        case MarkCommand.COMMAND_WORD:
            MarkCommand.execute(inputs, taskManager);
            break;

        case UnmarkCommand.COMMAND_WORD:
            UnmarkCommand.execute(inputs, taskManager);
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
    }
}
