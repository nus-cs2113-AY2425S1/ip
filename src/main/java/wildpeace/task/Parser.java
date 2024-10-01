package wildpeace.parser;

import wildpeace.exceptions.EmptyCommandException;
import wildpeace.exceptions.InvalidInputException;

public class Parser {

    public String[] parse(String input) throws EmptyCommandException {
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            throw new EmptyCommandException("Input cannot be empty.");
        }
        return trimmedInput.split("\\s+", 2);
    }

    public String getCommand(String[] parsedInput) {
        return parsedInput[0].toLowerCase();
    }

    public String getArguments(String[] parsedInput) {
        return parsedInput.length > 1 ? parsedInput[1] : "";
    }
}
