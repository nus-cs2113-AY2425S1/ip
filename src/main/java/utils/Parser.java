package utils;

public final class Parser {
    /**
     * Parse the <code>commandOptions</code> argument for {@link ActionManager#markUnmarkTask(String, String) markUnmarkTask} method
     * @param args <code>commandOptions</code> received from upstream
     * @return Task number (starts from 1)
     * @throws SuBotException If invalid task number
     */
    static int parseMarkUnmark(String args) throws SuBotException {
        if (args.isBlank()) {
            throw new SuBotException("Task number cannot be blank");
        }
        try {
            int taskIndex = Integer.parseUnsignedInt(args.trim()) - 1; //Index starts from 0
            if (taskIndex < 0) {
                throw new SuBotException("Task number must be a positive integer");
            } else { return taskIndex; }
        } catch (NumberFormatException e) {
            throw new SuBotException("Task number must be a positive integer");
        }
    }

    /**
     * Parse the input arguments for {@link ActionManager#addEvent(String) addEvent} method
     * @param args Arguments received from upstream stored in a <code>String</code>
     * @return Array of arguments: <code>{eventName, eventFrom, eventTo}</code>
     * @throws SuBotException If any missing arguments exists or invalid format
     */
    static String[] parseEvent(String args) throws SuBotException {
        int fromSlashIndex = args.indexOf("/from");
        if (fromSlashIndex == -1)  {
            throw new SuBotException("\"/from\" expected");
        }

        int toSlashIndex = args.indexOf("/to");
        if (toSlashIndex == -1)  {
            throw new SuBotException("\"/to\" expected");
        }

        String eventName = args.substring(0, fromSlashIndex).strip();
        if (eventName.isBlank()) {
            throw new SuBotException("Event name cannot be blank");
        }

        //5 spaces from `fromSlashIndex` since length of "/from" is 5
        String eventFrom = args.substring(fromSlashIndex + 5, toSlashIndex).strip();
        if (eventFrom.isBlank()) {
            throw new SuBotException("Event from <time> cannot be blank");
        }

        //3 spaces from `toSlashIndex` since length of "/to" is 3
        String eventTo = args.substring(toSlashIndex + 3).strip();
        if (eventTo.isBlank()) {
            throw new SuBotException("Event to <time> cannot be blank");
        }

        return new String[]{eventName, eventFrom, eventTo};
    }

    /**
     * Validate the input for {@link ActionManager#addTodo(String) addTodo} method
     * @param description Todo task description
     * @throws SuBotException If description is blank
     */
    static void validateToDo(String description) throws SuBotException {
        if (description.isBlank()) throw new SuBotException("Task description cannot be blank");
    }

    /**
     * Parse the input arguments for {@link ActionManager#addDeadline(String) addDeadline} method
     * @param args Arguments received from upstream stored in a <code>String</code>
     * @return Array of arguments: <code>{dlName, dlTime}</code>
     * @throws SuBotException If any missing arguments exists or invalid format
     */
    static String[] parseDeadline(String args) throws SuBotException {
        int slashIndex = args.indexOf("/by");
        if (slashIndex == -1) {
            throw new SuBotException("\"/by\" expected");
        }

        String dlName = args.substring(0, slashIndex).strip();
        if (dlName.isBlank()) {
            throw new SuBotException("<name> cannot be blank");
        }

        String dlTime = args.substring(slashIndex + 3).strip();
        if (dlTime.isBlank()) {
            throw new SuBotException("<time> cannot be blank");
        }

        return new String[]{dlName, dlTime};
    }
}
