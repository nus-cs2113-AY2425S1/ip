package nell;

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
        System.out.println("-> The task has been added to the list:");
        tasks.add(taskToAdd);
        System.out.println("   " + taskToAdd);
        System.out.println(String.format("   The list now has %d tasks", this.tasks.size()));
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public void removeTask(int index) throws IndexOutOfBoundsException {
        Task taskToRemove = tasks.get(index);
        System.out.println("-> The following task has been removed from the list:");
        tasks.remove(index);
        System.out.println("   " + taskToRemove);
        System.out.println(String.format("   The list now has %d tasks", this.tasks.size()));
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
