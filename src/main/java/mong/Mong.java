package mong;

import mong.exception.IllegalTaskFormatException;
import mong.exception.IllegalTaskTypeException;
import mong.task.Deadline;
import mong.task.Event;
import mong.task.Task;
import mong.task.TaskType;
import mong.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Mong {
    public static final String FILE_PATH = "src/main/java/mong/data/mong.txt";
    public static ArrayList<Task> list = new ArrayList<>();
    public static final int LENGTH_DEADLINE = 8;
    public static final int LENGTH_BY = 3;
    public static final int LENGTH_TODO = 4;
    public static final int LENGTH_EVENT = 5;
    public static final int LENGTH_FROM = 4;
    public static final int LENGTH_TO = 3;
    public static final String HORIZONTAL_LINE = "--------------------------------------------------";

    /**
     * Writes new content to txt file.
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Formats latest list to txt file format.
     */
    public static String parseListToTxt() {
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : list) {
            textToAdd.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return textToAdd.toString();
    }

    /**
     * Adds tasks line by line from txt file.
     */
    public static void loadLineContents(String line) {
        String[] lineData = line.split(" \\| ");
        if (lineData[0].contentEquals("T")) {
            addTodo(lineData[1], lineData[2]);
        } else if (lineData[0].contentEquals("D")) {
            addDeadline(lineData[1], lineData[2], lineData[3]);
        } else if (lineData[0].contentEquals("E")) {
            addEvent(lineData[1], lineData[2], lineData[3], lineData[4]);
        }
    }

    /**
     * Adds all tasks in txt file.
     */
    public static void loadFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            loadLineContents(line);
        }
    }

    /**
     * Marks item in index as completed.
     */

    public static void mark(String input) {
        // the itemIndex is -1 than the input from the user
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (itemIndex < 0 || itemIndex >= list.size()) {
            throw new NullPointerException();
        }
        try {
            list.get(itemIndex).setCompleted(true);
            System.out.println("Mong >_<!!I have marked it as completed:");
            System.out.println(list.get(itemIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        }
    }

    /**
     * Marks completed item in index as incompleted.
     */
    
    public static void unmark(String input) {
        // the itemIndex is -1 than the input from the user
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (itemIndex < 0 || itemIndex >= list.size()) {
            throw new NullPointerException();
        }
        try {
            list.get(itemIndex).setCompleted(false);
            System.out.println("Mong-mong :<! The task has been unmarked:");
            System.out.println(list.get(itemIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        }
    }
    /**
     * Prints a horizontal line with width of 50 characters.
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out an indexed list of commands given to Mong starting from 1.
     */
    
    public static void printIndexedList(String input) throws IllegalTaskFormatException {
        if (!input.contentEquals("list")) {
            throw new IllegalTaskFormatException("Task format is incorrect!");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println("MONG my god! You have " + list.size() + " task(s) in the list!");
    }

    /**
     * Add a Task of type Deadline from txt file.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(String isCompleted, String description, String by) {
        list.add(new Deadline(description, by));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Deadline.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(String input) {
        int endOfCommand = input.indexOf("deadline") + LENGTH_DEADLINE;
        int endOfBy = input.lastIndexOf("/by") + LENGTH_BY;
        int startOfBy = input.indexOf("/by");
        try {
            String description = input.substring(endOfCommand + 1, startOfBy);
            String deadline = input.substring(endOfBy + 1);
            list.add(new Deadline(description, deadline));
            System.out.println("Mong-ed! This item has been added: ");
            System.out.println(list.get(list.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! No description or deadline?@#!");
        }
    }

    /**
     * Add a Task of type Todo from txt file.
     * A todo consists of the task description only.
     */
    public static void addTodo(String isCompleted, String description) {
        list.add(new Todo(description));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Todo.
     * A todo consists of the task description only.
     */
    public static void addTodo(String input) {
        int endOfCommand = input.indexOf("todo") + LENGTH_TODO;
        try {
            String description = input.substring(endOfCommand + 1);
            list.add(new Todo(description));
            System.out.println("Mong-ed! This item has been added: ");
            System.out.println(list.get(list.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! No description?@#!");
        }
    }

    /**
     * Add a Task of type Event.
     * An event consists of the task description and from/to period in String format.
     */
    public static void addEvent(String isCompleted, String description, String from, String to) {
        list.add(new Event(description, from, to));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Event.
     * An event consists of the task description and from/to period in String format.
     */
    public static void addEvent(String input) {
        int endOfCommand = input.indexOf("event") + LENGTH_EVENT;
        int endOfFrom = input.lastIndexOf("/from") + LENGTH_FROM;
        int startOfFrom = input.indexOf("/from");
        int endOfTo = input.lastIndexOf("/to") + LENGTH_TO;
        int startOfTo = input.indexOf("/to");
        try {
            String description = input.substring(endOfCommand + 1, startOfFrom - 1);
            String from = input.substring(endOfFrom + 2, startOfTo - 1);
            String to = input.substring(endOfTo + 1);
            list.add(new Event(description, from, to));
            System.out.println("Mong-ed! This item has been added: ");
            System.out.println(list.get(list.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! Missing description, /from or /to commands...");
        }
    }

    /**
     * Delete a Task of any valid type.
     */
    public static void deleteTask(String input) {
        int indexValue = Integer.parseInt(input.split(" ")[1]) - 1;
        if (indexValue < 0 || indexValue >= list.size()) {
            throw new NullPointerException();
        }
        try {
            Task deletedTask = list.get(indexValue);
            list.remove(indexValue);
            System.out.println("Mong-?!@# This item has been removed: ");
            System.out.println(deletedTask);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        }
    }

    /**
     * Returns the TaskType enum value of the first word in the input.
     */
    public static TaskType decipherTaskType(String command) throws IllegalTaskTypeException {
        try {
            return TaskType.fromCommand(command);
        } catch (IllegalTaskTypeException e) {
            throw new IllegalTaskTypeException(e.getMessage());
        }
    }

    /**
     * Adds the command sent by the user into a list.
     * If the command "list" is sent, the list of previous commands will be printed.
     * If the command's first word is "mark", it will set the isCompleted variable of the Task to true.
     * If the command's first word is "unmark", it will set the isCompleted variable of the Task to false.
     * If the command "bye" is sent, the program will exit.
     */
    public static void addByTask() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        TaskType command;
        try {
            command = decipherTaskType(input.split(" ")[0]);
        } catch (IllegalTaskTypeException e) {
            command = TaskType.INVALID;
        }
        while (command != TaskType.BYE) {
            printHorizontalLine();
            switch(command) {
            case LIST:
                // print items in an indexed list
                try {
                    printIndexedList(input);
                } catch (IllegalTaskFormatException e) {
                    System.out.println("Oi oi MONG! Task format is incorrect...");
                }
                break;
            case MARK:
                mark(input);
                break;
            case UNMARK:
                unmark(input);
                break;
            case DEADLINE:
                addDeadline(input);
                break;
            case TODO:
                addTodo(input);
                break;
            case EVENT:
                addEvent(input);
                break;
            case DELETE:
                deleteTask(input);
                break;
            default:
                System.out.println("MooONG?! That's not a valid command...");
                break;
            }
            printHorizontalLine();
            input = in.nextLine();
            try {
                command = decipherTaskType(input.split(" ")[0]);
            } catch (IllegalTaskTypeException e) {
                command = TaskType.INVALID;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File directory = new File("src/main/java/mong/data/");
        File file = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        }
        try {
            loadFileContents(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        String logo = "\n" +
                "\n" +
                "___  ___                  _ \n" +
                "|  \\/  |                 | |\n" +
                "| .  . | ___  _ __   __ _| |\n" +
                "| |\\/| |/ _ \\| '_ \\ / _` | |\n" +
                "| |  | | (_) | | | | (_| |_|\n" +
                "\\_|  |_/\\___/|_| |_|\\__, (_)\n" +
                "                     __/ |  \n" +
                "                    |___/   \n" +
                "\n";
        printHorizontalLine();
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
        addByTask();
        printHorizontalLine();
        System.out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
        try {
            writeToFile(parseListToTxt());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
