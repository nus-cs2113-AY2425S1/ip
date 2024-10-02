import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        // Initialize an empty list of tasks
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        // Initialize the task list with existing tasks
        this.taskList = tasks;
    }

    public void addTask(Task task) {
        // Add a new task to the list
        taskList.add(task);
    }

    public Task deleteTask(int index) throws AirBorderException {
        // Delete a task from the list by its index
        if (index < 0 || index >= taskList.size()) {
            throw new AirBorderException("Invalid task number.");
        }
        return taskList.remove(index);
    }

    public Task getTask(int index) throws AirBorderException {
        // Retrieve a task from the list by its index
        if (index < 0 || index >= taskList.size()) {
            throw new AirBorderException("Invalid task number.");
        }
        return taskList.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        // Return the entire list of tasks
        return taskList;
    }

    public int size() {
        // Return the number of tasks in the list
        return taskList.size();
    }

    public boolean isEmpty() {
        // Check if the task list is empty
        return taskList.isEmpty();
    }
}
