package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Event;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command{

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_GUIDE = "event [task description] " +
            "/from [yyyy-mm-dd] (optional)[HHmm] /to [yyyy-mm-dd] (optional)[HHmm]: "
            + "Add a new event to our list.";
    public static final String[] COMMAND_KEYWORDS = {"", "/from", "/to"};

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
     * Adds a new event to the task list.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);
        String fromDay = arguments.get(COMMAND_KEYWORDS[1]);
        String toDay = arguments.get(COMMAND_KEYWORDS[2]);

        ArrayList<String> messages = new ArrayList<>();

        try {
            Task temp = new Event(description, fromDay,toDay);
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
