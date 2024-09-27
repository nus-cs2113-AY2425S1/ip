package luke;

import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    // Constants
    private static final int TASK_TYPE_INDEX = 0;
    private static final int ISDONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int BY_INDEX = 3;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;

    public ArrayList<Task> tasks = new ArrayList<>();
    Ui ui;

    public TaskList(ArrayList<String> saveStrings) {
        ui = new Ui();
        for (String line : saveStrings) {
            loadSingleTask(line);
        }
    }
    private void loadSingleTask(String taskStr) {
        String[] taskStrArr = taskStr.split("\\|");
        switch (taskStrArr[TASK_TYPE_INDEX]) {
        case "T":
            tasks.add(new ToDo(taskStrArr[DESCRIPTION_INDEX], taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        case "D":
            tasks.add(new Deadline(taskStrArr[DESCRIPTION_INDEX], taskStrArr[BY_INDEX],
                taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        case "E":
            tasks.add(new Event(taskStrArr[DESCRIPTION_INDEX], taskStrArr[FROM_INDEX], taskStrArr[TO_INDEX],
                taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        default:
            break;
        }
    }

    public String numberOfTasksMessage() {
        return String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() > 1 ? "tasks" : "task");
    }

    public int getSize() {
        return tasks.size();
    }

    public void list() {
        ui.printDivider();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. ", i + 1);
            System.out.println(tasks.get(i).toString());
        }
        ui.printDivider();
    }
}
