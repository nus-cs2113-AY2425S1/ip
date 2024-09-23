package niwa.command;

import niwa.Niwa;
import niwa.data.Storage;
import niwa.data.task.*;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import niwa.utils.NiwaUtils;

import java.util.ArrayList;

import java.io.IOException;

public class ReadCommand extends Command{
    public static final String COMMAND_WORD = "read";
    public static final String COMMAND_GUIDE = "read [.txt file path]: Read the tasks in the file and add to the list.";
    public static final String[] COMMAND_KEYWORDS = {""};

    public static final String PATH_FORMAT = "^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$";

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

    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        String dataPath = arguments.get(COMMAND_KEYWORDS[0]);
        if (!NiwaUtils.isMatch(dataPath, PATH_FORMAT)) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        Storage storage = new Storage(dataPath);
        ArrayList<Task> tasks;

        ArrayList<String> messages = new ArrayList<>();

        try {
            messages.add(String.format(NiwaMesssages.MESSAGE_READ_INFORM, dataPath));
            tasks = storage.loadTaskList();
        } catch (IOException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_READ_FAILED, e.getMessage()));
            return new CommandResult(messages);
        }

        for (Task task: tasks) {
            try {
                messages.add("Adding..." + task.getFileOutput());
                TaskList.getInstance().addTask(task);
            } catch (NiwaDuplicateTaskException e) {
                messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
            }
        }

        if (!dataPath.equals(Niwa.getOutputFilePath())) {
            messages.add(autoSaveTasks());
        }

        return new CommandResult(messages);
    }
}
