package Uranus.Utility;

import Uranus.Tasks.Deadlines;
import Uranus.Tasks.Events;
import Uranus.Tasks.Task;
import Uranus.Tasks.ToDos;
import UranusExceptions.EmptyDescriptionException;
import UranusExceptions.EmptyInputExceptions;
import UranusExceptions.IllegalCommandException;
import UranusExceptions.UranusExceptions;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The TaskList class manages the list of tasks,
 * including adding, deleting, marking, and finding tasks.
 * It interacts with the user input to perform various task management functions.
 */
public class TaskList extends Functions{
    /**
     * Handles the deletion of a task based on user input.
     * The method checks if the specified task exists and removes it from the task list.
     *
     * @param input The command input containing the task number to delete.
     */
    protected static void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.print("Got it. I've removed this task:",
                        "  " + taskStatus(taskList,index),
                        "Now you have %d task(s) in the list".formatted(taskList.size() - 1));
                taskList.remove(index);
            } else {
                Ui.print("No such task exists. Please try again.");
            }
        } catch (NumberFormatException e) {
            Ui.print("Invalid task input. Please try again.", "Correct format: delete <int>");
        } catch (IllegalArgumentException e) {
            Ui.print("Task Number cannot be empty!");
        }
    }

    /**
     * Handles marking or unmarking a task as done or not done based on user input.
     *
     * @param input The command input containing the task number to mark or unmark.
     */
    protected static void handleMarking(String input) {
        try {
            int taskNumIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (taskNumIndex >= 0 && taskNumIndex < taskList.size()) {
                if (input.startsWith("mark")) {
                    taskList.get(taskNumIndex).setDone();
                    Ui.print("Nice! I've marked this task as done:", taskStatus(taskList,taskNumIndex));
                } else {
                    taskList.get(taskNumIndex).setNotDone();
                    Ui.print("OK! I've marked this task as not done yet:", taskStatus(taskList,taskNumIndex));
                }
            } else {
                Ui.print("No such task exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            Ui.print("Invalid task input. Please try again.", "Correct format: mark <int> / unmark <int>");
        } catch (IllegalArgumentException e) {
            Ui.print("Task number cannot be empty!");
        }
    }

    /**
     * Returns the formatted status of a task at the specified index.
     *
     * @param taskList The list of tasks.
     * @param index The index of the task in the list.
     * @return A formatted string representing the task's status.
     */
    public static String taskStatus(ArrayList<Task> taskList, int index){
        return taskList.get(index).getTaskStatus();
    }

    /**
     * Lists all tasks in the task list and prints them to the console.
     *
     * @param taskList The list of tasks to display.
     */
    protected static void listTasks(ArrayList<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskStatus(taskList,i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Adds a new task based on the user's input.
     * It checks the input type and adds the task to the task list.
     *
     * @param input The user's input containing the task details.
     */
    protected static void addTask(String input) {

        try {
            if (input == null || input.trim().isEmpty()) {
                throw new EmptyInputExceptions();
            } else if (input.startsWith(Parser.TODO_COMMAND)){
                taskList.add(new ToDos(input));
            } else if (input.startsWith(Parser.DEADLINE_COMMAND)){
                taskList.add(new Deadlines(input));
            } else if (input.startsWith(Parser.EVENT_COMMAND)){
                taskList.add(new Events(input));
            } else if (input.startsWith(Parser.TASK_COMMAND)){
                taskList.add(new Task(input));
            } else{
                throw new IllegalCommandException();
            }
            Ui.print("Got it. I've added this task:",
                    "  " + taskStatus(taskList,taskList.size() - 1),
                    "Now you have %d task(s) in the list".formatted(taskList.size()));
        } catch (UranusExceptions e){
            Ui.print(e.getMessage());
        }
    }

    /**
     * Handles the find command by searching for tasks that contain the specified keyword.
     *
     * @param input The user's input containing the keyword to search for.
     */
    protected static void handleFind(String input) {
        try {
            if (input.trim().equals(Parser.FIND_COMMAND)){
                throw new EmptyDescriptionException();
            }
            String[] str = input.split(" ");
            String taskToFind = str[1];
            findTasks(taskToFind);
        } catch (UranusExceptions e) {
            Ui.print(e.getMessage());
        }
    }

    /**
     * Finds tasks in the task list that contain the specified keyword.
     * The results are displayed to the user.
     *
     * @param input The keyword to search for in the task descriptions.
     */
    protected static void findTasks(String input){
        ArrayList<Task> filteredTasks =
                taskList.stream()
                        .filter(task -> task.getDescription().toLowerCase().contains(input.toLowerCase()))
                        .collect(Collectors.toCollection(ArrayList::new));
        listTasks(filteredTasks);
    }
}
