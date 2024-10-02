package parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import commands.*;
import tasks.*;
import ui.Skeleton;

public class Parser {

    public static Command parse(String input) throws DateTimeException {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(splitInput[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(splitInput[1]) - 1);
        case "bye":
            return new ExitCommand();
        case "todo":
            return prepTodo(input);
        case "deadline":
            return prepDeadline(input);
        case "event":
            return prepEvent(input);
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitInput[1]) - 1);
        case "search":
            return prepSearch(splitInput);
        default:
            return new InvalidCommand();
        }
    }
    private static Command prepTodo(String input) {
        return new TodoCommand(new Todo(input.substring(!input.contains(" ") ? -1
                : input.indexOf(" ") + 1)));
    }
    private static Command prepDeadline(String input) {
        return new DeadlineCommand(new Deadlines(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1),
                LocalDate.parse(input.substring(input.indexOf("/by") + 4))));
    }

    private static Command prepEvent(String input) {
        return new EventCommand(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1),
                LocalDate.parse(input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1)),
                LocalDate.parse(input.substring(input.indexOf("/to") + 4))));
    }
    private static Command prepSearch(String[] splitInput) {
        return new SearchCommand(splitInput[1], LocalDate.parse(splitInput[2]));
    }
}
