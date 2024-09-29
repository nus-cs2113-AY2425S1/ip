package grok.parser;

import grok.commands.*;
import grok.GrokException;

public class Parser {
    public static Command parse(String input) throws GrokException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(words[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(words[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command, words[1]);
        default:
            throw new GrokException("I'm sorry, I don't understand that command.");
        }
    }
}
