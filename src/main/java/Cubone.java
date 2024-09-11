import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.CuboneMissingParameterError;
public class Cubone {
    static final String LOGO =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
    static final String CHAT_PREFIX = "\n(Cubone) ";
    static final String CHAT_BAR = "---------------------------------";
    
    // dictionary to store command usages
    // static final Dictionary<String, String> COMMAND_USAGES = new Hashtable<String, String>();
    static final HashMap<String, String> COMMAND_USAGES = new HashMap<String, String>() {{
        put("list", "list");
        put("mark", "mark <index>");
        put("unmark", "unmark <index>");
        put("todo", "todo <description>");
        put("deadline", "deadline <description> /by <date>");
        put("event", "event <description> /from <date> /to <date>");
        put("task", "task <description>");
    }};

    static final String USAGE_MSG = "Usage: ";

    // list to store user input
    static ArrayList<Task> inputed_tasks = new ArrayList<Task>();

    /**
     * Prints a welcome message from Cubone.
     * The welcome message includes the Cubone logo and a chat bar.
     */
    public static void sayWelcomeMsg() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Cubone\nWhat can I do for you?");
        System.out.println(CHAT_BAR);
    }
    
    /**
     * Prints a farewell message.
     */
    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the help message.
     * 
     * This method prints the list of commands and their usages.
     */
    public static void printHelp() {
        System.out.println("Here are the commands you can use:");
        for (String key : COMMAND_USAGES.keySet()) {
            System.out.println("    " + key + " - " + COMMAND_USAGES.get(key));
        }
    }

    /**
     * Prints the list of tasks.
     * 
     * This method prints the list of tasks in the format:
     * 1. [Task 1]
     * 2. [Task 2]
     * ...
     * 
     * The tasks are retrieved from the `inputed_tasks` list and are printed with their corresponding index.
     * The list is enclosed between chat bar lines for better visibility.
     */
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputed_tasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputed_tasks.get(i).toString());
        }
    } 

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public static void markTaskAsDone(int index) {
        try{
            inputed_tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + inputed_tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("task not found: " + e.getMessage());
            return;
        } 
    }

    /**
     * Marks a task as undone.
     *
     * @param index the index of the task to be marked as undone
     */
    public static void markTaskAsUndone(int index) {
        try{
            inputed_tasks.get(index).markAsUndone();
            System.out.println("Nice! I've marked this task as undone:\n" + inputed_tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("task not found: " + e.getMessage());
            return;
        }
    }

    /**
     * Adds a new Todo task to the list of tasks.
     *
     * @param description The description of the Todo task.
     */
    public static void addTodoTask(String description) {
        inputed_tasks.add(new Todo(description));
        System.out.println("Got it. I've added this Todo:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString());
        System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
}

    /**
     * Adds a new deadline task to the task list.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public static void addDeadlineTask(String description, String by) {
        inputed_tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this Deadline:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString());
        System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
    }

    /**
     * Adds an event task to the task list.
     *
     * @param description the description of the event task
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public static void addEventTask(String description, String from, String to) {
        inputed_tasks.add(new Event(description, from, to));
        System.out.println("Got it. I've added this Event:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString());
        System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
    }
    
    /**
     * Adds a new task to the task list.
     *
     * @param description The description of the task.
     */
    public static void addTask(String description) {
        inputed_tasks.add(new Task(description));
        System.out.println("Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString());
        System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
    }

    public static void main(String[] args) {
        sayWelcomeMsg();

        // loop for user input
        boolean isWorking = true;
        Scanner sc = new Scanner(System.in);
        while (isWorking) {
            // String input = sc.nextLine();
            String fullCommand = sc.nextLine();
            String[] command = fullCommand.split(" ", 2);
            System.out.println(CHAT_BAR + CHAT_PREFIX);
            switch (command[0]) {
            case "bye":
                // exit loop
                sayBye();
                isWorking = false;
                break;
            case "help":
                // print help message
                printHelp();
                break;
            case "task":
                // add task
                addTask(command[1]);
                break;
            case "list":
                // list all tasks
                listTasks();
                break;
            case "mark":
                // mark task as done
                try {
                    int markIndex = Integer.parseInt(command[1]);
                    markTaskAsDone(markIndex - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                } catch (NumberFormatException e) {
                    System.out.println("☹ Oh No! Invalid index.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                }
                break;
            case "unmark":
                // mark task as undone
                try {
                    if (command.length < 2 || command[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    int unmarkIndex = Integer.parseInt(command[1]);
                    markTaskAsUndone(unmarkIndex - 1);
                } catch (CuboneMissingParameterError e) {
                    System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                } catch (NumberFormatException e) {
                    System.out.println("☹ Oh No! Invalid index.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                }
                break;
            case "todo":
                // add todo task
                try {
                    if (command.length < 2 || command[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    addTodoTask(command[1]);
                } catch (CuboneMissingParameterError e) {
                    System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                }
                break;
            case "deadline":
                // add deadline task
                // usage: deadline <description> /by <date>
                try{
                    if (command.length < 2 || command[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    String[] deadlineCommand = command[1].split(" /by ");

                    if (deadlineCommand.length < 2 || deadlineCommand[0].isBlank() || deadlineCommand[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    addDeadlineTask(deadlineCommand[0], deadlineCommand[1]);
                } catch (CuboneMissingParameterError e) {
                    System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                }
                break;
            case "event":
                // add event task
                // usage: event <description> /from <date> /to <date>
                try{
                    if (command.length < 2 || command[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    String[] eventCommand = command[1].split(" /from ");

                    if (eventCommand.length < 2 || eventCommand[0].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    String[] eventCommand2 = eventCommand[1].split(" /to ");

                    if (eventCommand2.length < 2 || eventCommand2[0].isBlank() || eventCommand2[1].isBlank()) {
                        throw new CuboneMissingParameterError();
                    }

                    addEventTask(eventCommand[0], eventCommand2[0], eventCommand2[1]);
                } catch (CuboneMissingParameterError e) {
                    System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                }
                break;
            default:
                // unknown command
                System.out.println("☹ Oh No! Can't resove this command: " + command[0]);
                break;
            }
            System.out.println(CHAT_BAR);
        }
    }
}
