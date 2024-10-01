package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;
import java.util.ArrayList;

public class TaskList {
    public static final String SEPARATOR = "_".repeat(30);

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void deleteTask(String input, ArrayList<Task> tasks, int taskCount) throws lovespiritualException {
        String taskNumber = input.substring("delete".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Oopsie! (⊙_⊙) Please give me a valid number!");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Hmm, that's not a number! (・_・;) Try again, please!");
        }
        Task removedTask = tasks.get(indexNumber);
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.remove(indexNumber);
            System.out.println(SEPARATOR);
            System.out.println("Got it! (◠‿◠) This task is removed!");
            System.out.println(removedTask);
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Yikes! (≧Д≦) That number doesn't look right. Can you double-check it?");
        }
    }

}
