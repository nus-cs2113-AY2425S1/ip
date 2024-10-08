package parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import commands.*;
import tasks.*;
import ui.Ui;

public class Parser {
    public static Command parse(String input) throws DateTimeException {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
        case "list":
            return new ListCommand();
        case "mark":
            return prepMark(splitInput);
        case "unmark":
            return prepUnmark(splitInput);
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
            return prepSearch(splitInput, input);
        default:
            return new InvalidCommand(() -> new Ui().printInvalidCommandError());
        }
    }
    private static Command prepTodo(String input) {
        try {
            return new TodoCommand(new Todo(input.substring(!input.contains(" ") ? -1
                : input.indexOf(" ") + 1)));
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(() -> new Ui().printInvalidTaskError(Ui.Type.TODO));
        }
    }
    private static Command prepDeadline(String input) {
        try {
            return new DeadlineCommand(new Deadlines(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1),
                    LocalDate.parse(input.substring(input.indexOf("/by") + 4))));
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(() -> new Ui().printInvalidTaskError(Ui.Type.DEADLINE));
        } catch (DateTimeParseException e) {
            return new InvalidCommand(() -> new Ui().printDateError());
        }
    }

    private static Command prepEvent(String input) {
        try {
            return new EventCommand(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1),
                    LocalDate.parse(input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1)),
                    LocalDate.parse(input.substring(input.indexOf("/to") + 4))));
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(() -> new Ui().printInvalidTaskError(Ui.Type.EVENT));
        } catch (DateTimeParseException e) {
            return new InvalidCommand(() -> new Ui().printDateError());
        }
    }
    private static Command prepSearch(String[] splitInput, String input) {
        try {
            if (splitInput[1].equals("/c")) {
                return new SearchCommand(splitInput[1], input.substring(input.indexOf("/c") + 3));
            }
            return new SearchCommand(splitInput[1], LocalDate.parse(splitInput[2]));
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(() -> new Ui().printSearchError());
        } catch (DateTimeParseException e) {
            return new InvalidCommand(() -> new Ui().printDateError());
        }
    }
    private static Command prepMark(String[] splitInput) {
        try {
            return new MarkCommand(Integer.parseInt(splitInput[1]) - 1);
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(() -> new Ui().printMarkError(e));
        }
    }
    private static Command prepUnmark(String[] splitInput) {
        try {
            return new UnmarkCommand(Integer.parseInt(splitInput[1]) - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            return new InvalidCommand(() -> new Ui().printUnmarkError(e));
        }
    }
}
