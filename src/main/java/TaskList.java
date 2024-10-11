import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the user specified task from the list.
     * @param index
     * @return
     * @throws PlopBotException
     */
    public Task removeTask(int index) throws PlopBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlopBotException("Invalid task index");
        }
        return tasks.remove(index);
    }

    /**
     * Finds the matching tasks from the user's saved list of tasks. Case insensitive.
     * @param keyword
     * @return
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter method that returns the user specified task.
     * @param index
     * @return
     * @throws PlopBotException
     */
    public Task getTask(int index) throws PlopBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlopBotException("Task index out of range.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}