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

            switch(getCommand(input)) {
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
                System.out.println("Unknown command");
                break;
            }
        }

        System.out.println("Bye! Hope to see you again soon.");
        scanner.close();
    }

    private String getCommand(String input) {
        return input.split(" ")[0];
    }

    private void printWelcomeMessage() {
        printEnclosure();
        System.out.println("Hello, I am Diana! A personal assistant that helps you keep track of your tasks :)");
        System.out.println("Here are the list of things that Diana can do for you");
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark to indicate the completion rate of your task");
        System.out.println("5. Type list to view the your list of tasks.");
        System.out.println("6. Type bye to exit the programme");
        printEnclosure();
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

    private void addTodo (String input, List<Task> tasks) {
        String checkTodo = input.trim();
        if (checkTodo.equals("todo")) {
            System.out.println("todo descriptions cannot be empty");
            return;
        }
        String description = input.substring("todo".length()).trim();
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(description, tasks);
    }

    private void addDeadline (String input, List<Task> tasks) {
        if (input.contains("/by")) {
            String[] parts = input.split("/by", 2);
            String description = parts[0].substring("deadline".length()).trim();
            String by = parts[1].trim(); // trim removes white space
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            printAddedTask(description, tasks);

        } else {
            System.out.println("Please enter a valid deadline with the correct format:");
            System.out.println("Eg: Read book /by 5pm");
            return;
        }

    }

    private void addEvent (String input, List<Task> tasks) {
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
            System.out.println("Please enter a valid event with the correct format:");
            System.out.println("Eg: Read book /from 2pm /to 4pm");
            return;
        }
    }
}