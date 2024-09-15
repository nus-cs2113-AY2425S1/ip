package mong;

import mong.exception.IllegalTaskFormatException;
import mong.exception.IllegalTaskTypeException;
import mong.task.Deadline;
import mong.task.Event;
import mong.task.Task;
import mong.task.TaskType;
import mong.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Mong {

    public static final int LENGTH_DEADLINE = 8;
    public static final int LENGTH_BY = 3;
    public static final int LENGTH_TODO = 4;
    public static final int LENGTH_EVENT = 5;
    public static final int LENGTH_FROM = 4;
    public static final int LENGTH_TO = 3;
    public static final String HORIZONTAL_LINE = "--------------------------------------------------";

    /**
     * Marks item in index as completed.
     */
    public static void mark(String input, ArrayList<Task> list) {
        // the itemIndex is -1 than the input from the user
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            list.get(itemIndex).setCompleted(true);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        }
    }

    /**
     * Marks completed item in index as incompleted.
     */
    public static void unmark(String input, ArrayList<Task> list) {
        // the itemIndex is -1 than the input from the user
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (itemIndex < 0 || itemIndex >= list.size()) {
            throw new NullPointerException();
        }
        try {
            list.get(itemIndex).setCompleted(false);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
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
    public static void printIndexedList(String input, ArrayList<Task> list) throws IllegalTaskFormatException {
        if (!input.contentEquals("list")) {
            throw new IllegalTaskFormatException("Task format is incorrect!");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println("MONG my god! You have " + list.size() + " task(s) in the list!");
    }

    /**
     * Add a Task of type Deadline.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(ArrayList<Task> list, String input) {
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
     * Add a Task of type Todo.
     * A todo consists of the task description only.
     */
    public static void addTodo(ArrayList<Task> list, String input) {
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
    public static void addEvent(ArrayList<Task> list, String input) {
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
                    printIndexedList(input, list);
                } catch (IllegalTaskFormatException e) {
                    System.out.println("Oi oi MONG! Task format is incorrect...");
                }
                break;
            case MARK:
                mark(input, list);
                break;
            case UNMARK:
                unmark(input, list);
                break;
            case DEADLINE:
                addDeadline(list, input);
                break;
            case TODO:
                addTodo(list, input);
                break;
            case EVENT:
                addEvent(list, input);
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

    public static void main(String[] args) {
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
    }
}
