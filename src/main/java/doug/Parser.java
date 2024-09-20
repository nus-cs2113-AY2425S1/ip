package doug;

import doug.Commands.*;
import doug.Main.DougException;

import java.io.IOException;

import static doug.UI.DASHED_LINE;

public class Parser {

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
        } else {
            throw new DougException(DASHED_LINE + "Something seems off partner...\n" + DASHED_LINE);
        }
        return false;
    }

    public static void parseToDo(TaskList tasks, String command) throws DougException{
        String todoName = command.replace("todo", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }

        AddToDoCommand.newToDo(tasks, todoName);
    }

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

    public static void parseDelete(TaskList tasks, String command) throws DougException {
        if (tasks.getCount() <= 0) {
            System.out.println(DASHED_LINE + "Your list is empty pal, ain't nothing to rid off.\n" + DASHED_LINE);
            return;
        }

        command = command.replace("delete", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            tasks.checkOutOfBounds(listIndex);
            DeleteCommand.deleteTask(tasks, listIndex);
        } catch (DougException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseMark(TaskList tasks, String command) throws DougException {
        command = command.replace("mark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            tasks.checkOutOfBounds(listIndex);
            MarkCommand.markTask(tasks, listIndex);
        } catch (DougException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseUnmark(TaskList tasks, String command) throws DougException {
        command = command.replace("unmark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            tasks.checkOutOfBounds(listIndex);
            UnMarkCommand.unmarkTask(tasks, listIndex);
        } catch (DougException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
