package Yukee.parser;

import Yukee.exception.InvalidCommandException;

public class Parser {
    public static String[] parse(String fullCommand) throws InvalidCommandException {
        String[] inputSplit = fullCommand.split(" ", 2);
        if (inputSplit.length == 0 || inputSplit[0].isEmpty()) {
            throw new InvalidCommandException();
        }
        return inputSplit;
    }
}
