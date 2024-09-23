package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import niwa.data.task.Deadline;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_GUIDE = "deadline [task description] /by [yyyy-mm-dd] (optional)[HHmm]: "
            + "Add a new deadline to our list.";
    public static final String[] COMMAND_KEYWORDS = {"", "/by"};

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
     * Adds a new deadline to the task list.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);
        String byDay = arguments.get(COMMAND_KEYWORDS[1]);

        ArrayList<String> messages = new ArrayList<>();

        try {
            Task temp = new Deadline(description, byDay);
            TaskList.getInstance().addTask(temp);

            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize()));

            messages.add(autoSaveTasks());

        } catch (NiwaDuplicateTaskException | NiwaException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
