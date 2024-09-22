package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_GUIDE = "mark [task index]: Mark the task at the given index as done.";
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
     * Marks a task as done.
     *
     */
    @Override
    public void execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]);
        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().findTask(index);
            temp.markAsDone();

            String message = "OK, I've marked this task as done:%n"
                    + PREFIX + "%s%n";
            System.out.printf(PREFIX + message, temp.getFullInfo());

            ExecutedCommand.saveTasks();

        } catch (NiwaTaskIndexOutOfBoundException e) {
            System.out.println (PREFIX + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT);
        }
    }
}
