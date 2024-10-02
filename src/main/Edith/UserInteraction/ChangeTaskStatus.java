package UserInteraction;

import TaskTypes.Task;

import java.util.ArrayList;

import static UserInteraction.PrintShape.printHorizontalLine;

public class ChangeTaskStatus {

    public static void changeTaskStatus(ArrayList<Task> tasks, String enteredString) {
        int lengthOfString = enteredString.length();
        try {
            String taskNumberString = enteredString.substring(lengthOfString - 1);
            int taskNumber = Integer.parseInt(taskNumberString);

            if (HelperMethods.isInvalidTaskNumber(tasks, taskNumber)) { //can add an exception here
                throw new InvalidTaskNumberException("Invalid task number. Please try again.");
            }

            Task currentTask = tasks.get(taskNumber - 1);
            if (enteredString.contains("unmark")) {
                unmarkTask(currentTask);
            } else if (enteredString.contains("mark")) {
                markTask(currentTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Task Number not found ");
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            printHorizontalLine();
        }

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
