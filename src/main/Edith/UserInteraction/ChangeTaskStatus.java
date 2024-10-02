package UserInteraction;

import TaskTypes.Task;
import UserInteraction.HelperMethods;

import java.util.ArrayList;

import static UserInteraction.PrintShape.printHorizontalLine;

public class ChangeTaskStatus {

    public static void changeTaskStatus(ArrayList<Task> tasks, String enteredString) {
        int lengthOfString = enteredString.length();
        String taskNumberString = enteredString.substring(lengthOfString - 1);
        int taskNumber = Integer.parseInt(taskNumberString);

        if (! HelperMethods.isValidTaskNumber(tasks, taskNumber)) { //can add an exception here
            System.out.println("Invalid task number. Please try again.");
            printHorizontalLine();
            return;
        }

        Task currentTask = tasks.get(taskNumber - 1);
        if (enteredString.contains("unmark")) {
            unmarkTask(currentTask);
        } else if (enteredString.contains("mark")) {
            markTask(currentTask);
        }
        printHorizontalLine();
    }

    public static void unmarkTask(Task task) {
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
    public static void markTask(Task task) {
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }
}
