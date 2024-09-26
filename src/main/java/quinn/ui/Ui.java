package quinn.ui;

import quinn.task.Task;
import quinn.task.TaskList;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Command:");
        System.out.print("\t");
        return sc.nextLine().trim();
    }

    public void displayWelcome() {
        String logo = "\t" + "  QQQ   U   U III N   N N   N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  NN  N NN  N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N N N N N N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N  NN N  NN " + System.lineSeparator()
                + "\t" + "  QQQ    UUU  III N   N N   N " + System.lineSeparator()
                + "\t" + "    Q                       " + System.lineSeparator()
                + "\t" + "     QQ                     " + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Quinn, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + System.lineSeparator()
                + logo
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        displayResponse(welcomeMessage);
        displayLine();
    }

    public void displayExit() {
        String exitMessage = "\t" + "Farewell. Hope to see you again soon!";
        displayResponse(exitMessage);
    }

    public void displayResponse(String message) {
        displayLine();
        System.out.println(message);
    }

    public void displayError(String message) {
        displayLine();
        System.out.println("Error Message:");
        System.out.println("\t" + "('-')? " + message);
    }

    public void displayFilteredTasks(String message) {
        displayLine();
        System.out.println("[FILTERED TASKS]" + System.lineSeparator());
        System.out.println(message);
    }

    public void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskDoneMessage(Task task) {
        return "\t" + "Roger! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskNotDoneMessage(Task task) {
        return "\t" + "OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskDeletedMessage(Task task) {
        return "\t" + "Noted. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String numOfTasksInListMessage(TaskList taskList) {
        return "\t" + "Now you have " + taskList.getNumOfTasks()
                + (taskList.getNumOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    public String tasksInListMessage(TaskList taskList) {
        return "\t" + "Here"
                + (taskList.getNumOfTasks() > 1 ? " are the tasks  " : " is the task ")
                + "in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    public String tasksWithKeywordMessage(TaskList taskList, String keyword) {
        return "\t" + "Here"
                + (taskList.getNumOfFilteredTasks() > 1 ? " are the matching tasks " : " is the matching task ")
                + "in your list:"
                + System.lineSeparator()
                + "\t" + "[Keyword Search: " + keyword + "]"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }
}
