package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Deadline;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

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
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);
        String byDay = arguments.get(COMMAND_KEYWORDS[1]);

        try {
            Task temp = new Deadline(description, byDay);
            TaskList.getInstance().addTask(temp);

            String message = "Got it. I've added this deadline:%n"
                    + PREFIX + "%s%n"
                    + PREFIX + NiwaMesssages.MESSAGE_LIST_SIZE_INFORM;
            System.out.printf(PREFIX + message, temp.getFullInfo(), TaskList.getInstance().getTaskListSize());

            ExecutedCommand.saveTasks();
        } catch (NiwaDuplicateTaskException e) {
            System.out.printf(PREFIX + e.getMessage());
        }
    }
}
