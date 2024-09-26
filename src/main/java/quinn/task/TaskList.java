package quinn.task;

import quinn.command.CommandType;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private List<Task> filteredTasks;
    private CommandType filterCommandType;
    private String filterInfo;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public int getNumOfFilteredTasks() {
        return filteredTasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getFilteredTask(int index) {
        return filteredTasks.get(index);
    }

    public boolean hasFilter() {
        return !filteredTasks.isEmpty();
    }

    public CommandType getFilterCommandType() {
        return filterCommandType;
    }

    public String getFilterInfo() {
        return filterInfo;
    }

    public void resetFilteredTasks() {
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    public void setFilteredTasksByKeyword(String keyword) {
        filteredTasks = new ArrayList<>();

        // Iterate through each task and filter those that contain the keyword
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                filteredTasks.add(task);
            }
        }

        filterCommandType = CommandType.FIND;
        filterInfo = keyword;
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
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setDone();
        return task;
    }

    public Task markNotDone(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setNotDone();
        return task;
    }

    public Task deleteTask(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        tasks.remove(task);

        if (hasFilter()) {
            filteredTasks.remove(task);
        }

        return task;
    }

    public String listOfTasksString() {
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

    public String listOfFilteredTasksString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfFilteredTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getFilteredTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }

    @Override
    public String toString() {
        return !hasFilter() ? listOfTasksString() : listOfFilteredTasksString();
    }
}
