import java.util.Scanner;

import Commands.Command;
import Commands.CommandResult;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.CuboneMissingParameterError;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import TasksList.TasksList;
import Ui.Ui;
import utils.*;

public class Cubone {
    
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
        put("delete", "delete <index>");
        put("bye", "bye");
    }};

    static final String USAGE_MSG = "Usage: ";

    // list to store user input
    static TasksList tasksList = new TasksList();

    

    static ArrayList<Task> inputed_tasks = new ArrayList<Task>();
    static Boolean LogFileRead = false;
    // read the tasks from the file
    static {
        try {
            // inputed_tasks = Storage.readLogFile();
            tasksList.setTasksList(Storage.readLogFile());
            if (tasksList.size() > 0) 
                LogFileRead = true;
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * Deletes a task from the task list.
     *
     * @param index the index of the task to be deleted
     */
    public static void deleteTask(int index) {
        try{
            Task deletedTask = inputed_tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n" + deletedTask.toString());
            System.out.println("now you have " + inputed_tasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("task not found: " + e.getMessage());
            return;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasksList);
            CommandResult result = command.execute();
            // storage.save(addressBook);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Ui ui;
    public static void main(String[] args) {
        Cubone cuboneInstance = new Cubone();
        cuboneInstance.ui = new Ui();
        cuboneInstance.ui.showWelcomeMsg(LogFileRead, tasksList.getTasksList());

        // loop for user input
        boolean isWorking = true;
        // Scanner sc = new Scanner(System.in);
        Command command;
        while (isWorking) {
            String userCommandText = cuboneInstance.ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = cuboneInstance.executeCommand(command);
            // recordResult(result);
            cuboneInstance.ui.showResultToUser(result);
            // String input = sc.nextLine();
            // String fullCommand = sc.nextLine();
            // String[] command = fullCommand.split(" ", 2);
            // cuboneInstance.ui.showLine(true);
            // switch (command[0]) {
            // case "bye":
            //     // exit loop
            //     cuboneInstance.ui.showBye();
            //     isWorking = false;
            //     break;
            // case "help":
            //     // print help message
            //     printHelp();
            //     break;
            // case "task":
            //     // add task
            //     addTask(command[1]);
            //     break;
            // case "list":
            //     // list all tasks
            //     listTasks();
            //     break;
            // case "mark":
            //     // mark task as done
            //     try {
            //         int markIndex = Integer.parseInt(command[1]);
            //         markTaskAsDone(markIndex - 1);
            //     } catch (IndexOutOfBoundsException e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     } catch (NumberFormatException e) {
            //         System.out.println("☹ Oh No! Invalid index.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // case "unmark":
            //     // mark task as undone
            //     try {
            //         if (command.length < 2 || command[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }

            //         int unmarkIndex = Integer.parseInt(command[1]);
            //         markTaskAsUndone(unmarkIndex - 1);
            //     } catch (CuboneMissingParameterError e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     } catch (NumberFormatException e) {
            //         System.out.println("☹ Oh No! Invalid index.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // case "todo":
            //     // add todo task
            //     try {
            //         if (command.length < 2 || command[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         addTodoTask(command[1]);
            //     } catch (CuboneMissingParameterError e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // case "deadline":
            //     // add deadline task
            //     // usage: deadline <description> /by <date>
            //     try{
            //         if (command.length < 2 || command[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         String[] deadlineCommand = command[1].split(" /by ");
            //         if (deadlineCommand.length < 2 || deadlineCommand[0].isBlank() || deadlineCommand[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         addDeadlineTask(deadlineCommand[0], deadlineCommand[1]);
            //     } catch (CuboneMissingParameterError e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // case "event":
            //     // add event task
            //     // usage: event <description> /from <date> /to <date>
            //     try{
            //         if (command.length < 2 || command[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         String[] eventCommand = command[1].split(" /from ");
            //         if (eventCommand.length < 2 || eventCommand[0].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         String[] eventCommand2 = eventCommand[1].split(" /to ");
            //         if (eventCommand2.length < 2 || eventCommand2[0].isBlank() || eventCommand2[1].isBlank()) {
            //             throw new CuboneMissingParameterError();
            //         }
            //         addEventTask(eventCommand[0], eventCommand2[0], eventCommand2[1]);
            //     } catch (CuboneMissingParameterError e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // case "delete":
            //     // delete task
            //     try {
            //         int deleteIndex = Integer.parseInt(command[1]);
            //         deleteTask(deleteIndex - 1);
            //     } catch (IndexOutOfBoundsException e) {
            //         System.out.println("☹ Oh No! Missing parameter.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     } catch (NumberFormatException e) {
            //         System.out.println("☹ Oh No! Invalid index.\n" + USAGE_MSG + COMMAND_USAGES.get(command[0]));
            //     }
            //     break;
            // default:
            //     // unknown command
            //     System.out.println("☹ Oh No! Can't resove this command: " + command[0]);
            //     break;
            // }
            cuboneInstance.ui.showLine();
            // log the tasks to the file
            Storage.updateLogFile(tasksList.getTasksList());
        }
    }
}
