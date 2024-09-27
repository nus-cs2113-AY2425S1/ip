package king;

import king.command.*;

public class Parser {

    public static Command parse(String userInput) throws KingException {
        String parsedInput = userInput.trim().toLowerCase();
        userInput = userInput.trim();

        if (parsedInput.equals("bye")) {
            return new ExitCommand();
        } else if (parsedInput.equals("list")) {
            return new ListCommand();
        } else if (parsedInput.startsWith("mark")) {
            return new MarkCommand(parseTaskIndex(userInput));
        } else if (parsedInput.startsWith("unmark")) {
            return new UnmarkCommand(parseTaskIndex(userInput));
        } else if (parsedInput.startsWith("todo")) {
            return new TodoCommand(userInput);
        } else if (parsedInput.startsWith("deadline")) {
            return new DeadlineCommand(userInput);
        } else if (parsedInput.startsWith("event")) {
            return new EventCommand(userInput);
        } else if (parsedInput.startsWith("delete/exit")) {
            return new DeleteExitCommand();
        } else if (parsedInput.startsWith("delete")) {
            return new DeleteCommand(parseTaskIndex(userInput));
        } else {
            throw new KingException("Unknown command. Please try again.\n");
        }
    }

    protected static int parseTaskIndex(String text) throws KingException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new KingException("Your input can only be numbers!\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KingException("Have u forgotten to input the task number?\n");
        }

        return taskIndex;
    }
}
