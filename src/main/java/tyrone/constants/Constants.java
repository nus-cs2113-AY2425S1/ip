package tyrone.constants;

import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Constants {
    public static final String LINE = "    ___________________________________";
    public static String logo = " _____                           \n|_   _|   _ _ __ ___  _ __   ___ \n  | || | | | '__/ _ \\| '_ \\ / _ \\\n  | || |_| | | | (_) | | | |  __/\n  |_| \\__, |_|  \\___/|_| |_|\\___|\n      |___/                      ";

    public static java.util.List<Task> toDoList = new java.util.ArrayList<>();

    public Constants() {
    }

    public static void goodbye() {
        System.out.println(LINE);
        System.out.println("    see you brother");
        System.out.println(LINE);
    }

    // Save tasks to ./data/Tasks.txt
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
            for (int i = 0; i < toDoList.size(); i++) {
                Task task = toDoList.get(i);
                String taskType = task instanceof Deadline ? "D" : (task instanceof Event ? "E" : "T");
                String statusIcon = task.getStatusIcon();
                String taskDescription = task.getDescription();

                // Write task details based on its type
                if (task instanceof Deadline) {
                    String doBy = ((Deadline) task).getDoBy();
                    writer.write(taskType + " | " + statusIcon + " | " + taskDescription + " | by: " + doBy + "\n");
                } else if (task instanceof Event) {
                    String timing = ((Event) task).getTiming();
                    writer.write(taskType + " | " + statusIcon + " | " + taskDescription + " | " + timing + "\n");
                } else {
                    writer.write(taskType + " | " + statusIcon + " | " + taskDescription + "\n");
                }
            }

            writer.close();
            System.out.println(LINE);
            System.out.println("    Tasks have been saved to ./data/Tasks.txt");
            System.out.println(LINE);

        } catch (IOException e) {
            System.out.println(LINE);
            System.out.println("    An error occurred while saving tasks.");
            System.out.println(LINE);
        }
    }
}
