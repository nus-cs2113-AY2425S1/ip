package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;
import java.util.Scanner;

import static UserInteraction.AddTask.addTaskToList;
import static UserInteraction.ChangeTaskStatus.changeTaskStatus;
import static UserInteraction.DeleteTask.deleteTask;
import static UserInteraction.PrintShape.printHorizontalLine;

public class Ui {
    private final String MY_NAME;
    ArrayList<Task> tasks;

    public Ui(String MY_NAME) {
        this.MY_NAME = MY_NAME;
        tasks = new ArrayList<>();
    }

    public void giveIntroduction() {
        System.out.println("Hello I am " + MY_NAME + ".");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void interactWithUser() {
        Scanner sc = new Scanner(System.in);
        do {
            String enteredString = sc.nextLine();
            printHorizontalLine();
            if (enteredString.equals("bye")) {
                break;
            } else if (enteredString.equals("list")) {
                HelperMethods.listTasks(tasks);
            } else if (enteredString.contains("mark")) {
                changeTaskStatus(tasks, enteredString);
            } else if (enteredString.contains("delete")) {
                deleteTask(tasks, enteredString);
            } else {
                addTaskToList(tasks, enteredString);
            }
        } while (true);
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
