package jeff.helper;

import jeff.command.*;
import java.io.IOException;

public class Parser {
    //Returns the first word of a String
    private static String processLine(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace != -1) {
            return line.substring(0, firstSpace);
        } else {
            return line; // In case there's only one word with no spaces
        }
    }

    public static Command parse(String line) throws IOException {
        //Getting first word of user input
        String firstWord = processLine(line);

        //Carry out different actions based on the first word
        switch (firstWord) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(firstWord, line);
        case "list":
            return new ListCommand(firstWord, line);
        case "mark":
        case "unmark":
            return new MarkCommand(firstWord, line);
        case "delete":
            return new DeleteCommand(firstWord, line);
        case "find":
            return new FindCommand(firstWord, line);
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
