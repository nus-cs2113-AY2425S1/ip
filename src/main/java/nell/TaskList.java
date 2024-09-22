package nell;

import nell.common.Messages;
import nell.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
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
}
