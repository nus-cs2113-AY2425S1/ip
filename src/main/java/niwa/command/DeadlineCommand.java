package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Deadline;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_GUIDE = "deadline [task description] /by [time]: "
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

        } catch (NiwaDuplicateTaskException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
