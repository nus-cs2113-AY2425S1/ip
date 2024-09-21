package doug;

import doug.Commands.*;
import doug.Main.DougException;
import static doug.UI.DASHED_LINE;

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
     * @return True if user has sent "bye" command, and False otherwise
     * @throws DougException If the user input does not start with an existing command
     */
    public static boolean extractCommandType(TaskList tasks, String command) throws DougException {
        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")){
            ListCommand.listTasks(tasks);
        } else if (command.startsWith("mark")) {
            parseMark(tasks, command);
        } else if (command.startsWith("unmark")) {
            parseUnmark(tasks, command);
        } else if (command.startsWith("todo")) {
            parseToDo(tasks, command);
        } else if (command.startsWith("deadline")) {
            parseDeadline(tasks, command);
        } else if (command.startsWith("event")) {
            parseEvent(tasks, command);
        } else if (command.startsWith("delete")) {
            parseDelete(tasks, command);
        } else if (command.startsWith("find")) {
            parseFind(tasks, command);
        } else {
            throw new DougException(DASHED_LINE + "Something seems off partner...\n" + DASHED_LINE);
        }
        return false;
    }

    /**
     * Parses the user's input to obtain details of the todo task to be added
     * Calls the todo command class to add the new task to the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the name of the task
     */
    public static void parseToDo(TaskList tasks, String command) throws DougException{
        String todoName = command.replace("todo", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }

        AddToDoCommand.newToDo(tasks, todoName);
    }

    /**
     * Parses the user's input to obtain details of the deadline task to be added
     * Calls the deadline command class to add the new task to the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the name or deadline time/date of the task
     */
    public static void parseDeadline(TaskList tasks, String command) throws DougException{
        command = command.replace("deadline", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do here pal...\n" + DASHED_LINE);
        }
        if (!command.contains("/by")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/by\" keyword.\n" + DASHED_LINE);
        }

        int indexOfSlash = command.indexOf("/by");
        String deadlineName = command.substring(0, indexOfSlash).trim();
        if (deadlineName.isEmpty()) {
            throw new DougException(DASHED_LINE + "Didn't name your deadline task did you bud?\n" + DASHED_LINE);
        }

        command = command.replace(deadlineName, "");
        String deadlineBy = command.replace("/by", "").trim();
        if (deadlineBy.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when it's due by, huh bud?\n" + DASHED_LINE);
        }

        AddDeadlineCommand.newDeadline(tasks, deadlineName, deadlineBy);
    }

    /**
     * Parses the user's input to obtain details of the event task to be added
     * Calls the event command class to add the new task to the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the name or start/end time/date of the task
     */
    public static void parseEvent(TaskList tasks, String command) throws DougException {
        command = command.replace("event", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do here pal...\n" + DASHED_LINE);
        }
        if (!command.contains("/from")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/from\" keyword.\n" + DASHED_LINE);
        }
        if (!command.contains("/to")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/to\" keyword.\n" + DASHED_LINE);
        }

        int indexOfFirstSlash = command.indexOf("/from");
        String eventName = command.substring(0, indexOfFirstSlash).trim();
        if (eventName.isEmpty()) {
            throw new DougException(DASHED_LINE + "Didn't name your event did you bud?\n" + DASHED_LINE);
        }

        command = command.replace(eventName, "");
        command = command.replace("/from", "").trim();
        int indexOfSecondSlash = command.indexOf("/to");
        String eventFrom = command.substring(0, indexOfSecondSlash).trim();
        if (eventFrom.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when " + eventName
                    + " starts, huh bud?\n" + DASHED_LINE);
        }

        command = command.replace(eventFrom, "");
        String eventTo = command.replace("/to", "").trim();
        if (eventTo.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when " + eventName
                    + " ends, huh bud?\n" + DASHED_LINE);
        }

        AddEventCommand.newEvent(tasks, eventName, eventFrom, eventTo);
    }

    /**
     * Parses the user's input to obtain details of the delete task to be added
     * Calls the delete command class to remove the selected task from the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the index of the task to delete
     */
    public static void parseDelete(TaskList tasks, String command) throws DougException {
        if (tasks.getCount() <= 0) {
            System.out.println(DASHED_LINE + "Your list is empty pal, ain't nothing to rid off.\n" + DASHED_LINE);
            return;
        }

        command = command.replace("delete", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(DASHED_LINE + "You're missing the index number pal...\n" + DASHED_LINE);
            return;
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            DeleteCommand.deleteTask(tasks, listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the user's input to obtain details of the task to be marked
     * Calls the mark command class to mark the selected task in the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the index of the task to mark
     */
    public static void parseMark(TaskList tasks, String command) throws DougException {
        command = command.replace("mark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(DASHED_LINE + "You're missing the index number pal...\n" + DASHED_LINE);
            return;
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            MarkCommand.markTask(tasks, listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the user's input to obtain details of the task to be unmarked
     * Calls the unmark command class to unmark the selected task in the list
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param command The full line of input from the user
     * @throws DougException If the user input is missing the index of the task to unmark
     */
    public static void parseUnmark(TaskList tasks, String command) throws DougException {
        command = command.replace("unmark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = -1;

        try {
            listIndex = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.print(DASHED_LINE + "You're missing the index number pal...\n" + DASHED_LINE);
            return;
        }

        try {
            tasks.checkOutOfBounds(listIndex);
            UnMarkCommand.unmarkTask(tasks, listIndex);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseFind(TaskList tasks, String command) throws DougException {
        command = command.replace("find", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you want to find pal...\n" + DASHED_LINE);
        }
        FindCommand.findTask(tasks, command);
    }
}
