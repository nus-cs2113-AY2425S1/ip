package parser;

import commands.*;
import exception.IllegalCommandException;
import exception.IncompleteCommandException;
import exception.InvalidTaskContentException;

import static main.Sirius.*;

/**
 * The Parser class is responsible for interpreting user input and returning the corresponding Command object.
 * It does not have any instance variables, so all methods are static.
 */
public class Parser {

    /**
     * Parses the user's input and returns a corresponding Command object.
     *
     * @param userInput The full string of the user's input.
     * @return A Command object corresponding to the user input.
     * @throws InvalidTaskContentException If the input does not contain valid content for a task.
     * @throws IncompleteCommandException  If the command is incomplete or missing required arguments.
     * @throws IllegalCommandException     If the command does not match any known command prefix.
     */
    public static Command parse(String userInput) throws InvalidTaskContentException, IncompleteCommandException, IllegalCommandException {
        String[] commandPieces = userInput.split(" ");
        String commandPrefix = commandPieces[0];

        switch (commandPrefix) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(userInput, true);
            case UNMARK:
                return new MarkCommand(userInput, false);
            case TODO, DEADLINE, EVENT:
                return new AddCommand(userInput);
            case DELETE:
                return new DeleteCommand(userInput);
            case FIND:
                return new FindCommand(userInput);
            default:
                throw new IllegalCommandException(ILLEGAL_COMMAND_MESSAGE + "\n" + SEPARATOR);
        }
    }
}

