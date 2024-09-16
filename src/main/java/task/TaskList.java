package task;

public class TaskList {
    private int taskNumber;
    private Task[] tasks;
    private Storage storage;

    public TaskList() {
        taskNumber = 0;
        tasks = new Task[100];
        storage = new Storage();
        loadTasks();
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Storage of new task in the task list.
     *
     * @param task Name of the task is added.
     */
    public void storeTask(Task task) {
        if (taskNumber >= tasks.length) {
            return;
        }
        tasks[taskNumber] = task;
        taskNumber += 1;
        saveTasks();
    }

    /**
     * Displays all tasks in the array.
     *
     * @return A string representing all the tasks.
     */
    public String displayTasks() {
        String taskList = "";
        for (int i = 0; i < taskNumber; i += 1) {
            taskList += (i + 1) + ". " + tasks[i] + "\n";
        }
        return taskList.toString();
    }

    /**
     * Marks the task as done.
     *
     * @param index The task number to be marked as done.
     * @return A confirmation message.
     */
    public String markTaskAsDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        Task task = tasks[index - 1];
        task.setAsDone();
        saveTasks();
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Marks the task as undone.
     *
     * @param index The task number to be marked as undone.
     * @return A confirmation message.
     */
    public String markTaskAsNotDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        Task task = tasks[index - 1];
        task.setAsUndone();
        saveTasks();
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public void saveTasks() {
        storage.saveTasks(tasks, taskNumber);
    }

    public void loadTasks() {
        Task[] loadedTasks = storage.loadTasks();
        for (Task task : loadedTasks) {
            if (task != null) {
                tasks[taskNumber++] = task;
            }
        }
    }
}
