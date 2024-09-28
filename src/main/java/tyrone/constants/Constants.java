package tyrone.constants;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Constants {
    public static final String LINE = "    ___________________________________";
    public static String logo = " _____                           \n|_   _|   _ _ __ ___  _ __   ___ \n  | || | | | '__/ _ \\| '_ \\ / _ \\\n  | || |_| | | | (_) | | | |  __/\n  |_| \\__, |_|  \\___/|_| |_|\\___|\n      |___/                      ";

    public static ArrayList<Task> toDoList = new ArrayList<>();


    public static void intro(){
        System.out.println(LINE);
        System.out.println("    Hello from\n" + Constants.logo + "\n");
        System.out.println("    What can I do for you cuh?\n");
        System.out.println(LINE);
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

    public static void findTasks(String userInput) {
        // Extract the search string (substring after "find ")
        String searchString = userInput.substring(5).trim().toLowerCase(); // Convert to lowercase for case-insensitive search
    
        // Check if the search string is empty
        if (searchString.isEmpty()) {
            System.out.println("    Please specify a keyword to search.");
            return;
        }
    
        // Create a list to store matching tasks
        ArrayList<Task> matchingTasks = new ArrayList<>();
    
        // Iterate over the to-do list and find matching tasks
        for (Task task : Constants.toDoList) {
            if (task.getDescription().toLowerCase().contains(searchString)) { // Case-insensitive match
                matchingTasks.add(task);
            }
        }
    
        // Print the results in the same format as getList()
        System.out.println(Constants.LINE);
        if (matchingTasks.isEmpty()) {
            System.out.println("    No matching tasks found for: " + searchString);
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
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
        System.out.println(Constants.LINE);
    }    

    public static void getPriorityList(){
        List<Deadline> deadlineList = new ArrayList<>();

    // Filter out deadlines from the to-do list
        for (Task task : Constants.toDoList) {
            if (task instanceof Deadline) {
                deadlineList.add((Deadline) task);
            }
        }

    // Sort the deadlines by due date
        deadlineList.sort(Comparator.comparing(Deadline::getDueDateTime));

    // Print the sorted deadlines
        System.out.println(Constants.LINE);
        System.out.println("    Here are your deadlines in order of priority:");
        for (int i = 0; i < deadlineList.size(); i++) {
            Deadline d = deadlineList.get(i);
            System.out.println("    " + (i + 1) + ". [D][ ] " + d.getDescription() + " (by: " + d.getDoBy() + ")");
        }
        System.out.println(Constants.LINE);
    }

}
