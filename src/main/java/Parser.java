public class Parser {
    public static Command parse(String input) throws PoirotException {
        String[] list_input = input.split(" ");
        switch (list_input[0]) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(list_input[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(list_input[1]));
            case "todo":
                return new AddTodoCommand(input.substring(5).trim());
            case "deadline":
                return parseDeadlineCommand(input);
            case "event":
                return parseEventCommand(input);
            case "delete":
                return new DeleteCommand(Integer.parseInt(list_input[1]));
            case "find":
                return new FindCommand(input);
            case "bye":
                return new ExitCommand();
            default:
                throw new PoirotException("Unknown command!");
        }
    }

    private static Command parseDeadlineCommand(String input) throws PoirotException {
        try {
            String[] parts = input.split("/by");
            if (parts.length < 2) {
                throw new PoirotException("Invalid deadline format! Use: deadline <description> /by <yyyy-mm-dd>");
            }
            String description = parts[0].trim().substring(9).trim();
            String by = parts[1].trim();
            return new AddDeadlineCommand(description + " /by " + by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PoirotException("Invalid format for deadline command.");
        }
    }

    private static Command parseEventCommand(String input) throws PoirotException {
        try {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new PoirotException("Invalid event format! Use: event <description> /from <start> /to <end>");
            }

            String[] fromParts = input.split("/from");
            if (fromParts.length < 2) {
                throw new PoirotException("Invalid event format! Use: event <description> /from <start> /to <end>");
            }

            String description = fromParts[0].trim().substring(6).trim();

            String[] toParts = fromParts[1].split("/to");
            if (toParts.length < 2) {
                throw new PoirotException("Invalid event format! Use: event <description> /from <start> /to <end>");
            }

            String from = toParts[0].trim();
            String to = toParts[1].trim();

            return new AddEventCommand(description, from, to);
        } catch (StringIndexOutOfBoundsException e) {
            throw new PoirotException("Error extracting event description. Please check your input format.");
        }
    }


}
