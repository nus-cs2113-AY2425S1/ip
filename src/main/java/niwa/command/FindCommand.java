package niwa.command;

import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_GUIDE = "todo [keyword]: "
            + "Find tasks by keyword.";
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
     * Find tasks by keyword.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String keyword = arguments.get(COMMAND_KEYWORDS[0]);

        ArrayList<Task> currentTaskList = TaskList.getInstance().getTaskList();
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) currentTaskList.stream()
                .filter((t) -> isContainingKeyword(t, keyword))
                .collect(toList());

        ArrayList<String> messages = new ArrayList<>();
        if (filteredTaskList.isEmpty()) {
            messages.add(NiwaMesssages.MESSAGE_FIND_EMPTY);
        } else {
            messages.add(NiwaMesssages.MESSAGE_FIND_SUCCESS);
        }

        return new CommandResult(messages, filteredTaskList);
    }

    public boolean isContainingKeyword (Task task, String keyword) {
        return (task.getDescription()
                    .toLowerCase()
                    .contains(keyword.toLowerCase()));
    }
}
