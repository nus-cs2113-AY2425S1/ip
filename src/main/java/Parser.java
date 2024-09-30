public class Parser {

    public static Command parse(String fullCommand) throws BebeException {
        try {
            String[] words = fullCommand.split(" ", 2);
            String command = words[0].toLowerCase();

            return switch (command) {
                case "bye" -> new ExitCommand();
                case "list" -> new ListCommand();
                case "mark" -> {
                    if (words.length < 2) throw new BebeException("The 'mark' command requires an index.");
                    yield new MarkCommand(words[1], true);
                }
                case "unmark" -> {
                    if (words.length < 2) throw new BebeException("The 'unmark' command requires an index.");
                    yield new MarkCommand(words[1], false);
                }
                case "todo" -> {
                    if (words.length < 2) throw new BebeException("The 'todo' command requires a description.");
                    yield new AddCommand("todo", words[1]);
                }
                case "deadline" -> {
                    if (words.length < 2) throw new BebeException("The 'deadline' command requires a description.");
                    yield new AddCommand("deadline", words[1]);
                }
                case "event" -> {
                    if (words.length < 2) throw new BebeException("The 'event' command requires a description.");
                    yield new AddCommand("event", words[1]);
                }
                case "delete" -> {
                    if (words.length < 2) throw new BebeException("The 'delete' command requires an index.");
                    yield new DeleteCommand(words[1]);
                }
                case "help" -> new HelpCommand();
                default -> throw new BebeException("Unknown command: " + command);
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BebeException("Invalid input. Please check your command format.");
        }
    }
}