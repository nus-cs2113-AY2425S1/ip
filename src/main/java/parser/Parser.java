package parser;

import commands.*;
import tasks.*;

/**
 * The Parser class is responsible for parsing user input and returning the appropriate
 * Command object based on the input. It contains static methods to handle specific
 * command preparations such as tasks like ToDos, Deadlines, and Events.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     * The input is split into individual parts, and the first part (the command keyword)
     * determines which Command subclass is returned.
     *
     * @param input the user input string to be parsed
     * @return the corresponding Command object
     */
    public static Command parse(String input) {
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
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Prepares and returns a TodoCommand object from the user input.
     * It extracts the task description from the input and creates a Todo object.
     *
     * @param input the user input string for the "todo" command
     * @return a TodoCommand object
     */
    private static Command prepTodo(String input) {
        return new TodoCommand(new Todo(input.substring(!input.contains(" ") ? -1
                : input.indexOf(" ") + 1)));
    }

    /**
     * Prepares and returns a DeadlineCommand object from the user input.
     * It extracts the task description and deadline from the input and creates a Deadlines object.
     *
     * @param input the user input string for the "deadline" command
     * @return a DeadlineCommand object
     */
    private static Command prepDeadline(String input) {
        return new DeadlineCommand(new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1),
                input.substring(input.indexOf("/by") + 4)));
    }

    /**
     * Prepares and returns an EventCommand object from the user input.
     * It extracts the event description, start time, and end time from the input
     * and creates an Event object.
     *
     * @param input the user input string for the "event" command
     * @return an EventCommand object
     */
    private static Command prepEvent(String input) {
        return new EventCommand(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1),
                input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1),
                input.substring(input.indexOf("/to") + 4)));
    }
}
