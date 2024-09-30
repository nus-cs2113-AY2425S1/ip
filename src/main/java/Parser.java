public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_DELETE = "delete";

    public static Command parse(String fullCommand) throws BebeException {
        try {
            String[] words = fullCommand.split(" ", 2);
            String command = words[0].toLowerCase();

            return switch (command) {
                case COMMAND_BYE -> new ExitCommand();
                case COMMAND_LIST -> new ListCommand();
                case COMMAND_MARK -> {
                    if (words.length < 2) throw new BebeException("The 'mark' command requires an index.");
                    yield new MarkCommand(words[1], true);
                }
                case COMMAND_UNMARK -> {
                    if (words.length < 2) throw new BebeException("The 'unmark' command requires an index.");
                    yield new MarkCommand(words[1], false);
                }
                case COMMAND_TODO -> {
                    if (words.length < 2) throw new BebeException("The 'todo' command requires a description.");
                    yield new AddCommand("todo", words[1]);
                }
                case COMMAND_DEADLINE -> {
                    if (words.length < 2) throw new BebeException("The 'deadline' command requires a description.");
                    yield new AddCommand("deadline", words[1]);
                }
                case COMMAND_EVENT -> {
                    if (words.length < 2) throw new BebeException("The 'event' command requires a description.");
                    yield new AddCommand("event", words[1]);
                }
                case COMMAND_DELETE -> {
                    if (words.length < 2) throw new BebeException("The 'delete' command requires an index.");
                    yield new DeleteCommand(words[1]);
                }
                case COMMAND_HELP -> new HelpCommand();
                default -> throw new BebeException("Unknown command: " + command);
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BebeException("Invalid input. Please check your command format.");
        }
    }
}