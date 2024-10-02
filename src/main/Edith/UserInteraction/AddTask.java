package UserInteraction;

import TaskTypes.Task;
import TaskTypes.TaskTypeException;
import java.util.ArrayList;
import static UserInteraction.CreateTask.addNewTask;
import static UserInteraction.HelperMethods.printNumberOfTasks;
import static UserInteraction.PrintShape.printHorizontalLine;
import static UserInteraction.Storage.addTaskToFile;

public class AddTask {

    public static void addTaskToList(ArrayList<Task> tasks, String enteredString) {
        try {
            Task newTask = addNewTask(enteredString);
            tasks.add(newTask);
            addTaskToFile(newTask);
            System.out.println("Got it. I've added this task: ");
            System.out.println(newTask);
            printNumberOfTasks(tasks);
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
