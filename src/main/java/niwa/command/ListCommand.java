package niwa.command;

import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_GUIDE = "list: List all current tasks.";
    public static final String[] COMMAND_KEYWORDS = {};

    public boolean isValidArguments() {
        if (arguments.size() != COMMAND_KEYWORDS.length) {
            return false;
        }
        for (String keyword: COMMAND_KEYWORDS) {
            if (!arguments.containsKey(keyword)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Lists all tasks in the task list.
     *
     */
    @Override
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        System.out.println(PREFIX + "Here are the tasks in your list:");
        int index = 1;

        for (Task task : TaskList.getInstance().getTaskList()) {
            System.out.printf(PREFIX + "%d. %s%n", index++, task.getFullInfo());
        }
    }
}
