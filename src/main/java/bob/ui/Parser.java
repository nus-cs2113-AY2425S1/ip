package bob.ui;
import bob.command.Command;
import bob.command.AddTodoCommand;
import bob.command.AddDeadlineCommand;
import bob.command.AddEventCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.command.DeleteCommand;
import bob.command.ListCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;

public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_FIND = "find";
    private static final Ui UI = new Ui();

    public static Command parse(String fullCommand) {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandWord = commandParts[0];

        try {
            switch (commandWord) {
            case COMMAND_TODO:
                return new AddTodoCommand(fullCommand);
            case COMMAND_DEADLINE:
                return new AddDeadlineCommand(fullCommand);
            case COMMAND_EVENT:
                return new AddEventCommand(fullCommand);
            case COMMAND_MARK:
                return new MarkCommand(Integer.parseInt(commandParts[1]) - 1);
            case COMMAND_UNMARK:
                return new UnmarkCommand(Integer.parseInt(commandParts[1]) - 1);
            case COMMAND_DELETE:
                return new DeleteCommand(Integer.parseInt(commandParts[1]) - 1);
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_FIND:
                return new FindCommand(commandParts[1]);
            case COMMAND_BYE:
                return new ExitCommand();
            default:
                UI.printInvalidCommand();
            }
        } catch (NumberFormatException e) {
            UI.printInvalidInputTypeMessage();
        }
        return null;
    }
}

