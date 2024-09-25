package jeremy.util;

import jeremy.command.Command;
import jeremy.exception.IllegalCommandException;

public class Parser {
    public static Command command(String userInput) throws IllegalCommandException {
        String[] parts = userInput.split(" ", 2);
        return Command.fromString(parts[0]);
    }

    public static String argument(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }
}
