import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import exceptions.IllegalCommandException;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;

import java.util.ArrayList;
import java.util.Scanner;

public class Cy {

    public static final String HORIZONTAL_LINE = "______________________________________";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String LIST_TASKS = "Here are the tasks in your list";
    public static final String CONFIRM_ADD = "Got it. I've added this task:";
    public static final String CONFIRM_DEADLINE = "Got it. I've added this deadline:";
    public static ArrayList<Task> items = new ArrayList<>();


    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markOutput(Task task) {
        printLine();

        if (task.isDone()) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    public static void validateMark(String[] splitInputs, int count) throws IllegalTaskException{
        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                throw new IllegalTaskException("Please enter a valid task number from 1 to " + count);
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException("Please enter a valid number.");
        }

    }

    public static void markItem(String[] splitInputs, int count) throws IllegalTaskException {
        validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);
        markOutput(items.get(index));
    }

    public static void unmarkItem(String[] splitInputs, int count) throws IllegalTaskException {
        validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(false);
        markOutput(items.get(index));
    }

    public static void printList(int count) {
        printLine();
        System.out.println(LIST_TASKS);

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + items.get(i).getStatusIcon() + " " + items.get(i).getDescription());
        }
        printLine();
    }

    public static String trimString(String input) throws IllegalEmptyException {
        String output = input.trim();

        // Split the input into two parts: the command and the description
        String[] outputSubstrings = output.split(" ", 2);

        // Check if the length is less than 2, meaning no description was provided
        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            throw new IllegalEmptyException("Please enter a valid description.");
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }

    public static int addTodo(int count, String input) throws IllegalEmptyException{
        String task = trimString(input);
        items.add(new Todo(task));

        printTodoMessage(count, task);

        return count + 1;
    }

    private static void printTodoMessage(int count, String task) {
        printLine();
        System.out.println(CONFIRM_ADD);
        System.out.println(items.get(count).getStatusIcon() + " " + task);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();
    }

    public static int addDeadline(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        if (!input.contains("by")) {
            throw new IllegalKeywordException("Please enter the by keyword");
        }

        String description = trimString(input);
        String deadline = createDeadlineString(description);
        items.add(new Deadline(deadline));

        printDeadlineMessage(count, deadline);

        return count + 1;
    }

    private static void printDeadlineMessage(int count, String deadline) {
        printLine();
        System.out.println(CONFIRM_DEADLINE);
        System.out.println(items.get(count).getStatusIcon() + " " + deadline);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();
    }

    private static String createDeadlineString(String description) throws IllegalEmptyException {
        String[] descriptionSubstrings = description.split("by", 2);
        String deadline = descriptionSubstrings[0] + "(by:" + descriptionSubstrings[1] + ")";

        if (descriptionSubstrings[1].isEmpty()) {
            throw new IllegalEmptyException("The description of todo cannot be empty!");
        }

        return deadline;
    }

    public static int addEvent(int count, String input) throws IllegalEmptyException{

        input = trimString(input);

        if (!input.contains("from") || !input.contains("to")) {
            System.out.println("Input must contain both 'from' and 'to' keywords.");
            return count;
        }

        String[] splitInputs = input.split("from|to");
        String start = splitInputs[1];
        String end = splitInputs[2];
        String event = splitInputs[0] + "(from:" + start + "to:" + end + ")";

        items.add(new Event(event));

        printLine();
        System.out.println(CONFIRM_DEADLINE);
        System.out.println(items.get(count).getStatusIcon() + " " + event);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();

        return count + 1;
    }

    public static int deleteItem(int count, String[] splitInputs){

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        if (deleteIndex < 0 || deleteIndex >= items.size()) {
            System.out.println("Invalid task number. Please try again.");
            return count;
        }

        Task deleteItem = items.get(deleteIndex);
        items.remove(deleteIndex);

        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
        return count - 1;
    }

    public static void main(String[] args) throws IllegalCommandException,IllegalEmptyException,IllegalTaskException,IllegalKeywordException {
        printWelcomeMessage();

        int count = 0;
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();
            count = handleCommand(input, count, command, splitInputs);
            input = scan.nextLine();
        }

        printEndingMessage();
    }

    private static void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
    }

    private static int handleCommand(String input, int count, String command, String[] splitInputs) throws IllegalEmptyException, IllegalCommandException,IllegalTaskException,IllegalKeywordException {
        if (command.equalsIgnoreCase("list")) {
            printList(count);
        } else if (command.equals(MARK)) {
            markItem(splitInputs, count);
        } else if (command.equals(UNMARK)) {
            unmarkItem(splitInputs, count);
        } else if (command.equals(TODO)) {
            count = addTodo(count, input);
        } else if (command.equals(DEADLINE)) {
            count = addDeadline(count, input);
        } else if (command.equals(EVENT)) {
            count = addEvent(count, input);
        } else if (command.equals(DELETE)){
            count = deleteItem(count, splitInputs);
        } else {
            throw new IllegalCommandException("Please enter a valid command");
        }
        return count;
    }
}