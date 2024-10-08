package util;

import exception.InvalidArgumentException;
import task.TaskList;

public final class Parser {
    /**
     * Parse the <code>commandOptions</code> argument for {@link TaskList#markUnmarkTask(String, String) markUnmarkTask} method
     * @param args <code>commandOptions</code> received from upstream
     * @return Task number (starts from 1)
     * @throws InvalidArgumentException If invalid task number
     */
    public static int parseTaskIndex(String args) throws InvalidArgumentException {
        if (args.isBlank()) {
            throw new InvalidArgumentException("Task number cannot be blank");
        }
        try {
            int taskIndex = Integer.parseUnsignedInt(args.trim()) - 1; //Index starts from 0
            if (taskIndex < 0) {
                throw new InvalidArgumentException("Task number must be a positive integer");
            } else { return taskIndex; }
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Task number must be a positive integer");
        }
    }

    /**
     * Parse the input arguments for {@link TaskList#addEvent(String) addEvent} method
     * @param args Arguments received from upstream stored in a <code>String</code>
     * @return Array of arguments: <code>{eventName, eventFrom, eventTo}</code>
     * @throws InvalidArgumentException If any missing arguments exists or invalid format
     */
    public static String[] parseEvent(String args) throws InvalidArgumentException {
        int fromSlashIndex = args.indexOf("/from");
        if (fromSlashIndex == -1)  {
            throw new InvalidArgumentException("\"/from\" expected");
        }

        int toSlashIndex = args.indexOf("/to");
        if (toSlashIndex == -1)  {
            throw new InvalidArgumentException("\"/to\" expected");
        }

        String eventName = args.substring(0, fromSlashIndex).strip();
        if (eventName.isBlank()) {
            throw new InvalidArgumentException("Event name cannot be blank");
        }

        //5 spaces from `fromSlashIndex` since length of "/from" is 5
        String eventFrom = args.substring(fromSlashIndex + 5, toSlashIndex).strip();
        if (eventFrom.isBlank()) {
            throw new InvalidArgumentException("Event from <time> cannot be blank");
        }

        //3 spaces from `toSlashIndex` since length of "/to" is 3
        String eventTo = args.substring(toSlashIndex + 3).strip();
        if (eventTo.isBlank()) {
            throw new InvalidArgumentException("Event to <time> cannot be blank");
        }

        return new String[]{eventName, eventFrom, eventTo};
    }

    /**
     * Validate the input for {@link TaskList#addTodo(String) addTodo} method
     * @param description Todo task description
     * @throws InvalidArgumentException If description is blank
     */
    public static void validateToDo(String description) throws InvalidArgumentException {
        if (description.isBlank()) throw new InvalidArgumentException("Task description cannot be blank");
    }

    /**
     * Parse the input arguments for {@link TaskList#addDeadline(String) addDeadline} method
     * @param args Arguments received from upstream stored in a <code>String</code>
     * @return Array of arguments: <code>{dlName, dlTime}</code>
     * @throws InvalidArgumentException If any missing arguments exists or invalid format
     */
    public static String[] parseDeadline(String args) throws InvalidArgumentException {
        int slashIndex = args.indexOf("/by");
        if (slashIndex == -1) {
            throw new InvalidArgumentException("\"/by\" expected");
        }

        String dlName = args.substring(0, slashIndex).strip();
        if (dlName.isBlank()) {
            throw new InvalidArgumentException("<name> cannot be blank");
        }

        String dlTime = args.substring(slashIndex + 3).strip();
        if (dlTime.isBlank()) {
            throw new InvalidArgumentException("<time> cannot be blank");
        }

        return new String[]{dlName, dlTime};
    }
}
