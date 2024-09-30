public class Parser {

    public static Command parse(String fullCommand) throws BebeException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0].toLowerCase();
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(words[1], true);
            case "unmark":
                return new MarkCommand(words[1], false);
            case "todo":
                return new AddCommand("todo", words[1]);
            case "deadline":
                return new AddCommand("deadline", words[1]);
            case "event":
                return new AddCommand("event", words[1]);
            case "delete":
                return new DeleteCommand(words[1]);
            case "help":
                return new HelpCommand();
            default:
                throw new BebeException("Unknown command.");
        }
    }
}
