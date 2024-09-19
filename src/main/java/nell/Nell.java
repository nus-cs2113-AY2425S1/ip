package nell;

import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.Task;
import nell.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Nell {
    private static final String UNMARK_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  unmark <task number>
            """;
    private static final String MARK_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  mark <task number>
            """;
    private static final String TODO_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  todo <description>
            """;
    private static final String DEADLINE_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  deadline <description> /by <by-date>
            """;
    private static final String EVENT_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  event <description> /from <from-date> /to <to-date>
            """;
    public static final String INVALID_TASK_MESSAGE = "-> Invalid task!";
    private static final String REMOVE_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  remove <task number>
            """;

    private static TaskList tasks = new TaskList();


    /**
     * Prints out the formatted string for the task at a specified index
     *
     * @param task  The task at the specified index
     * @param index The index of task
     */
    private static void printTaskAtIndex(Task task, int index) {
        System.out.println(String.format("   %d. %s", index, task));
    }

    /**
     * Lists out the currently stored tasks in TaskList
     */
    private static void listTasks() {
        System.out.println("-> The tasks listed are as follows:");
        int taskCount = tasks.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            // Prints all tasks in list
            printTaskAtIndex(tasks.getTaskAtIndex(i), (i + 1));
        }
    }

    /**
     * Adds a todo to the task list
     *
     * @param description The description of the todo
     */
    private static void addToDo(String description) {
        ToDo toDoToAdd = new ToDo(description);
        tasks.addTask(toDoToAdd);
    }

    /**
     * Adds a deadline to the task list
     *
     * @param detail The detail of the deadline
     */
    private static void addDeadline(String detail) {
        try {
            String[] details = detail.split("/by");
            Deadline deadlineToAdd = new Deadline(details[0].trim(), details[1].trim());
            tasks.addTask(deadlineToAdd);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DEADLINE_ERROR_MESSAGE);
        }
    }

    /**
     * Adds an event command to the task list
     *
     * @param detail The detail of the event
     */
    private static void addEvent(String detail) {
        try {
            String[] details = detail.split("/from|/to", 3);
            Event eventToAdd = new Event(details[0].trim(), details[1].trim(), details[2].trim());
            tasks.addTask(eventToAdd);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(EVENT_ERROR_MESSAGE);
        }
    }

    /**
     * Unmarks a task as done
     *
     * @param taskNumber The command body
     */
    private static void unmarkTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            tasks.getTaskAtIndex(taskIndex - 1).setDone(false);
            System.out.println("-> The following task has been marked not done:");
            printTaskAtIndex(tasks.getTaskAtIndex(taskIndex - 1), taskIndex);
        } catch (NumberFormatException e) {
            System.out.print(UNMARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Marks a task as done
     *
     * @param taskNumber The command body
     */
    private static void markTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            tasks.getTaskAtIndex(taskIndex - 1).setDone(true);
            System.out.println("-> The following task has been marked done:");
            printTaskAtIndex(tasks.getTaskAtIndex(taskIndex - 1), taskIndex);
        } catch (NumberFormatException e) {
            System.out.print(MARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Removes a task from the list
     *
     * @param taskNumber The command body
     */
    private static void removeTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            tasks.removeTask(taskIndex - 1);
        } catch (NumberFormatException e) {
            System.out.print(REMOVE_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Handles wrong or wrongly formatted commands
     */
    private static void handleIncorrectInput() {
        System.out.print("""
                -> Invalid command!
                   Please enter one of the following commands:
                      list
                      mark <number>
                      unmark <number>
                      todo <description>
                      deadline <description> /by <by-date>
                      event <description> /from <from-date> /to <to-date>
                      remove <number>
                      bye
                """);
    }

    /**
     * Says bye to the user
     */
    private static void sayBye() {
        System.out.println("-> Bye. Hope to see you again soon!");
    }

    private static void loadFromFile() {
        try {
            File directory = new File("data");
            File dataFile = new File("data/data.txt");

            if (!dataFile.exists()) {
                directory.mkdir();
                dataFile.createNewFile();
            }

            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNext()) {
                String fileTask = fileScanner.nextLine();
                loadTask(fileTask);
            }
        } catch (IOException e) {
            System.out.println("Data not loaded - File not found");
        }
    }

    /**
     * Parses a task from the file
     *
     * @param taskLine The file line containing the task
     */
    private static void loadTask(String taskLine) {
        String[] taskParameters = taskLine.split("\\|");
        String taskType = taskParameters[0];
        String taskDescription = taskParameters[2];

        boolean taskIsDone = taskParameters[1].equals("X");

        switch (taskType) {
        case "T":
            ToDo toDoToAdd = new ToDo(taskDescription, taskIsDone);
            tasks.loadTask(toDoToAdd);
            break;

        case "D":
            String deadlineBy = taskParameters[3];
            Deadline deadlineToAdd = new Deadline(taskDescription, taskIsDone, deadlineBy);
            tasks.loadTask(deadlineToAdd);
            break;

        case "E":
            String eventFrom = taskParameters[3];
            String eventTo = taskParameters[4];
            Event eventToAdd = new Event(taskDescription, taskIsDone, eventFrom, eventTo);
            tasks.loadTask(eventToAdd);
            break;

        default:
            break;
        }
    }

    /**
     * Saves the task list data in a file
     */
    private static void saveToFile() {
        try {
            String filePath = "data/data.txt";
            tasks.writeListToFile(filePath);
        } catch (IOException e) {
            System.out.println("   Data not saved due to error");
        }
    }

    /**
     * Greet the user upon program startup
     */
    private static void greetUser() {
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");
    }

    /**
     * Get commands from user and execute commands received
     */
    private static void getCommands() {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            String[] commandWords = command.split(" ", 2);
            switch (commandWords[0]) {
            case "bye":
                sayBye();
                saveToFile();
                isGettingCommands = false;
                break;

            case "list":
                listTasks();
                break;

            case "mark":
                try {
                    markTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(MARK_ERROR_MESSAGE);
                }
                break;

            case "unmark":
                try {
                    unmarkTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(UNMARK_ERROR_MESSAGE);
                }
                break;

            case "todo":
                try {
                    addToDo(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(TODO_ERROR_MESSAGE);
                }
                break;

            case "deadline":
                try {
                    addDeadline(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(DEADLINE_ERROR_MESSAGE);
                }
                break;

            case "event":
                try {
                    addEvent(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(EVENT_ERROR_MESSAGE);
                }
                break;

            case "remove":
                try {
                    removeTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(REMOVE_ERROR_MESSAGE);
                }
                break;

            default:
                handleIncorrectInput();
                break;
            }
        }
    }

    public static void main(String[] args) {
        loadFromFile();
        greetUser();
        getCommands();
    }
}