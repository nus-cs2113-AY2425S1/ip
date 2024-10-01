public class Parser {

    public String getCommand(String line) throws EvaException {
        String command = line.split(" ")[0].toLowerCase();

        switch (command) {
        case "list":
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "find":
        case "bye":
            return command;
        default:
            throw new EvaException("Sorry! I don't understand what '" + line + "' means.");
        }
    }

    public String[] parseArguments(String line, String command) throws EvaException {
        String[] args;
        switch (command) {
        case "mark":
        case "unmark":
        case "delete":
            String[] parts = line.split(" ");
            if (parts.length < 2) {
                throw new EvaException("Oops! You need to provide a task number after the command '" + parts[0] + "'.");
            }
            args = new String[]{parts[1]};
            break;
        case "todo":
            String todoDesc = line.replaceFirst("todo", "").trim();
            if (todoDesc.isEmpty()) {
                throw new EvaException("Oops! The description of a todo cannot be empty. Please provide a task description.");
            }
            args = new String[]{todoDesc};
            break;
        case "deadline":
            String[] deadlineParts = line.replaceFirst("deadline", "").split("/by");
            if (deadlineParts.length < 2) {
                throw new EvaException("Oh no! The deadline command must have a description and a by time." +
                        "\nIt show be in this format: deadline (name of task) /by (time)." +
                        "\nPlease try again!");
            }
            args = new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()};
            break;
        case "event":
            String[] eventParts = line.replaceFirst("event", "").split("/from|/to");
            if (eventParts.length < 3) {
                throw new EvaException("Oh no! The event command must have a description, a from time, " +
                        "and a to time. \nThe format should be event (name of task) /from (time) /to (time)" +
                        "\nPlease try again!");
            }
            args = new String[]{eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()};
            break;
        case "find":
            String subject = line.replaceFirst("find", "").trim();
            if (subject.isEmpty()) {
                throw new EvaException("Oops! The description of a todo cannot be empty. Please provide a task description.");
            }
            args = new String[]{subject};
            break;
        default:
            args = new String[]{};
        }
        return args;
    }
}
