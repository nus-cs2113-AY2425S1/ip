package quinn.ui;

import quinn.task.Task;

import java.util.List;
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

        String welcomeMessage = "\t" + "Hello! I'm quinn.Quinn, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + System.lineSeparator()
                + logo
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        displayResponse(welcomeMessage);
        displayLine();
    }

    public void displayExit() {
        String exitMessage = "\t" + "Bye. Hope to see you again soon!";
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

    public void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this quinn.task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this quinn.task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskNotDoneMessage(Task task) {
        return "\t" + "OK, I've marked this quinn.task as not done yet:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String numOfTasksInListMessage(List<Task> tasks) {
        return "\t" + "Now you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    public String tasksInListMessage(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("\t")
                .append(tasks.size() > 1 ? "Here are the tasks in your list:" : "Here is the quinn.task in your list:")
                .append(System.lineSeparator())
                .append("\t")
                .append("[Legend: T = todo, D = deadline, E = event]")
                .append(System.lineSeparator());

        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                sb.append(System.lineSeparator());
            }

            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i));
        }

        return sb.toString();
    }
}
