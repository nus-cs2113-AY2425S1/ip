import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import exceptions.IllegalCommandException;
import exceptions.IllegalEmptyException;

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

    public static boolean isMarkError(String[] splitInputs, int count) {

        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                System.out.println("Please enter a valid task number from 1 to " + count);
                return true;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return true;
        }

        return false;
    }

    public static void markItem(String[] splitInputs, int count) {
        if (isMarkError(splitInputs, count)) {
            return;
        }

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);
        markOutput(items.get(index));
    }

    public static void unmarkItem(String[] splitInputs, int count) {
        if (isMarkError(splitInputs, count)) {
            return;
        }

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

        String[] outputSplit= output.split(" ",2);

        if (outputSplit.length < 2 || outputSplit[1].isEmpty()) {
            throw new IllegalEmptyException("Please enter a valid description");
        }

        return outputSplit[1];
    }

    public static int addTodo(int count, String input) throws IllegalEmptyException{

        input = trimString(input);
        items.add(new Todo(input));

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(items.get(count).getStatusIcon() + " " + input);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();

        return count + 1;
    }

    public static int addDeadline(int count, String input) throws IllegalEmptyException{

        if (!input.contains("by")) {
            System.out.println("Input must contain 'by' keyword.");
            return count;
        }

        input = trimString(input);

        if (input.isEmpty()) {
            System.out.println("The description of todo cannot be empty!");
            throw new IllegalEmptyException();
        }

        String[] splitInputs = input.split("by", 2);
        String deadline = splitInputs[0] + "(by:" + splitInputs[1] + ")";

        items.add(new Deadline(deadline));

        printLine();
        System.out.println("Got it. I've added this deadline:");
        System.out.println(items.get(count).getStatusIcon() + " " + deadline);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();

        return count + 1;
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
        System.out.println("Got it. I've added this deadline:");
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

    public static void main(String[] args) throws IllegalCommandException,IllegalEmptyException {
        welcomeMessage();

        int count = 0;
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();
            count = handleCommand(input, count, command, splitInputs);
            input = scan.nextLine();
        }

        endingMessage();
    }

    private static void endingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void welcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
    }

    private static int handleCommand(String input, int count, String command, String[] splitInputs) throws IllegalEmptyException, IllegalCommandException {
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
            System.out.println("Please enter a valid command");
            throw new IllegalCommandException();
        }
        return count;
    }
}