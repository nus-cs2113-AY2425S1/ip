package xiaome;

import xiaome.commands.ByeCommand;
import xiaome.commands.Command;
import xiaome.commands.DeadlineCommand;
import xiaome.commands.DeleteCommand;
import xiaome.commands.EventCommand;
import xiaome.commands.FindCommand;
import xiaome.commands.InvalidCommand;
import xiaome.commands.ListCommand;
import xiaome.commands.MarkCommand;
import xiaome.commands.ToDoCommand;

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

        switch(first) {
        case "todo":
            return new ToDoCommand(userInput);
        case "deadline":
            return new DeadlineCommand(userInput);
        case "event":
            return new EventCommand(userInput);
        case "mark":
            //Fallthrough
        case "unmark":
            return new MarkCommand(userInput);
        case "find":
            return new FindCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        default:
            return new InvalidCommand();
        }
    }
}
