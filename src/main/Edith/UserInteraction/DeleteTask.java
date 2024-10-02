package UserInteraction;

import TaskTypes.*;

import java.util.ArrayList;

import static UserInteraction.HelperMethods.*;
import static UserInteraction.PrintShape.printHorizontalLine;

public class DeleteTask {
    public static void deleteTask(ArrayList<Task> tasks, String enteredString) {
        try {
            int currentTaskNumber = getTaskNumber(tasks, enteredString);
            Task removedTask = tasks.remove(currentTaskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            printNumberOfTasks(tasks);
        } catch (NumberFormatException e) {
            System.out.println("Task Number not found ");
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            printHorizontalLine();
        }
    }
}
