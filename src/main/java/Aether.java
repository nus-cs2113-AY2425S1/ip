import java.util.Scanner;

public class Aether {
    private boolean isExit = false;
    private String[] tasks = new String[100];
    private int taskCount = 0;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
    }

    public void run() {
        Display.showStartScreen();
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            System.out.println("You:");
            String command = scanner.nextLine();
            Display.printSeparator();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        switch (command.toLowerCase()) {
        case "bye":
            isExit = true;
            Display.showEndScreen();
            break;
        case "list":
            listTasks();
            break;
        default:
            addTask(command);
            break;
        }
        Display.printSeparator();
    }

    private void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            Display.response("added: " + task);
        } else {
            Display.response("Task list is full. Sorry!");
        }
    }

    private void listTasks() {
        if (taskCount == 0) {
            Display.response("Task list is empty.");
        } else {
            Display.response("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}

class Display {
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____   \n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\  \n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) | \n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /  \n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\  \n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\ \n";
        String startScreen = "Aether:\n"
                + "Hello! I'm Aether, your friendly assistant.\n"
                + "How can I help you today?\n";

        System.out.println(logo);
        printSeparator();
        System.out.println(startScreen);
        printSeparator();
    }

    public static void showEndScreen() {
        String endScreen = "Aether:\n"
                + "Goodbye! Hope to see you again soon!";
        System.out.println(endScreen);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________");
    }

    public static void response(String message) {
        System.out.println("Aether:\n" + message);
    }
}