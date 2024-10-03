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

    public static ArrayList<Task> toDoList = new ArrayList<>();

    /**
     * Bunch of static function that just prints out Strings to make code easier to read.
     */
    
    public static void help(){
        System.out.println("""
            Available commands:
            \t- bye: Exit the application.
            \t- todo [description]: Add a new todo task.
            \t- deadline [description] /by [DD/MM/YYYY 2400]: Add a new task with a deadline.
            \t- event [description] /from [start] /to [end]: Add a new event.
            \t- mark [task number]: Mark a task as completed.
            \t- unmark [task number]: Unmark a completed task.
            \t- delete [task number]: Delete a task.
            \t- clear: Clear all tasks in the list.
            \t- list: List all tasks.
            \t- priority list: Show all the deadlines due, with the earliest deadline at the top.
            \t- find [keyword]: Find a task by keyword.
            \t- help: Show this help message."""
        );
    }
   
    public static final String LINE = "    ___________________________________";
    public static String logo = " _____                           \n|_   _|   _ _ __ ___  _ __   ___ \n  | || | | | '__/ _ \\| '_ \\ / _ \\\n  | || |_| | | | (_) | | | |  __/\n  |_| \\__, |_|  \\___/|_| |_|\\___|\n      |___/                      ";

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

    public static void invalidTask(){
        System.out.println(LINE);
        System.out.println("    Invalid task number bro.");
        System.out.println(LINE);
    }

    public static void invalidCommand(){
        System.out.println(LINE);
        System.out.println("    Invalid command my brother.");
        System.out.println(LINE);
    }

    public static void missingTimeInfo() {
        System.out.println(LINE);
        System.out.println("    YOURE Missing time info BRUTHA");
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


    /** 
     * Higher level functions with greater complexity.
     */

    public static void getList(ArrayList <Task> tasksList) {
        System.out.println(LINE);
        if (tasksList.isEmpty()) {
            System.out.println("    Your task list is empty, brother.");
        } else {
            for (int i = 0; i < tasksList.size(); i++) {
                Task task = tasksList.get(i);
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
    
        // Print the results using getList()
        System.out.println(LINE);
        if (matchingTasks.isEmpty()) {
            System.out.println("    No matching tasks found for: " + searchString);
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            Constants.getList(matchingTasks);
        }
        System.out.println(LINE);
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
        System.out.println(LINE);
        System.out.println("    Here are your deadlines in order of priority:");
        for (int i = 0; i < deadlineList.size(); i++) {
            Deadline d = deadlineList.get(i);
            System.out.println("    " + (i + 1) + ". [D][ ] " + d.getDescription() + " (by: " + d.getDoBy() + ")");
        }
        System.out.println(LINE);
    }

    public static void clearTasks() {
        // Prompt the user for confirmation
        System.out.println("    Are you sure you want to clear all tasks? (y/n)");
        
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
    
        if (response.equals("y")) {
            // Clear the task list in memory
            Constants.toDoList.clear();
            Task.listCount = 0;
    
            // Clear the tasks in the text file by overwriting it with an empty file
            try {
                FileWriter writer = new FileWriter("data/Tasks.txt", false);  // Overwrite mode
                writer.write("");  // Clear the content of the file
                writer.close();
                System.out.println(Constants.LINE);
                System.out.println("    All tasks have been cleared.");
                System.out.println(Constants.LINE);
            } catch (IOException e) {
                System.out.println("    An error occurred while clearing the tasks.");
            }
        } else if (response.equals("n")) {
            // User chose not to clear the tasks
            System.out.println(Constants.LINE);
            System.out.println("    Task clearing canceled.");
            System.out.println(Constants.LINE);
        } else {
            // Handle invalid input
            System.out.println(Constants.LINE);
            System.out.println("    Invalid input. Please reply with 'y' for yes or 'n' for no.");
            System.out.println(Constants.LINE);
        }
    }
}
