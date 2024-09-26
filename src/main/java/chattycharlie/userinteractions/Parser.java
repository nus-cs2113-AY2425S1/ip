package chattycharlie.userinteractions;

import chattycharlie.commands.CommandType;

public class Parser {
    public CommandType getCommand(String input) {
        String firstWord = input.split(" ")[0];
        return CommandType.valueOf(firstWord.toUpperCase());
    }
}
