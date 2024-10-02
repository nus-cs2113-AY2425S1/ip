package atom;

public class Parser {

    public static Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new MarkCommand(words, command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(words, fullCommand, command);
        case "delete":
            return new DeleteCommand(words);
        default:
            return new InvalidCommand();
        }
    }
}
