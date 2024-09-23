package niwa.command;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import java.util.ArrayList;

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
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        ArrayList<String> messages = new ArrayList<>();

        ArrayList<Task> returnedTasks = TaskList.getInstance().getTaskList();

        if (returnedTasks.isEmpty()) {
            messages.add(NiwaMesssages.MESSAGE_LIST_EMPTY);
        }
        else {
            messages.add(NiwaMesssages.MESSAGE_LIST_SUCCESS);
        }
        return new CommandResult(messages, returnedTasks);
    }
}
