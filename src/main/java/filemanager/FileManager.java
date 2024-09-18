package filemanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create the data directory if it doesn't exist
                file.createNewFile();
            }

            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                taskList.add(parseTask(taskLine));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return taskList;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + System.lineSeparator());
            }
            System.out.println("Saving tasks to: " + new File(filePath).getAbsolutePath());

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private Task parseTask(String taskLine) {
        String[] parts = taskLine.split(" \\| ");
        String taskType = parts[0];  // T, D, or E
        boolean isDone = parts[1].equals("1");  // "1" means done, "0" means not done
        String description = parts[2];

        switch (taskType) {
            case "T": // Todo
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.setStatus();
                }
                return todo;

            case "D": // Deadline
                String deadline = parts[3];  // The deadline is in the 4th part
                Deadline dl = new Deadline(description, deadline);
                if (isDone) {
                    dl.setStatus();
                }
                return dl;

            case "E": // Event
                String[] eventTimes = parts[3].split(" ");
                String startTime = eventTimes[0];
                String endTime = eventTimes[1];
                Event event = new Event(description, startTime, endTime);
                if (isDone) {
                    event.setStatus();
                }
                return event;

            default:
                return null;  // Handle unknown task type (shouldn't happen if the format is correct)
        }
    }
}
