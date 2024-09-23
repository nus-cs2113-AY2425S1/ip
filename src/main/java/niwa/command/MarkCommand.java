package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

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
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]);

        ArrayList<String> messages = new ArrayList<>();

        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().findTask(index);
            temp.markAsDone();

            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());

            messages.add(autoSaveTasks());

        } catch (NiwaTaskIndexOutOfBoundException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_FAILED, e.getMessage()));
        } catch (NumberFormatException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_FAILED,
                    NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT));
        }

        return new CommandResult(messages);
    }
}
