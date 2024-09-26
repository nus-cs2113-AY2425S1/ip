package quinn.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task addToDoTask(String description) {
        Task toDoTask = new ToDo(description);
        addTask(toDoTask);
        return toDoTask;
    }

    public Task addDeadlineTask(String description, String dueDateTime) {
        Task deadlineTask = new Deadline(description, dueDateTime);
        addTask(deadlineTask);
        return deadlineTask;
    }

    public Task addEventTask(String description, String startDateTime, String endDateTime) {
        Task eventTask = new Event(description, startDateTime,endDateTime);
        addTask(eventTask);
        return eventTask;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markDone(int taskNum) {
        Task task = getTask(taskNum - 1);
        task.setDone();
        return task;
    }

    public Task markNotDone(int taskNum) {
        Task task = getTask(taskNum - 1);
        task.setNotDone();
        return task;
    }

    public Task deleteTask(int taskNum) {
        Task task = getTask(taskNum - 1);
        tasks.remove(task);
        return task;
    }

    @Override
    public String toString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }
}
