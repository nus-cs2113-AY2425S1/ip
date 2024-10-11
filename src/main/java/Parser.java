public class Parser {
    public String[] parseInput(String input) {
        return input.split(" ", 2);
    }

    public int parseTaskNumber(String argument) throws KBotException {
        try {
            return Integer.parseInt(argument) - 1; // Adjust for zero-based index
        } catch (NumberFormatException e) {
            throw new KBotException("Invalid task number.");
        }
    }

    public void validateDeadlineArgument(String argument) throws KBotException {
        if (!argument.contains("/by ")) {
            throw new KBotException("The deadline description or date is missing.");
        }
    }

    public void validateEventArgument(String argument) throws KBotException {
        if (!argument.contains("/from ") || !argument.contains("/to ")) {
            throw new KBotException("The event description or timing is missing.");
        }
    }
}
