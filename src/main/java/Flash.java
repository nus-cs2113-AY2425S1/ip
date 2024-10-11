import exception.FlashException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Flash {

    private static List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/flash.txt";
    private static Storage storage;

    public static void markTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = tasks.get(taskNumber);
            task.markDone();
            UI.displayTaskMarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public static void unMarkTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = tasks.get(taskNumber);
            task.markNotDone();
            UI.displayTaskUnmarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public static void todo(String input) throws FlashException {
        if (input.length() <= 5) {
            throw new FlashException("Uh-oh! Description for Todo Needed!! Cannot be left empty.");
        }

        String description = Parser.parseTodo(input);
        if (description.isEmpty()) {
            throw new FlashException("Uh-oh! Description for task.ToDo Needed!! Cannot be left empty.");
        }

        Task task = new ToDo(description);
        tasks.add(task);
        UI.displayTaskAdded(tasks, task);
    }

    public static void deadline(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseDeadline(input);
            String description = parsedInput[0];
            String by = parsedInput[1];
            Task task = new Deadline(description, by);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    public static void event(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseEvent(input);
            String description = parsedInput[0];
            String from = parsedInput[1];
            String to = parsedInput[2];
            Task task = new Event(description, from, to);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    public static void deleteTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            UI.displayTaskDeleted(tasks, task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FlashException("Uh-oh! task.Task number is needed for deletion. Enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public static void main(String[] args) {
        storage = new Storage(FILE_PATH);

        // Load tasks from file
        try {
            tasks = storage.load();
            UI.displayLoadSuccessMessage();
        } catch (FlashException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }

        Scanner in = UI.readCommand();
        UI.displayWelcomeMessage();

        while(true) {
            try {
                String input = in.nextLine();
                String command = Parser.parseCommand(input);

                if (command.equalsIgnoreCase("bye")) {
                    UI.displayByeMessage();
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    UI.displayTasks(tasks);
                } else if (command.equalsIgnoreCase("mark")) {
                    markTask(input);
                    storage.save(tasks);
                } else if (command.equalsIgnoreCase("unmark")) {
                    unMarkTask(input);
                    storage.save(tasks);
                } else if (command.equalsIgnoreCase("todo")) {
                    todo(input);
                    storage.save(tasks);
                } else if (command.equalsIgnoreCase("deadline")) {
                    deadline(input);
                    storage.save(tasks);
                } else if (command.equalsIgnoreCase("event")) {
                    event(input);
                } else if (command.equalsIgnoreCase("delete")) {
                    deleteTask(input);
                    storage.save(tasks);
                } else {
                    throw new FlashException("Uh-oh! I don't know what that means.");
                }
            } catch (FlashException e){
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

}
