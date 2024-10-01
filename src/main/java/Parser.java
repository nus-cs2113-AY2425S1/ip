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
                return new AddDeadlineCommand(input);
            case "event":
                return new AddEventCommand(input);
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
}
