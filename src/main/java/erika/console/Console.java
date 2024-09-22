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
                String.format("\tNow you have %d task%s in the list.", TaskList.getTaskArraySize(),
                        (TaskList.getTaskArraySize() > 1) ? "s" : "");
        printMessage(message);
    }

    public static void printDeletedMessage(Task deletedTask) {
        String message = "Nice! I've deleted this task:\n" + "\t" + deletedTask + "\n\t" +
                "Now you have " + (TaskList.getTaskArraySize() - 1) + " tasks in the list.";
        printMessage(message);
    }
    public static void printMarkedMessage(Task markedTask) {
        String message = "Nice! I've marked this task as done:\n" + "\t\t" + markedTask;
        printMessage(message);
    }

    public static void printUnmarkedMessage(Task unmarkedTask) {
        String message = "Nice! I've marked this task as not done yet:\n" + "\t\t" + unmarkedTask;
        printMessage(message);
    }

    public static void printGoodbyeMessage() {
        printMessage("Bye! Hope to see you again soon!");
    }
}
