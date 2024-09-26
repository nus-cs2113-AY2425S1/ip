package bot;

public class Parser {
    public static Command parse(String fullCommand) throws TobyBotException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(parts[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(parts[1]));
            case "todo":
                return new AddCommand("todo", parts[1]);
            case "deadline":
                return new AddCommand("deadline", parts[1]);
            case "event":
                return new AddCommand("event", parts[1]);
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1]));
            default:
                throw new TobyBotException("Unknown command");
        }
    }
}