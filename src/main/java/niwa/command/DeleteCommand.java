package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_GUIDE = "delete [task index]: Delete the task at the given index.";
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
     * Executes the deletion of a task based on the provided index.
     *
     * @throws NumberFormatException if the index is not a valid number.
     * @throws NiwaTaskIndexOutOfBoundException if the index is out of bounds of the task list.
     */
    @Override
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]);
        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().deleteTask(index);

            // Prepare the message to confirm deletion.
            String message = "OK, I've deleted this task:%n"
                    + PREFIX + "   %s%n"
                    + PREFIX + NiwaMesssages.MESSAGE_LIST_SIZE_INFORM;;

            // Print out a confirmation message with task details and remaining task count.
            System.out.printf(PREFIX + message, temp.getFullInfo(), TaskList.getInstance().getTaskListSize());

            ExecutedCommand.saveTasks();
        } catch (NiwaTaskIndexOutOfBoundException e) {
            System.out.println (PREFIX + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT);
        }
    }
}
