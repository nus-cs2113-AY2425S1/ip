package erika.console;

import erika.exception.EmptyListException;
import erika.task.Task;
import erika.tasklist.TaskList;

public class Console {
    private TaskList tasks;

    public Console(TaskList tasks) {
        this.tasks = tasks;
    }

    public static void printWelcomeMessage() {
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                        "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                        "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                        " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                        "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                        "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                        "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("\tHello from\n" + logo + "\n");
        printMessage("Hello! I'm Erika\n \tWhat can I do for you?");
    }

    public static void printMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    public static void printAddedMessage(Task newTask) {
        String message = "Got it. I've added this task:\n" + "\t  " + newTask + "\n" +
                String.format("\tNow you have %d task%s in the list.", Task.getTaskArraySize(),
                        (Task.getTaskArraySize() > 1) ? "s" : "");
        printMessage(message);
    }

    public static void printDeletedMessage(TaskList tasks) {
        String message = "Nice! I've deleted this task:\n" + "\t" + tasks.get(Task.markIndex - 1);
        printMessage(message);
    }
    public void printMarkedMessage(TaskList tasks) {
        String message = "Nice! I've marked this task as done:\n" + "\t\t" + tasks.get(Task.markIndex - 1);
        printMessage(message);
    }

    public void printUnmarkedMessage(TaskList tasks) {
        String message = "Nice! I've marked this task as not done yet:\n" + "\t\t" + tasks.get(Task.markIndex - 1);
        printMessage(message);
    }

    public static void printGoodbyeMessage() {
        printMessage("Bye! Hope to see you again soon!");
    }

    public void printList(TaskList tasks) throws EmptyListException {
        String message = "";
        if (Task.getTaskArraySize() == 0) {
            throw new EmptyListException();
        }
        message += "Here are the tasks in your list:\n";
        for (int i = 0; i < Task.getTaskArraySize(); i++) {
            message += "\t" + Integer.toString(i + 1) + "." + tasks.get(i);
            if (i < Task.getTaskArraySize() - 1) {
                message += "\n";
            }
        }
        printMessage(message);
    }
}
