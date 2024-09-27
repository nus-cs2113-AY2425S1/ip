package tyrone.storage;

import tyrone.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    // Constructor that accepts a file path
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Modify loadTasks to return a list of tasks
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Open Tasks.txt file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Read each line and parse the task details
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 4) {
                    String taskType = parts[1].trim();  // T, D, E
                    boolean isDone = parts[2].trim().equals("X");  // X means done, " " means not done
                    String description = parts[3].trim();  // Task description

                    if (taskType.equals("T")) {
                        ToDo todo = new ToDo(description);
                        todo.setDone(isDone);
                        tasks.add(todo);  // Add to task list
                    } else if (taskType.equals("D") && parts.length == 5) {
                        String by = parts[4].trim().replace("by:", "").trim();
                        Deadline deadline = new Deadline(description, by);
                        deadline.setDone(isDone);
                        tasks.add(deadline);  // Add to task list
                    } else if (taskType.equals("E") && parts.length == 5) {
                        String timing = parts[4].trim();
                        Event event = new Event(description, timing);
                        event.setDone(isDone);
                        tasks.add(event);  // Add to task list
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("    Tasks.txt file not found.");
        }
        return tasks;  // Return the list of tasks
    }

    // Save the tasks to the file
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileWriter writer = new FileWriter("./data/Tasks.txt");

            for (Task task : tasks) {
                String taskType = task instanceof Deadline ? "D" : (task instanceof Event ? "E" : "T");
                String statusIcon = task.getStatusIcon();
                String taskDescription = task.getDescription();

                if (task instanceof Deadline) {
                    String doBy = ((Deadline) task).getDoBy();
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + " | by: " + doBy + "\n");
                } else if (task instanceof Event) {
                    String timing = ((Event) task).getTiming();
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + " | " + timing + "\n");
                } else {
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("    An error occurred while saving tasks.");
        }
    }
}
