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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public static final String CONFIRM_EVENT = "Got it. I've added this event:";
    public static final String CONFIRM_MARK = "Nice! I've marked this task as done:";
    public static final String CONFIRM_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String VALID_NUMBER_WARNING = "Please enter a valid number.";
    public static final String VALID_INDEX_WARNING = "Please enter a valid task number from 1 to ";
    public static final String VALID_DESCRIPTION_WARNING = "Please enter a valid description.";
    public static final String VALID_DEADLINE_KEYWORD_WARNING = "Please enter the by keyword";
    public static final String VALID_EVENT_KEYWORD_WARNING = "Input must contain both 'from' and 'to' keywords.";
    public static final String FILE_PATH = "src/main/java/data/cy.txt";
    public static final String VALID_TASK_TYPE_WARNING = "Please enter a valid task type.";


    public static ArrayList<Task> items = new ArrayList<>();

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markOutput(Task task) {
        printLine();

        if (task.isDone()) {
            System.out.println(CONFIRM_MARK);
        } else {
            System.out.println(CONFIRM_UNMARK);
        }

        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    public static void validateMark(String[] splitInputs, int count) throws IllegalTaskException{
        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                throw new IllegalTaskException(VALID_INDEX_WARNING + count);
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(VALID_NUMBER_WARNING);
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
            throw new IllegalEmptyException(VALID_DESCRIPTION_WARNING);
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }

    public static int addTodo(int count, String input) throws IllegalEmptyException{
        String task = trimString(input);
        items.add(new Todo(task));

        printTodoMessage(count, task);

        saveNewData(input, TODO);
        return count + 1;
    }

    private static void printTodoMessage(int count, String task) {
        printLine();
        System.out.println(CONFIRM_ADD);
        printListUpdate(count, task);
        printLine();
    }

    private static void printListUpdate(int count, String task) {
        System.out.println(items.get(count).getStatusIcon() + " " + task);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
    }

    public static int addDeadline(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        if (!input.contains("by")) {
            throw new IllegalKeywordException(VALID_DEADLINE_KEYWORD_WARNING);
        }

        String description = trimString(input);
        String deadline = createDeadlineString(description);
        items.add(new Deadline(deadline));

        System.out.println(deadline);
        printDeadlineMessage(count, deadline);

        saveNewData(input, DEADLINE);
        return count + 1;
    }

    private static void printDeadlineMessage(int count, String deadline) {
        printLine();
        System.out.println(CONFIRM_DEADLINE);
        printListUpdate(count, deadline);
        printLine();
    }

    private static String createDeadlineString(String description) throws IllegalEmptyException {
        String[] descriptionSubstrings = description.split("by", 2);
        String deadline = descriptionSubstrings[0] + "(by:" + descriptionSubstrings[1] + ")";

        if (descriptionSubstrings[1].isEmpty()) {
            throw new IllegalEmptyException(VALID_DESCRIPTION_WARNING);
        }

        return deadline;
    }

    public static int addEvent(int count, String input) throws IllegalEmptyException, IllegalKeywordException{

        input = trimString(input);

        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(VALID_EVENT_KEYWORD_WARNING);
        }

        String event = createEventString(input);
        items.add(new Event(event));
        printEventMessage(count, event);

        saveNewData(input, EVENT);
        return count + 1;
    }

    private static void printEventMessage(int count, String event) {
        printLine();
        System.out.println(CONFIRM_EVENT);
        printListUpdate(count, event);
        printLine();
    }

    private static String createEventString(String input) {
        String[] splitInputs = input.split("from|to");
        String start = splitInputs[1];
        String end = splitInputs[2];
        String event = splitInputs[0] + "(from:" + start + "to:" + end + ")";

        return event;
    }

    public static int deleteItem(int count, String[] splitInputs) throws IllegalTaskException {

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        if (deleteIndex < 0 || deleteIndex >= items.size()) {
            throw new IllegalTaskException(VALID_INDEX_WARNING + count);
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

    public static int handleCommand(String input, int count, String command, String[] splitInputs) throws IllegalEmptyException, IllegalCommandException,IllegalTaskException,IllegalKeywordException {
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

    private static void printFileContents() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void loadExistingData(){
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, file is not found");
        }
    }

    private static void saveNewData(String input, String taskType) {

        String textToAppend = getString(input, taskType);

        try {
            appendToFile(textToAppend);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }


    private static String getString(String input, String taskType) {
        String textToAppend = "";
        switch (taskType) {
            case TODO:
                textToAppend = "T | 0 | " + input;
                break;
            case DEADLINE:
                String[] splitDeadlineSubstring = input.split("by");
                textToAppend = "D | 0 | " + splitDeadlineSubstring[0] + " | " + splitDeadlineSubstring[1];
                break;
            case EVENT:
                String[] splitEventSubstring = input.split("from|to");
                String start = splitEventSubstring[1];
                String end = splitEventSubstring[2];
                textToAppend = "E | 0 | " + splitEventSubstring[0] + " | " + start + "-" + end;
                break;
            default:
                System.out.println(VALID_TASK_TYPE_WARNING);
        }
        return textToAppend;
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