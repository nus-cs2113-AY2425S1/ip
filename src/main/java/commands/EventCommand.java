package commands;

import tasklist.TaskList;
import tasklist.tasks.Event;

import java.util.HashMap;

public class EventCommand extends Command {
    private static final String[] EVENT_REQUIRED_OPTIONS = {"desc", "from", "to"};

    public void execute(TaskList taskList, String line){
        HashMap<String,String> options = getFlags(line,EVENT_REQUIRED_OPTIONS);
        if (options == null) {
            return;
        }
        taskList.addTask(new Event(options.get("desc"), options.get("from"), options.get("to")));
    }
}
