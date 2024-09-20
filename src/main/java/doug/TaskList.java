package doug;

import doug.Main.DougException;
import doug.tasks.Task;
import java.util.ArrayList;

import static doug.UI.DASHED_LINE;

public class TaskList {
    private ArrayList<Task> tasks;
    private static int counter;

    public TaskList() {
        counter = 0;
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        counter++;
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public  Task getTask(int id) {
        return tasks.get(id);
    }
    public void removeTask(int id) {
        tasks.remove(id);
        counter--;
    }
    public int getCount() {
        return counter;
    }
    public void checkOutOfBounds(int listIndex) throws DougException {
        if (listIndex > counter || listIndex <= 0) {
            throw new DougException(DASHED_LINE + "No can do bud, your input is invalid!\n" + DASHED_LINE);
        }
    }
}
