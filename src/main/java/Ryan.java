import java.util.Scanner;
import java.util.ArrayList;
import taskmanager.*;

public class Ryan {

    public static void main(String[] args) {
        boolean isExiting = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = FileManager.loadTasks();

        printGreeting();

        while (!isExiting) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ", 2);
            String command = splitInput[0];
            String inputBody = splitInput.length > 1 ? splitInput[1] : "";

            try {
                switch (command) {
                    case "bye":
                        isExiting = true;
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

                    case "delete":
                        deleteTask(tasks, inputBody);
                        break;

                    case "mark":
                        handleMark(tasks, inputBody);
                        break;

                    case "unmark":
                        handleUnmark(tasks, inputBody);
                        break;

                    default:
                        defaultCase();
                        break;
                }
                FileManager.saveTasks(tasks);
            } catch (RyanException e) {
                handleError(e);
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

    private static void defaultCase() {
        Utils.horizontalLine();
        System.out.println("Invalid command");
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

    private static void handleMark(ArrayList<Task> tasks, String inputBody) throws RyanException {
        Utils.horizontalLine();
        int index = parseIndex(inputBody);
        if (isValidIndex(index, tasks.size())) {
            tasks.get(index).mark();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
        } else {
            throw new RyanException("Invalid task number.");
        }
        Utils.horizontalLine();
    }

    private static void handleUnmark(ArrayList<Task> tasks, String inputBody) throws RyanException {
        Utils.horizontalLine();
        int index = parseIndex(inputBody);
        if (isValidIndex(index, tasks.size())) {
            tasks.get(index).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
        } else {
            throw new RyanException("Invalid task number.");
        }
        Utils.horizontalLine();
    }

    private static int parseIndex(String inputBody) {
        return Integer.parseInt(inputBody) - 1;
    }

    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }

    private static void addTask(ArrayList<Task> tasks, String userInput) throws RyanException {
        if (userInput.trim().isEmpty()) {
            throw new RyanException("Task description cannot be empty");
        }

        Utils.horizontalLine();
        Task task = new Task(userInput);
        tasks.add(task);
        System.out.println("added: " + userInput);
        Utils.horizontalLine();
    }

    private static void addTodo(ArrayList<Task> tasks, String command) throws RyanException {
        if (command.trim().isEmpty()) {
            throw new RyanException("Todo task description cannot be empty.");
        }

        Task task = new Todo(command);
        tasks.add(task);

        Utils.horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }

    private static void addDeadline(ArrayList<Task> tasks, String command) throws RyanException {
        String[] splitCommand = command.split("/by", 2);

        if (splitCommand.length < 2) {
            throw new RyanException("Deadline tasks should be in the format 'description /by deadline'.");
        }

        String description = splitCommand[0].trim();
        String by = splitCommand[1].trim();
        Task task = new Deadline(description, by);
        tasks.add(task);

        Utils.horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }

    private static void addEvent(ArrayList<Task> tasks, String command) throws RyanException {
        String[] splitFrom = command.split("/from", 2);

        if (splitFrom.length < 2) {
            throw new RyanException("Event tasks should be in the format 'description /from start-time /to end-time'.");
        }

        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to", 2);

        if (splitTo.length < 2) {
            throw new RyanException("Event tasks should include both start-time and end-time.");
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();
        Task task = new Event(description, from, to);
        tasks.add(task);

        Utils.horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Utils.horizontalLine();
    }

    public static void deleteTask(ArrayList<Task> tasks, String inputBody) throws RyanException {
        Utils.horizontalLine();
        int index = parseIndex(inputBody);
        if (isValidIndex(index, tasks.size())) {
            Task task = tasks.remove(index);
            System.out.println("OK, I've removed this task:\n" + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new RyanException("Invalid task number.");
        }
        Utils.horizontalLine();
    }

    private static void handleError(RyanException e) {
        Utils.horizontalLine();
        System.out.println("Error: " + e.getMessage());
        Utils.horizontalLine();
    }

    private static void printGoodbye() {
        System.out.println(
                "  GGGGG    OOOOO   OOOOO  DDDDD   BBBBB   Y   Y  EEEEE\n" +
                " G        O     O O     O D    D  B    B   Y Y   E\n" +
                " G  GGG   O     O O     O D    D  BBBBB     Y    EEEE\n" +
                " G    G   O     O O     O D    D  B    B    Y    E\n" +
                "  GGGGG    OOOOO   OOOOO  DDDDD   BBBBB     Y    EEEEE\n");
        Utils.horizontalLine();
    }
}