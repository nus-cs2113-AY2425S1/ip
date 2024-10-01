package apsea.parser;

import apsea.command.DeleteTaskCommand;
import apsea.command.ExitCommand;
import apsea.command.Command;
import apsea.command.AddTodoCommand;
import apsea.command.AddDeadlineCommand;
import apsea.command.AddEventCommand;
import apsea.command.FindCommand;
import apsea.command.ListCommand;
import apsea.command.MarkTaskCommand;
import apsea.command.UnmarkTaskCommand;
import apsea.exception.ApseaException;

public class Parser {
    private final String BYE = "bye";
    private final String LIST = "list";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String MARK = "mark";
    private final String UNMARK = "unmark";
    private final String DELETE = "delete";
    private final String FIND = "find";

    private final String INVALID_COMMAND_MESSAGE = "\tSorry, please try again: \n"
            + "\t - list: to list all tasks \n"
            + "\t - todo [task name]: to add a todo\n"
            + "\t - deadline [task name] /by [time]: to add a deadline\n"
            + "\t - event [task name] /from [time] /to [time]: to add a event\n"
            + "\t - mark [task number]: to mark a task\n"
            + "\t - unmark [task number]: to unmark a task\n"
            + "\t - delete [task number]: to delete a task\n"
            + "\t - find [query]: to find a task\n"
            + "\t - bye: to save and close Apsea";

    public Parser() {
    }

    public Command parse(String fullCommand) throws ApseaException {
        String[] words = fullCommand.split(" ");
        switch (words[0].toLowerCase()) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return new AddTodoCommand(fullCommand);
        case DEADLINE:
            return new AddDeadlineCommand(fullCommand);
        case EVENT:
            return new AddEventCommand(fullCommand);
        case MARK:
            return new MarkTaskCommand(words);
        case UNMARK:
            return new UnmarkTaskCommand(words);
        case DELETE:
            return new DeleteTaskCommand(words);
        case FIND:
            return new FindCommand(fullCommand);
        default:
            throw new ApseaException(INVALID_COMMAND_MESSAGE);
        }
    }

}
