import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DianaAssistant {
    private static final String ENCLOSURE = "------------------------------";

    public void interact() {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        String input;
        while (true) {
            input = scanner.nextLine();
            if ("bye".equals(input)) {
                break;
            }
            try {
                switch (processCommand(input)) {
                    case "list":
                        printList(tasks);
                        break;
                    case "mark":
                        toMark(tasks, input, true);
                        break;
                    case "unmark":
                        toMark(tasks, input, false);
                        break;
                    case "todo":
                        addTodo(input, tasks);
                        break;
                    case "event":
                        addEvent(input, tasks);
                        break;
                    case "deadline":
                        addDeadline(input, tasks);
                        break;
                    default:
                        throw new DianaException("Unknown command: " + input);
                }
            } catch (DianaException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Bye! Hope to see you again soon.");
        scanner.close();
    }

    private String processCommand(String input) {
        return input.split(" ")[0];
    }

    private void printWelcomeMessage() {
        printEnclosure();
        System.out.println("Hello, I am Diana! A personal assistant that helps you keep track of your tasks :)");
        System.out.println("Here are the list of things that Diana can do for you");
        printListofCommands();
        printEnclosure();
    }

    private void printListofCommands() {
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark to indicate the completion rate of your task");
        System.out.println("5. Type list to view the your list of tasks.");
        System.out.println("6. Type bye to exit the programme");
    }

    private void printEnclosure() {
        System.out.println(ENCLOSURE);
    }

    private void printList (List<Task> tasks) {
        printEnclosure();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        printEnclosure();
        return;
    }

    private void toMark (List<Task> tasks, String input, boolean shouldMark) {
        try {
            String substring = input.substring(input.indexOf(" ") + 1);
            int TaskNum = Integer.parseInt(substring) - 1;
            if (TaskNum >= 0 && TaskNum < tasks.size()) {
                Task task = tasks.get(TaskNum);
                if (shouldMark) {
                    task.markAsDone();
                    printEnclosure();
                    System.out.println("Nice! I've marked this task as done\n" + task.toString());
                    printEnclosure();
                } else {
                    task.markAsNotDone();
                    printEnclosure();
                    System.out.println("Okay, I've marked this task as not done yet\n" + task.toString());
                    printEnclosure();
                }
            } else {
                System.out.println("Please enter a number between 1 and " + (tasks.size() - 1));
            }
        } catch (NumberFormatException e) {
            System.out.println("Number specified must be an integer");
            System.out.println("For eg: Mark 1 NOT Mark One");
        }
    }

    private void printAddedTask (String description, List<Task> tasks) {
        printEnclosure();
        System.out.println("Got it! I've added this task");
        System.out.println(description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printList(tasks);
    }

    private void addTodo (String input, List<Task> tasks) throws DianaException  {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DianaException("Description of todo task cannot be empty");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(description, tasks);
    }

    private void addDeadline (String input, List<Task> tasks) throws DianaException {
        if (input.contains("/by")) {
            String[] parts = input.split("/by", 2);
            String description = parts[0].substring("deadline".length()).trim();
            String by = parts[1].trim(); // trim removes white space
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            printAddedTask(description, tasks);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: " +
                    "deadline <description> /by <time>");
        }
    }

    private void addEvent (String input, List<Task> tasks) throws DianaException {
        if (input.contains("/from") && input.contains("/to")) {
            String[] parts = input.split("/from", 2);
            String description = parts[0].substring("event".length()).trim();
            String[] fromto = parts[1].split("/to", 2);
            String from = fromto[0].trim();
            String to = fromto[1].trim();
            Task event = new Event(description, from, to);
            tasks.add(event);
            printAddedTask(description, tasks);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: " +
                    "event <description> /from <start time> /to <end time>");
        }
    }
}