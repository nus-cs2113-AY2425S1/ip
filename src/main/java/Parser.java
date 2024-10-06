/**
 * The Parser class handles parsing of user input and calls the appropriate method
 * from the TaskManager based on the command given.
 */
public class Parser {
    // Command constants
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";

    /**
     * Parses the user's input and calls the appropriate method in the TaskManager.
     * Depending on the command provided by the user, this method performs one of the following actions:
     * List tasks
     * Mark a task as done
     * Marks a task as undone
     * Delete a task
     * Find tasks by a keyword
     * Add a new task
     * @param input The command input by the user
     * @param taskManager The TaskManager object that handles task operations
     * @throws SleepyException If the input cannot be processed or a task number is invalid
     */
    //goes through all the possible commands and calls the appropriate method
    public static void parse(String input, TaskManager taskManager) throws SleepyException {
        if (input.equals(CMD_LIST)) {
            taskManager.listTasks();
        } else if (input.startsWith(CMD_MARK)) {
            int taskNumber = Integer.parseInt(input.substring(CMD_MARK.length()).trim());
            taskManager.markTask(taskNumber);
        } else if (input.startsWith(CMD_UNMARK)) {
            int taskNumber = Integer.parseInt(input.substring(CMD_UNMARK.length()).trim());
            taskManager.unmarkTask(taskNumber);
        } else if (input.startsWith(CMD_DELETE)) {
            int taskNumber = Integer.parseInt(input.substring(CMD_DELETE.length()).trim());
            taskManager.deleteTask(taskNumber);
        } else if (input.startsWith(CMD_FIND)) {
            String keyword = input.substring(CMD_FIND.length()).trim();
            taskManager.findTask(keyword);
        } else {
            taskManager.addTask(input);
        }
    }
}
