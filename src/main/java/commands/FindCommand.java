package commands;

import tasklist.TaskList;

import java.util.HashMap;

public class FindCommand extends Command {
    private static final String[] FIND_REQUIRED_OPTIONS = {"match"};

    public void execute(TaskList taskList, String line){
        HashMap<String,String> options = getFlags(line,FIND_REQUIRED_OPTIONS);
        if (options == null) {
            return;
        }
        taskList.searchTasks(options.get("match"));
    }
}
