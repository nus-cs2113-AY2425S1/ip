package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;
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

    public static Task getTask(ArrayList<Task> tasks, String enteredString) throws NumberFormatException, InvalidTaskNumberException {
        int taskNumber = getTaskNumber(tasks, enteredString);
        Task task = tasks.get(taskNumber - 1);
        return task;
    }

    public static int getTaskNumber(ArrayList<Task> tasks, String enteredString) throws NumberFormatException, InvalidTaskNumberException {
        int lengthOfString = enteredString.length();
        String taskNumberString = enteredString.substring(lengthOfString - 1);
        int taskNumber = Integer.parseInt(taskNumberString);
        if (isInvalidTaskNumber(tasks, taskNumber)) { //can add an exception here
            throw new InvalidTaskNumberException("Invalid task number. Please try again.");
        }
        return taskNumber;
    }

    public static void printNumberOfTasks(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
