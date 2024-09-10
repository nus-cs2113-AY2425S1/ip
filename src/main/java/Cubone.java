import java.util.Scanner;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import Exceptions.CuboneNoDecriptionError;
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
    static final Dictionary<String, String> COMMAND_USAGES = new Hashtable<String, String>();
    static{
        COMMAND_USAGES.put("list", "list");
        COMMAND_USAGES.put("mark", "mark <index>");
        COMMAND_USAGES.put("unmark", "unmark <index>");
        COMMAND_USAGES.put("todo", "todo <description>");
        COMMAND_USAGES.put("deadline", "deadline <description> /by <date>");
        COMMAND_USAGES.put("event", "event <description> /from <date> /to <date>");
        COMMAND_USAGES.put("task", "task <description>");
    }

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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Adds a new Todo task to the list of tasks.
     *
     * @param description The description of the Todo task.
     */
    public static void addTodoTask(String description) {
        try {
            if (description.isEmpty()) {
                throw new CuboneNoDecriptionError("☹ Oh No! The description of a todo cannot be empty.");
            }
            inputed_tasks.add(new Todo(description));
            System.out.println("Got it. I've added this Todo:\n" + inputed_tasks.get(inputed_tasks.size() - 1).toString());
            System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
        } catch (CuboneNoDecriptionError e) {
            System.out.println(e.getMessage() + "\n");
        }
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
                        System.out.println("☹ Oh No! Someting missing, usage: mark <index>");
                    }
                    break;
                case "unmark":
                    // mark task as undone
                    try {
                        int unmarkIndex = Integer.parseInt(command[1]);
                        markTaskAsUndone(unmarkIndex - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ Oh No! Someting missing, usage: unmark <index>");
                    }
                    break;
                case "todo":
                    // add todo task
                    try {
                        addTodoTask(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ Oh No! Someting missing, usage: todo <description>");
                    }
                    break;
                case "deadline":
                    // add deadline task
                    // usage: deadline <description> /by <date>
                    try{
                        String[] deadlineCommand = command[1].split(" /by ");
                        addDeadlineTask(deadlineCommand[0], deadlineCommand[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ Oh No! Someting missing\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                    }
                    break;
                case "event":
                    // add event task
                    // usage: event <description> /from <date> /to <date>
                    try{
                        String[] eventCommand = command[1].split(" /from ");
                        String[] eventCommand2 = eventCommand[1].split(" /to ");
                        addEventTask(eventCommand[0], eventCommand2[0], eventCommand2[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ Oh No! Someting missing\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
                    }
                    break;
                case "task":
                    // add task
                    addTask(command[1]);
                    break;
                default:
                    System.out.println("☹ Oh No! Can't resove this command");
                    break;
            }
            System.out.println(CHAT_BAR);
        }
    }
}
