package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.data.task.ToDo;

import java.util.ArrayList;

public class TodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_GUIDE = "todo [task description]: "
            + "Add a new to-do task to our list.";
    public static final String[] COMMAND_KEYWORDS = {""};

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
     * Adds a new todo to the task list.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);

        ArrayList<String> messages = new ArrayList<>();
        try {
            Task temp = new ToDo(description);
            TaskList.getInstance().addTask(temp);

            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize()));

            messages.add(autoSaveTasks());

        } catch (NiwaDuplicateTaskException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
