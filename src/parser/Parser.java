package parser;

import commands.*;
import exception.IllegalCommandException;
import exception.IncompleteCommandException;
import exception.InvalidTaskContentException;

import static main.Sirius.*;

public class Parser {
    // Parser has no instance，so using static
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

