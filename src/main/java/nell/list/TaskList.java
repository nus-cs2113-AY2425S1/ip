package nell.list;

import nell.common.Messages;
import nell.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public void addTask(Task taskToAdd) {
        System.out.println(Messages.TASK_ADD_MESSAGE);
        tasks.add(taskToAdd);
        System.out.println("   " + taskToAdd);
        System.out.println(String.format(Messages.NEW_TASK_COUNT_MESSAGE, this.tasks.size()));
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the formatted string for the task at a specified index
     *
     * @param index The index of the task
     */
    public String getTaskStringAtIndex(int index) throws IndexOutOfBoundsException {
        return String.format("   %d. %s", (index + 1), tasks.get(index));
    }

    public void removeTask(int index) throws IndexOutOfBoundsException {
        Task taskToRemove = tasks.get(index);
        System.out.println(Messages.TASK_REMOVE_MESSAGE);
        tasks.remove(index);
        System.out.println("   " + taskToRemove);
        System.out.println(String.format(Messages.NEW_TASK_COUNT_MESSAGE, this.tasks.size()));
    }

    public void writeListToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (Task task : tasks) {
            String formatLine = task.getFileLine() + System.lineSeparator();
            writer.write(formatLine);
        }
        writer.close();
    }

    public void loadTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    /**
     * Returns a list of tasks that contain a specified keyword.
     * If no tasks are found, returns an empty string
     *
     * @param keyword The keyword
     * @return A list of matching tasks, in the form of a formatted string
     */
    public String getMatchingTaskList(String keyword) {
        String matchingTaskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                matchingTaskList += getTaskStringAtIndex(i);
                matchingTaskList += System.lineSeparator();
            }
        }

        return matchingTaskList;
    }

    /**
     * Returns a list of tasks that occur on a specified date
     *
     * @param date The specified date
     * @return A list of tasks that occur on date
     */
    public String getTasksOnDate(LocalDate date) {
        String matchingTasksList = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isOnDate(date)) {
                matchingTasksList += getTaskStringAtIndex(i);
                matchingTasksList += System.lineSeparator();
            }
        }

        return matchingTasksList;
    }
}
