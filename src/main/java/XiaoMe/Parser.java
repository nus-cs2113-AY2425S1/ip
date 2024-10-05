package XiaoMe;

import XiaoMe.commands.*;

import java.util.Objects;

/**
 * The Parser class is responsible for interpreting user input
 * and creating corresponding command objects.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * If the command is unrecognized, an InvalidCommand object is returned.
     *
     * @param userInput the input string from the user
     * @return the corresponding Command object based on user input
     */
    public static Command parseCommand(String userInput) {
        // checks what kind of command was received by XiaoMe

        if (Objects.equals(userInput, "bye")) {
            return new ByeCommand();
        } else if (Objects.equals(userInput, "list")) {
            return new ListCommand();
        }

        String[] words = userInput.trim().split(" ");
        String first = words[0];

        if (Objects.equals(first, "todo")) {
            return new ToDoCommand(userInput);
        } else if (Objects.equals(first, "deadline")) {
            return new DeadlineCommand(userInput);
        } else if (Objects.equals(first, "event")) {
            return new EventCommand(userInput);
        } else if (Objects.equals(first, "mark") || Objects.equals(first, "unmark")) {
            return new MarkCommand(userInput);
        } else if (Objects.equals(first, "find")) {
            return new FindCommand(userInput);
        } else if (Objects.equals(first, "delete")) {
            return new DeleteCommand(userInput);
        }

        return new InvalidCommand();
    }

}
