package tyrone.constants;

import java.util.ArrayList;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Constants {
    public static final String LINE = "    ___________________________________";
    public static String logo = " _____                           \n|_   _|   _ _ __ ___  _ __   ___ \n  | || | | | '__/ _ \\| '_ \\ / _ \\\n  | || |_| | | | (_) | | | |  __/\n  |_| \\__, |_|  \\___/|_| |_|\\___|\n      |___/                      ";

    public static ArrayList<Task> toDoList = new ArrayList<>();

    public Constants() {
    }

    public static void goodbye() {
        System.out.println(LINE);
        System.out.println("    see you brother");
        System.out.println(LINE);
    }

    public static void markAsDone(int index) {
        toDoList.get(index).setDone(true);
        System.out.println(LINE);
        System.out.println("    TIGHT! I've marked this task as done:");
        System.out.println("      [X] " + toDoList.get(index).getDescription());
        System.out.println(LINE);
    }

    public static void unmarkAsUndone(int index) {
        toDoList.get(index).setDone(false);
        System.out.println(LINE);
        System.out.println("    alright cuh, I've marked this task as unfinished:");
        System.out.println("      [ ] " + toDoList.get(index).getDescription());
        System.out.println(LINE);
    }

    public static void getList() {
        System.out.println(LINE);
        if (toDoList.isEmpty()) {
            System.out.println("    Your task list is empty, brother.");
        } else {
            for (int i = 0; i < toDoList.size(); i++) {
                Task task = toDoList.get(i);
                String statusIcon = task.getStatusIcon();
                if (task instanceof Deadline) {
                    System.out.println("    " + (i + 1) + ". [D][" + statusIcon + "] " + task.getDescription() + " (by: " + ((Deadline) task).getDoBy() + ")");
                } else if (task instanceof Event) {
                    System.out.println("    " + (i + 1) + ". [E][" + statusIcon + "] " + task.getDescription() + " (" + ((Event) task).getTiming() + ")");
                } else {
                    System.out.println("    " + (i + 1) + ". [T][" + statusIcon + "] " + task.getDescription());
                }
            }
        }
        System.out.println(LINE);
    }

    public static void missingTimeInfo() {
        System.out.println(LINE);
        System.out.println("    YOURE Missing time info BRUTHA");
        System.out.println(LINE);
    }
    
    public static void deleteTask(int index) {
        if (index >= 0 && index < toDoList.size()) {
            Task removedTask = toDoList.remove(index);
            System.out.println(LINE);
            System.out.println("    Noted. I've removed this task:");
            String taskType = removedTask instanceof Deadline ? "D" : removedTask instanceof Event ? "E" : "T";
            String statusIcon = removedTask.getStatusIcon();
            System.out.println("      [" + taskType + "][" + statusIcon + "] " + removedTask.getDescription());
            if (removedTask instanceof Deadline) {
                System.out.println("      (by: " + ((Deadline) removedTask).getDoBy() + ")");
            } else if (removedTask instanceof Event) {
                System.out.println("      (" + ((Event) removedTask).getTiming() + ")");
            }
            System.out.println("    Now you have " + toDoList.size() + " tasks in the list.");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("    Invalid task number bro.");
            System.out.println(LINE);
        }
    }
    // Save tasks
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
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + " | by: " + doBy + "\n");
                } else if (task instanceof Event) {
                    String timing = ((Event) task).getTiming();
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + " | " + timing + "\n");
                } else {
                    writer.write("| " + taskType + " | " + statusIcon + " | " + taskDescription + "\n");
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
