/**
 * Parses user input into a command and returns the corresponding Command object.
 * Supports commands such as add, delete, mark, unmark, list, find, and exit.
 */
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
    private static final String COMMAND_FIND = "find";

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param fullCommand The full string input entered by the user.
     * @return The corresponding Command object based on the user's input.
     * @throws BebeException if the input is invalid or the command is unknown.
     */
    public static Command parse(String fullCommand) throws BebeException {
        try {
            String[] words = fullCommand.split(" ", 2);
            String command = words[0].toLowerCase();

            return switch (command) {
                case COMMAND_BYE -> new ExitCommand();
                case COMMAND_LIST -> new ListCommand();
                case COMMAND_MARK -> {
                    if (words.length < 2 || !isNumeric(words[1])) {
                        throw new BebeException("The 'mark' command requires a valid task number.");
                    }
                    yield new MarkCommand(words[1], true);
                }
                case COMMAND_UNMARK -> {
                    if (words.length < 2 || !isNumeric(words[1])) {
                        throw new BebeException("The 'unmark' command requires a valid task number.");
                    }
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
                    if (words.length < 2 || !isNumeric(words[1])) {
                        throw new BebeException("The 'delete' command requires a valid task number.");
                    }
                    yield new DeleteCommand(words[1]);
                }
                case COMMAND_HELP -> new HelpCommand();
                case COMMAND_FIND -> {
                    if (words.length < 2) throw new BebeException("The 'find' command requires a keyword.");
                    yield new FindCommand(words[1]);
                }
                default -> throw new BebeException("Unknown command: " + command);
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BebeException("Invalid input. Please check your command format.");
        }
    }

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}