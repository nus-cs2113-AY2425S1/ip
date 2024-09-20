package nell;

import nell.common.Messages;
import nell.storage.Storage;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;

import java.util.Scanner;

public class Nell {
    private static TaskList tasks = new TaskList();
    private static Storage dataStorage = new Storage("./data/data.txt", tasks);

    /**
     * Lists out the currently stored tasks in TaskList
     */
    private static void listTasks() {
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        int taskCount = tasks.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            // Prints all tasks in list
            System.out.println(tasks.getTaskStringAtIndex(i));
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
            System.out.println(Messages.DEADLINE_ERROR_MESSAGE);
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
            System.out.println(Messages.EVENT_ERROR_MESSAGE);
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
            System.out.println(Messages.UNMARK_MESSAGE);
            System.out.println(tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (NumberFormatException e) {
            System.out.print(Messages.UNMARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
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
            System.out.println(Messages.MARK_MESSAGE);
            System.out.println(tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (NumberFormatException e) {
            System.out.print(Messages.MARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
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
            System.out.print(Messages.REMOVE_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Handles wrong or wrongly formatted commands
     */
    private static void handleIncorrectInput() {
        System.out.print(Messages.INVALID_COMMAND_MESSAGE);
    }

    /**
     * Says bye to the user
     */
    private static void sayBye() {
        System.out.println(Messages.BYE_MESSAGE);
    }
    
    /**
     * Greet the user upon program startup
     */
    private static void greetUser() {
        System.out.println(Messages.HELLO_MESSAGE);
        System.out.println(Messages.PROMPT_MESSAGE);
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
                isGettingCommands = false;
                break;

            case "list":
                listTasks();
                break;

            case "mark":
                try {
                    markTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.MARK_ERROR_MESSAGE);
                }
                break;

            case "unmark":
                try {
                    unmarkTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.UNMARK_ERROR_MESSAGE);
                }
                break;

            case "todo":
                try {
                    addToDo(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.TODO_ERROR_MESSAGE);
                }
                break;

            case "deadline":
                try {
                    addDeadline(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.DEADLINE_ERROR_MESSAGE);
                }
                break;

            case "event":
                try {
                    addEvent(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.EVENT_ERROR_MESSAGE);
                }
                break;

            case "remove":
                try {
                    removeTask(commandWords[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(Messages.REMOVE_ERROR_MESSAGE);
                }
                break;

            default:
                handleIncorrectInput();
                break;
            }
            dataStorage.saveToFile();
        }
    }

    public static void main(String[] args) {
        dataStorage.loadFromFile();
        greetUser();
        getCommands();
    }
}