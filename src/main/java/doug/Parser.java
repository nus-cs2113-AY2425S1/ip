package doug;

import doug.Commands.*;
import doug.Main.DougException;

/**
 * Class that breaks down the input string from the user
 * Parses and interprets the user input and calls the appropriate command classes
 */
public class Parser {

    /**
     * Deduces which command type has been input by the user
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A command object matching the command type entered by the user
     * @throws DougException If the command entered by user is not of a proper existing format
     */
    public static Command extractCommandType(TaskList tasks, String command, UI ui) throws DougException {
        Command c = new Command();
        if (command.equals("bye")) {
            c.setBye();
        } else if (command.equals("list")) {
            c = new ListCommand();
        } else if (command.equals("help")) {
            c = new HelpCommand();
        } else if (command.startsWith("mark")) {
            c = parseMark(tasks, command, ui);
        } else if (command.startsWith("unmark")) {
            c = parseUnmark(tasks, command, ui);
        } else if (command.startsWith("todo")) {
            c = parseToDo(tasks, command, ui);
        } else if (command.startsWith("deadline")) {
            c = parseDeadline(tasks, command, ui);
        } else if (command.startsWith("event")) {
            c = parseEvent(tasks, command, ui);
        } else if (command.startsWith("delete")) {
            c = parseDelete(tasks, command, ui);
        } else if (command.startsWith("find")) {
            c = parseFind(tasks, command, ui);
        } else {
            throw new DougException(ui.getDashedLine() + "Something seems off partner...\n" + ui.getDashedLine());
        }
        return c;
    }

    /**
     * Parses the user's input to obtain details of the todo task to be added
     * Creates and returns a ToDoCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A ToDoCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the name/description of the task
     */
    public static Command parseToDo(TaskList tasks, String command, UI ui) throws DougException{
        String todoName = command.replaceFirst("todo", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        if (todoName.isEmpty()) {
            throw new DougException(ui.getDashedLine()
                    + "Didn't name your todo task did you bud?\n"
                    + ui.getDashedLine());
        }
        return new AddToDoCommand(todoName);
    }

    /**
     * Parses the user's input to obtain details of the deadline task to be added
     * Creates and returns a DeadlineCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A DeadlineCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the name or deadline time/date of the task
     */
    public static Command parseDeadline(TaskList tasks, String command, UI ui) throws DougException{
        command = command.replaceFirst("deadline", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        if (!command.contains("/by")) {
            throw new DougException(ui.getDashedLine()
                    + "You're missing the \"/by\" keyword bud.\n"
                    + ui.getDashedLine());
        }

        int indexOfSlash = command.indexOf("/by");
        String deadlineName = command.substring(0, indexOfSlash).trim();
        if (deadlineName.isEmpty()) {
            throw new DougException(ui.getDashedLine()
                    + "Didn't name your deadline task did you bud?\n"
                    + ui.getDashedLine());
        }

        command = command.replaceFirst(deadlineName, "");
        String deadlineBy = command.replaceFirst("/by", "").trim();
        if (deadlineBy.isEmpty()) {
            throw new DougException(ui.getDashedLine()
                    + "Forgot to mention when it's due by, huh bud?\n"
                    + ui.getDashedLine());
        }
        return new AddDeadlineCommand(deadlineName, deadlineBy);
    }

    /**
     * Parses the user's input to obtain details of the event task to be added
     * Creates and returns an EventCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return An EventCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the name or start/end time/date of the task
     */
    public static Command parseEvent(TaskList tasks, String command, UI ui) throws DougException {
        command = command.replaceFirst("event", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        if (!command.contains("/from")) {
            throw new DougException(ui.getDashedLine()
                    + "You're missing the \"/from\" keyword bud.\n"
                    + ui.getDashedLine());
        }
        if (!command.contains("/to")) {
            throw new DougException(ui.getDashedLine()
                    + "You're missing the \"/to\" keyword bud.\n"
                    + ui.getDashedLine());
        }

        // check to see if the /from comes before the /to input parameter
        int indexOfFrom = command.indexOf("/from");
        int indexOfTo = command.indexOf("/to");
        if (indexOfFrom >= indexOfTo) {
            throw new DougException(ui.getDashedLine()
                    + "You're gotten the order of the \"/from\" and \"/to\" all mixed up bud.\n"
                    + ui.getDashedLine());
        }

        int indexOfFirstSlash = command.indexOf("/from");
        String eventName = command.substring(0, indexOfFirstSlash).trim();
        if (eventName.isEmpty()) {
            throw new DougException(ui.getDashedLine() + "Didn't name your event did you bud?\n" + ui.getDashedLine());
        }

        command = command.replaceFirst(eventName, "");
        command = command.replaceFirst("/from", "").trim();
        int indexOfSecondSlash = command.indexOf("/to");
        String eventFrom = command.substring(0, indexOfSecondSlash).trim();
        if (eventFrom.isEmpty()) {
            throw new DougException(ui.getDashedLine() + "Forgot to mention when " + eventName
                    + " starts, huh bud?\n" + ui.getDashedLine());
        }

        command = command.replaceFirst(eventFrom, "");
        String eventTo = command.replaceFirst("/to", "").trim();
        if (eventTo.isEmpty()) {
            throw new DougException(ui.getDashedLine() + "Forgot to mention when " + eventName
                    + " ends, huh bud?\n" + ui.getDashedLine());
        }

        return new AddEventCommand(eventName, eventFrom, eventTo);
    }


    /**
     * Parses the user's input to obtain details of the delete task to be added
     * Creates and returns a DeleteCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A DeleteCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the index of the task to delete
     */
    public static Command parseDelete(TaskList tasks, String command, UI ui) throws DougException {
        if (tasks.getCount() <= 0) {
            System.out.println(ui.getDashedLine()
                    + "Your list is empty pal, there ain't nothing to rid off.\n"
                    + ui.getDashedLine());
            return new Command();
        }

        command = command.replace("delete", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(ui.getMissingIndexMessage());
            return new Command();
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            return new DeleteCommand(listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
        return new Command();
    }

    /**
     * Parses the user's input to obtain details of the task to be marked
     * Creates and returns a MarkCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A MarkCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the index of the task to mark
     */
    public static Command parseMark(TaskList tasks, String command, UI ui) throws DougException {
        command = command.replace("mark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(ui.getMissingIndexMessage());
            return new Command();
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            return new MarkCommand(listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
        return new Command();
    }

    /**
     * Parses the user's input to obtain details of the task to be unmarked
     * Creates and returns an UnMarkCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return An UnMarkCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the index of the task to unmark
     */
    public static Command parseUnmark(TaskList tasks, String command, UI ui) throws DougException {
        command = command.replace("unmark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(ui.getMissingIndexMessage());
            return new Command();
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            return new UnMarkCommand(listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
        return new Command();
    }

    /**
     * Parses the user's input to obtain details what to tasks to find
     * Creates and returns a FindCommand object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @param ui The UI object
     * @return A FindCommand object containing the user's input parameters
     * @throws DougException If the user input is missing the keyword to find in the tasks
     */
    public static Command parseFind(TaskList tasks, String command, UI ui) throws DougException {
        command = command.replaceFirst("find", "").trim();
        if (command.isEmpty()) {
            throw new DougException(ui.getConfusedMessage());
        }

        return new FindCommand(command);
    }
}
