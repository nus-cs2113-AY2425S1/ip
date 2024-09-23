package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

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
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]);

        ArrayList<String> messages = new ArrayList<>();

        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().deleteTask(index);

            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize()));

            messages.add(autoSaveTasks());

        } catch (NiwaTaskIndexOutOfBoundException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_FAILED, e.getMessage()));
        } catch (NumberFormatException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_FAILED,
                    NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT));
        }

        return new CommandResult(messages);
    }
}
