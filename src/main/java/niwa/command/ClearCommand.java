package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.TaskList;

public class ClearCommand extends Command{
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_GUIDE = "clear: Clear all tasks in the list.";
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
     * Clear all tasks in the list
     *
     */
    @Override
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        TaskList.getInstance().clearTaskList();

        // Prepare the message to confirm deletion.
        String message = "OK, I've clear your task list.%n"
                + PREFIX + NiwaMesssages.MESSAGE_LIST_SIZE_INFORM;

        // Print out a confirmation message with task details and remaining task count.
        System.out.printf(PREFIX + message, TaskList.getInstance().getTaskListSize());
    }
}
