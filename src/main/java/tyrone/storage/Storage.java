package tyrone.storage;

import tyrone.constants.Constants;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.Task;
import tyrone.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Storage has two main functions,
 * storing Tasks in the .txt file and
 * reading the Tasks in the .txt file when it is first booted up
 */

public class Storage {
    /**
     * Every task is saved after every action taken in Parser
     */
    public static void saveTasks() {
        try {
            // Check if ./data directory exists, if not, create it
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Create or open Tasks.txt file
            FileWriter writer = new FileWriter("./data/Tasks.txt");

            // Write all tasks to the file
            for (int i = 0; i < Constants.toDoList.size(); i++) {
                Task task = Constants.toDoList.get(i);
                String taskType = task instanceof Deadline ? "D" : (task instanceof Event ? "E" : "T");
                String statusIcon = task.getStatusIcon();
                String taskDescription = task.getDescription();

                // Write task details based on its type
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
            System.out.println(Constants.LINE);
            System.out.println("    An error occurred while saving tasks.");
            System.out.println(Constants.LINE);
        }
    }

    /** 
     * This function reads the tasks in data/Task.txt and then inputs them into the ArrayList of Tasks
     */
    public static ArrayList<Task> loadTasks() {
  
        ArrayList<Task> tasks = new ArrayList<>();
    
        try {
        // Open Tasks.txt file
        File file = new File("data/Tasks.txt");
        Scanner scanner = new Scanner(file);

        // Read each line and parse the task details
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            if (parts.length >= 4) {
                 // Task type, T (ToDo), D (Deadline), or E (Event)
                String taskType = parts[1].trim(); 

                // Marked as done if 'X', otherwise not done
                boolean isDone = parts[2].trim().equals("X");  

                // Task description
                String description = parts[3].trim(); 

                // Handle different task types
                if (taskType.equals("T")) {
                    ToDo todo = new ToDo(description);
                    todo.setDone(isDone);

                    // Create new Todo Task
                    tasks.add(todo);
                } else if (taskType.equals("D") && parts.length == 5) {
                    String by = parts[4].trim();
                    // Remove "by:" prefix
                    if (by.startsWith("by:")) {
                        by = by.replace("by:", "").trim();  
                    }

                    try {
                        // Parse the deadline date and time
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu h:mma");
                        LocalDateTime dueDateTime = LocalDateTime.parse(by, inputFormatter);  

                        // Create the Deadline object using LocalDateTime
                        Deadline deadline = new Deadline(description, dueDateTime);
                        deadline.setDone(isDone);
                        tasks.add(deadline);
                    } catch (DateTimeParseException e) {
                        System.out.println("    Invalid date format for deadline: " + by);
                    }
                } else if (taskType.equals("E") && parts.length == 5) {
                    String timing = parts[4].trim(); 

                    // Create new Event Task
                    Event event = new Event(description, timing); 
                    event.setDone(isDone);
                    tasks.add(event);
                }
            }
        }
        scanner.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("    Tasks.txt file not found.");
        }
        return tasks; 
    }    
}
