import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import constants.Warnings;
import exceptions.IllegalCommandException;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import constants.Utils;
import constants.Statements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cy {


    public static ArrayList<Task> items = new ArrayList<>();

    public static void printLine() {
        System.out.println(Utils.HORIZONTAL_LINE);
    }

    public static void printMarkOutput(Task task) {
        printLine();

        if (task.isDone()) {
            System.out.println(Statements.CONFIRM_MARK);
        } else {
            System.out.println(Statements.CONFIRM_UNMARK);
        }

        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    public static void validateMark(String[] splitInputs, int count) throws IllegalTaskException {
        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + count);
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(Warnings.VALID_NUMBER_WARNING + count);
        }

    }

    public static void markItem(String[] splitInputs, int count) throws IllegalTaskException {
        validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);

        printMarkOutput(items.get(index));
    }

    public static void unmarkItem(String[] splitInputs, int count) throws IllegalTaskException {
        validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(false);
        printMarkOutput(items.get(index));
    }

    public static void printList(int count) {
        printLine();
        System.out.println(Statements.LIST_TASKS);

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
            throw new IllegalEmptyException(Warnings.VALID_DESCRIPTION_WARNING);
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }

    public static int addTodo(int count, String input) throws IllegalEmptyException {
        String task = trimString(input);
        Todo todo = new Todo(task);
        items.add(todo);

        printTodoMessage(count, todo.createTodoList());

        saveNewData(todo.createTodoTxt(), Utils.TODO);
        return count + 1;
    }

    private static void printTodoMessage(int count, String task) {
        printLine();
        System.out.println(Statements.CONFIRM_ADD);
        printListUpdate(count, task);
        printLine();
    }

    private static void printListUpdate(int count, String task) {
        System.out.println(task);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
    }

    public static int addDeadline(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        if (!input.contains("by")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }

        String description = trimString(input);
        String[] descriptionSubstrings = description.split("by", 2);
        String deadlineDescription = descriptionSubstrings[0].trim();
        String by  = descriptionSubstrings[1].trim();

        Deadline deadline = new Deadline(deadlineDescription, by);
        items.add(deadline);

        printDeadlineMessage(count, deadline.createDeadlineList());

        saveNewData(deadline.createDeadlineTxt(), Utils.DEADLINE);
        return count + 1;
    }

    private static void printDeadlineMessage(int count, String deadline) {
        printLine();
        System.out.println(Statements.CONFIRM_DEADLINE);
        printListUpdate(count, deadline);
        printLine();
    }


    public static int addEvent(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        input = trimString(input);

        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        String[] splitInputs = input.split("from|to");
        String start = splitInputs[1].trim();
        String end = splitInputs[2].trim();
        String eventDescription = splitInputs[0].trim();

        Event event = new Event(eventDescription,start, end);
        items.add(event);

        printEventMessage(count, event.createEventList());

        saveNewData(event.createEventTxt(), Utils.EVENT);
        return count + 1;
    }

    private static void printEventMessage(int count, String event) {
        printLine();
        System.out.println(Statements.CONFIRM_EVENT);
        printListUpdate(count, event);
        printLine();
    }


    public static int deleteItem(int count, String[] splitInputs) throws IllegalTaskException {

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        if (deleteIndex < 0 || deleteIndex >= items.size()) {
            throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + count);
        }

        Task deleteItem = items.get(deleteIndex);
        items.remove(deleteIndex);

        printDeleteMessage(deleteItem);
        return count - 1;
    }

    private static void printDeleteMessage(Task deleteItem) {
        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
    }

    public static void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        System.out.println("But first, let me load your previous submissions!");
    }

    public static int handleCommand(String input, int count, String command, String[] splitInputs) throws IllegalEmptyException, IllegalCommandException, IllegalTaskException, IllegalKeywordException {
        if (command.equalsIgnoreCase("list")) {
            printList(count);
        } else if (command.equals(Utils.MARK)) {
            markItem(splitInputs, count);
        } else if (command.equals(Utils.UNMARK)) {
            unmarkItem(splitInputs, count);
        } else if (command.equals(Utils.TODO)) {
            count = addTodo(count, input);
        } else if (command.equals(Utils.DEADLINE)) {
            count = addDeadline(count, input);
        } else if (command.equals(Utils.EVENT)) {
            count = addEvent(count, input);
        } else if (command.equals(Utils.DELETE)) {
            count = deleteItem(count, splitInputs);
        } else {
            throw new IllegalCommandException("Please enter a valid command");
        }
        return count;
    }

    private static void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void createFile(){
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
    //print out existing data from
    private static void loadExistingData() {
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }


    private static void saveNewData(String input, String taskType) {
        try {
            appendToFile(input);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Utils.FILE_PATH, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }


    public static void main(String[] args) throws IllegalCommandException, IllegalEmptyException, IllegalTaskException, IllegalKeywordException {
        printWelcomeMessage();

        loadExistingData();

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
}