package UserInteraction;

import TaskTypes.Task;
import TaskTypes.TaskTypeException;

import java.util.ArrayList;

import static UserInteraction.CreateTask.addNewTask;
import static UserInteraction.PrintShape.printHorizontalLine;

public class HelperMethods {

    public static boolean isValidTaskNumber(ArrayList<Task> tasks, int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= tasks.size());
    }

    public static boolean isInvalidTaskNumber(ArrayList<Task> tasks, int taskNumber) {
        return (!isValidTaskNumber(tasks, taskNumber));
    }

    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            printHorizontalLine();
            return;
        }
        try {
            System.out.println("Here are the tasks in your list: ");
            int serialNumber = 0;
            for (Task task : tasks) {
                serialNumber++;
                System.out.println(serialNumber + "." + task.toString());
            }
            printHorizontalLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTaskToList(ArrayList<Task> tasks, String enteredString) {
        try {
            Task newTask = addNewTask(enteredString);
            tasks.add(newTask);
            System.out.println("Got it. I've added this task: ");
            System.out.println(newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid format for input. Please try again.");
        } catch (TaskTypeException e) {
            System.out.println(e); // "No task type stated. Please try again."
        } catch (Exception e) {
            System.out.println("Error. Please try again.");
        } finally {
            printHorizontalLine();
        }
    }
}
