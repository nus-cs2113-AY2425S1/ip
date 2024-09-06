import java.util.Scanner;
import java.util.ArrayList;

public class Ryan {

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printGreeting();

        while (!exit) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ", 2);
            String Command = splitInput[0];
            String inputBody = splitInput.length > 1 ? splitInput[1] : "";

            switch (Command) {
                case "bye":
                    exit = true;
                    Utils.horizontalLine();
                    break;

                case "tasks":
                    printTasks(tasks);
                    break;

                case "todo":
                    addTodo(tasks, inputBody);
                    break;

                case "deadline":
                    addDeadline(tasks, inputBody);
                    break;

                case "event":
                    addEvent(tasks, inputBody);
                    break;

                case "mark":
                    handleMark(tasks, inputBody);
                    break;

                case "unmark":
                    handleUnmark(tasks, inputBody);
                    break;

                default:
                    addTask(tasks, userInput);
                    break;
            }
        }

        printGoodbye();
        scanner.close();
    }

    private static void printGreeting() {
        Utils.horizontalLine();
        System.out.println("Hello! I'm Ryan");
        System.out.println("What can I do for you?");
        Utils.horizontalLine();
    }

    private static void printTasks(ArrayList<Task> tasks) {
        Utils.horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        Utils.horizontalLine();
    }

    private static void handleMark(ArrayList<Task> tasks, String inputBody) {
        Utils.horizontalLine();
        int index = Integer.parseInt(inputBody) - 1;
        if (isValidIndex(index, tasks.size())) {
            tasks.get(index).mark();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }

        Utils.horizontalLine();
    }

    private static void handleUnmark(ArrayList<Task> tasks, String inputBody) {
        Utils.horizontalLine();
        int index = Integer.parseInt(inputBody) - 1;
        if (isValidIndex(index, tasks.size())) {
            tasks.get(index).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }
        Utils.horizontalLine();
    }

    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }

    private static void addTask(ArrayList<Task> tasks, String userInput) {
        Utils.horizontalLine();
        Task task = new Task(userInput);
        tasks.add(task);
        System.out.println("added: " + userInput);
        Utils.horizontalLine();
    }

    private static void addTodo(ArrayList<Task> tasks, String command) {
        Utils.horizontalLine();
        Task task = new Todo(command);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }

    private static void addDeadline(ArrayList<Task> tasks, String command) {
        Utils.horizontalLine();

        String[] splitCommand = command.split("/by", 2);

        if (splitCommand.length < 2) {
            System.out.println("Error: Deadline tasks should be in the format 'description /by deadline'.");
            Utils.horizontalLine();
            return;
        }

        String description = splitCommand[0].trim();
        String by = splitCommand[1].trim();

        Task task = new Deadline(description, by);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }


    private static void addEvent(ArrayList<Task> tasks, String command) {
        Utils.horizontalLine();

        String[] splitFrom = command.split("/from", 2);

        if (splitFrom.length < 2) {
            System.out.println("Error: Event tasks should be in the format 'description /from start-time /to end-time'.");
            Utils.horizontalLine();
            return;
        }

        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to", 2);

        if (splitTo.length < 2) {
            System.out.println("Error: Event tasks should include both start-time and end-time.");
            Utils.horizontalLine();
            return;
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        Task task = new Event(description, from, to);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }


    private static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        Utils.horizontalLine();
    }
}
