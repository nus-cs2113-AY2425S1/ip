package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;
import niwa.data.task.TaskList;
import niwa.messages.NiwaShortMessages;
import niwa.ui.NiwaUI;

import java.util.ArrayList;

public class ClearCommand extends Command{
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_GUIDE = "clear: Clear all tasks in the list.";
    public static final String[] COMMAND_KEYWORDS = {};

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
     * Clear all tasks in the list
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        ArrayList<String> messages = new ArrayList<>();

        NiwaUI ui = new NiwaUI();
        ui.printMiddleMessage(NiwaMesssages.MESSAGE_CLEAR_ASK);

        String response = ui.getUserCommand().toUpperCase().trim();

        if (response.equals(NiwaShortMessages.YES_MESSAGE)) {
            TaskList.getInstance().clearTaskList();

            messages.add(NiwaMesssages.MESSAGE_CLEAR_SUCCESS);
            messages.add(autoSaveTasks());
        } else {
            messages.add(NiwaMesssages.MESSAGE_CLEAR_CANCEL);
        }

        messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                TaskList.getInstance().getTaskListSize()));

        return new CommandResult(messages);
    }
}
