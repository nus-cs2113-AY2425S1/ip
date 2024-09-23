public class Parser {
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";

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
