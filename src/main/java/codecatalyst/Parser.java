package codecatalyst;

import codecatalyst.command.*;
import codecatalyst.exception.InvalidCommandException;

public class Parser {
    private static int TODO_LENGTH = 5;
    private static int DEADLINE_LENGTH = 9;
    private static int EVENT_LENGTH = 6;
    private static int MARK_LENGTH = 5;
    private static int UNMARK_LENGTH = 7;
    private static int DELETE_LENGTH = 7;
    private static int FIND_LENGTH = 5;

    public static Command parse(String fullCommand) throws Exception {
        String commandWord = getCommand(fullCommand);

        switch (commandWord) {
        case "todo":
            return new AddTodoCommand(fullCommand.substring(TODO_LENGTH));
        case "deadline":
            return new AddDeadlineCommand(fullCommand.substring(DEADLINE_LENGTH));
        case "event":
            return new AddEventCommand(fullCommand.substring(EVENT_LENGTH));
        case "mark":
            return new MarkCommand(fullCommand.substring(MARK_LENGTH));
        case "unmark":
            return new UnmarkCommand(fullCommand.substring(UNMARK_LENGTH));
        case "delete":
            return new DeleteCommand(fullCommand.substring(DELETE_LENGTH));
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(fullCommand.substring(FIND_LENGTH));
        default:
            throw new InvalidCommandException("Invalid Command");
        }
    }

    private static String getCommand(String fullCommand) {
        if (fullCommand.equals("bye")){
            return "bye";
        } else if (fullCommand.equals("list")) {
            return "list";
        } else if (fullCommand.startsWith("mark ")) {
            return "mark";
        } else if (fullCommand.startsWith("unmark ")) {
            return "unmark";
        } else if (fullCommand.startsWith("todo ")) {
            return "todo";
        } else if (fullCommand.startsWith("deadline ")) {
            return "deadline";
        } else if (fullCommand.startsWith("event ")) {
            return "event";
        } else if (fullCommand.startsWith("delete ")) {
            return "delete";
        } else if (fullCommand.startsWith("find ")) {
            return "find";
        }else {
            return "invalid command";
        }
    }


}
