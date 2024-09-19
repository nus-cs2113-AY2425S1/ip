package bob;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    public static final String SEPARATOR = "____________________________________________________________";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String DEADLINE_BY = " /by ";
    private static final String EVENT_FROM = " /from ";
    private static final String EVENT_TO = " /to ";
    private static final String FILE_PATH = "data/bob.txt";

    public static void main(String[] args) {

        printGreeting();

        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.load();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine();

            if (input.equals(COMMAND_BYE)) {
                storage.save(tasks);
                exit();
                break;
            } else if (input.equals(COMMAND_LIST)) {
                printList(tasks);
            } else if (input.startsWith(COMMAND_MARK)) {
                markTask(input, tasks);
            } else if (input.startsWith(COMMAND_UNMARK)) {
                unmarkTask(input, tasks);
            } else if (input.equals(COMMAND_TODO)) {
                printEmptyDescription("todo");
            } else if (input.startsWith(COMMAND_TODO + " ")) {
                String description = input.substring(COMMAND_TODO.length()).trim();
                if (description.isEmpty()) {
                    printEmptyDescription("todo");
                } else {
                    Task newTask = new ToDo(description);
                    tasks.add(newTask);
                    printAddedTask(tasks);
                    storage.appendTask(newTask);
                }
            } else if (input.equals(COMMAND_DEADLINE)) {
                printEmptyDescription("deadline");
            } else if (input.startsWith(COMMAND_DEADLINE + " ")) {
                String[] components = input.split(DEADLINE_BY);
                String description = components[0].substring(COMMAND_DEADLINE.length()).trim();
                if (description.isEmpty()) {
                    printEmptyDescription("deadline");
                } else {
                    try {
                        String by = components[1];
                        Task newTask = new Deadline(description, by);
                        tasks.add(newTask);
                        printAddedTask(tasks);
                        storage.appendTask(newTask);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printSeparator();
                        System.out.println("Sorry! Please provide a valid deadline with '/by <date/time>'.");
                        printSeparator();
                    }
                }
            } else if (input.equals(COMMAND_EVENT)) {
                printEmptyDescription("event");
            } else if (input.startsWith(COMMAND_EVENT + " ")) {
                int fromIndex = input.indexOf(EVENT_FROM);
                int toIndex = input.indexOf(EVENT_TO);
                String userTypedDescription = input.substring(COMMAND_EVENT.length()).trim();
                if (userTypedDescription.isEmpty()) {
                    printEmptyDescription("event");
                } else {
                    try {
                        String description = input.substring(COMMAND_EVENT.length() +1, fromIndex);
                        String from = input.substring(fromIndex + EVENT_FROM.length(), toIndex);
                        String to = input.substring(toIndex + EVENT_TO.length());
                        Task newTask = new Event(description, from, to);
                        tasks.add(newTask);
                        printAddedTask(tasks);
                        storage.appendTask(newTask);
                    } catch (StringIndexOutOfBoundsException e) {
                        printSeparator();
                        System.out.println("Sorry! Please provide a valid event with '/from <start date/time> /to <end date/time>'.");
                        printSeparator();
                    }
                }
            } else if (input.startsWith(COMMAND_DELETE)) {
                deleteTask(input, tasks);
            }
            else {
                printInvalidInput();
            }
        }
        scanner.close();
    }

    public static void printGreeting() {
        String logo = """
              ____        _
             | |_) \\ ___ | |___
             |  _ //  _  \\   _ \\
             | |_)\\\\ (_) /  |_) |
             |____/ \\___/|_|___/
            """;

        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }
    
    public static void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }
    
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void printList(ArrayList<Task> tasks) {
        printSeparator();
        if (tasks.isEmpty()) {
            System.out.println("Sorry! Your list is empty.");
            printSeparator();
            return;
        }
        System.out.println("Here " + (tasks.size() == 1 ? "is" : "are") + " the " + (tasks.size() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printSeparator();
    }

    private static void markTask(String input, ArrayList<Task> tasks) {
        printSeparator();
        if (tasks.isEmpty()) {
            System.out.println("Sorry! Your list is empty, please add a task before marking as done.");
            printSeparator();
            return;
        }
        try {
            String[] inputsInString = input.split(" ");
            int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
            if (tasks.get(taskIndex).getStatusIcon().equals("X")) {
                System.out.println("Sorry! This task is already marked as done.");
            } else {
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
            }
            printSeparator();
        } catch (NullPointerException e) {
            String[] inputsInString = input.split(" ");
            int inputTaskNumber = Integer.parseInt(inputsInString[1]);
            System.out.println("Sorry! Task " + inputTaskNumber + " is not found in the list.");
            System.out.println("Please input a valid task number from 1 to " + tasks.size());
            printSeparator();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please input a valid task number from 1 to " + tasks.size() + " after inputting the word 'mark'.");
            printSeparator();
        }
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) {
        printSeparator();
        if (tasks.isEmpty()) {
            System.out.println("Sorry! Your list is empty, please add a task before unmarking.");
            printSeparator();
            return;
        }
        try {
            String[] inputsInString = input.split(" ");
            int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
            if (tasks.get(taskIndex).getStatusIcon().equals(" ")) {
                System.out.println("Sorry! This task is already unmarked.");
            } else {
                tasks.get(taskIndex).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
            }
            printSeparator();
        } catch (NullPointerException e) {
            String[] inputsInString = input.split(" ");
            int inputTaskNumber = Integer.parseInt(inputsInString[1]);
            System.out.println("Sorry! Task " + inputTaskNumber + " is not found in the list.");
            System.out.println("Please input a valid task number from 1 to " + tasks.size() + ".");
            printSeparator();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please input a valid task number from 1 to " + tasks.size() + " after inputting the word 'unmark'.");
            printSeparator();
        }
    }

    public static void printEmptyDescription(String taskType) {
        printSeparator();
        if (taskType.equals("event")) {
            System.out.println("Sorry! The description of an " + taskType + " cannot be empty.");
        } else {
            System.out.println("Sorry! The description of a " + taskType + " cannot be empty.");
        }
        printSeparator();
    }

    public static void printAddedTask (ArrayList<Task> tasks) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
        printSeparator();
    }

    public static void deleteTask(String input, ArrayList<Task> tasks) {
        printSeparator();
        if (tasks.isEmpty()) {
            System.out.println("Sorry! Your list is empty, there is nothing to delete.");
            printSeparator();
            return;
        }
        try {
            String[] inputsInString = input.split(" ");
            int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
            Task removedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            printSeparator();
        } catch (NullPointerException e) {
            String[] inputsInString = input.split(" ");
            int inputTaskNumber = Integer.parseInt(inputsInString[1]);
            System.out.println("Sorry! Task " + inputTaskNumber + " is not found in the list.");
            System.out.println("Please input a valid task number from 1 to " + tasks.size() + ".");
            printSeparator();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please input a valid task number from 1 to " + tasks.size() + " after inputting the word 'delete'.");
            printSeparator();
        }
    }

    public static void printInvalidInput() {
        printSeparator();
        System.out.println("Sorry! I don't understand what you mean.");
        printSeparator();
    }
}