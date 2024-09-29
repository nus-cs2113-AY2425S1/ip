package pythia.utility;

import pythia.task.Task;

import java.io.*;

public class Storage {
    private String filePath;
    private WriteVisitor writeVisitor;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.writeVisitor = new WriteVisitor();
    }

    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : taskList) {
                String taskAsString = task.accept(writeVisitor);
                writer.write(taskAsString);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        TaskFromStringFactory factory = new TaskFromStringFactory();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String taskAsString;
            while ((taskAsString = reader.readLine()) != null) {
                Task task = factory.create(taskAsString);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return taskList;
    }
}
