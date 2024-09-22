package nova;

import nova.command.*;

public class Parser {

    private TaskList taskManager;

    public Parser(TaskList taskManager) {
        this.taskManager = taskManager;
    }

    public boolean handleInput(String[] inputs) {
        switch (inputs[0].toLowerCase()) {
        case ExitCommand.COMMAND_WORD:
            return ExitCommand.execute();

        case ListCommand.COMMAND_WORD:
            ListCommand.execute(taskManager);
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
