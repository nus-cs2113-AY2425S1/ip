package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Event;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command{

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_GUIDE = "event [task description] /from [time] /to [time]: "
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
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);
        String fromDay = arguments.get(COMMAND_KEYWORDS[1]);
        String toDay = arguments.get(COMMAND_KEYWORDS[2]);
        try {
            Task temp = new Event(description, fromDay,toDay);
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
