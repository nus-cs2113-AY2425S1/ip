package commands;

import tasklist.TaskList;
import tasklist.tasks.Deadline;

import java.util.HashMap;

public class DeadlineCommand extends Command {
    private static final String[] DEADLINE_REQUIRED_OPTIONS = {"desc", "by"};

    public void execute(TaskList taskList, String line){
        HashMap<String,String> options = getFlags(line,DEADLINE_REQUIRED_OPTIONS);
        if (options == null) {
            return;
        }
        taskList.addTask(new Deadline(options.get("desc"), options.get("by")));
    }
}
