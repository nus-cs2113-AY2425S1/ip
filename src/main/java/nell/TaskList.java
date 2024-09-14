package nell;

import nell.tasks.Deadline;
import nell.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void addTask(Task taskToAdd) {
        System.out.println("-> The task has been added to the list:");
        tasks.add(taskToAdd);
        System.out.println("   " + taskToAdd);
        System.out.println(String.format("   The list now has %d tasks", tasks.size()));
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException,
            NullPointerException {
        return tasks.get(index);
    }

    public void writeListToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (Task task : tasks) {
            String formatLine = task.getFileLine() + System.lineSeparator();
            writer.write(formatLine);
        }
        writer.close();
    }
}
