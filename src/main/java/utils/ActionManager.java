package utils;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;


public final class ActionManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    public static boolean isRunning = true;
    private static final class UsageString {
        private static final String LIST_USAGE = "Usage: list";
        private static final String MARK_UNMARK_USAGE = "Usage: mark/unmark <taskNumber>";
        private static final String EVENT_USAGE = "Usage: event <description> /from <from> /to <to>";
        private static final String TODO_USAGE = "Usage: todo <description>";
        private static final String DEADLINE_USAGE = "Usage: deadline <name> /by <time>";
        private static final String DELETE_USAGE = "Usage: delete <taskNumber>";
        private static final String DEFAULT = "All commands: list, mark, unmark, event, todo, deadline";
    }
    private static final String fileName = "tasks.subot";
    private static final Path savePath = Path.of(System.getProperty("user.dir")
            , fileName);


    /**
     * List all tasks in the format: <code>[T/D/E][isDone?] &lt;name&gt;
     * (/from &lt;time&gt; /to &lt;time&gt;) (/by &lt;time&gt;) </code>
     */
    private static void listAllTask() {
        if (taskCount == 0) {
            System.out.println("No task found");
            return;
        }

        for (int i = 0; i < taskCount; ++i) {
            System.out.printf("%d.", i + 1);
            tasks.get(i).printTask();
        }
    }

    /**
     * Marks/unmarks a task as done
     * @param action <code>mark</code> or <code>unmark</code>
     * @param argString Task index as <code>String</code>
     * @throws SuBotException If argString cannot be parsed to <code>int</code>
     */
    private static void markUnmarkTask(String action, String argString) throws SuBotException {
        try {
            int taskIndex = Parser.parseTaskIndex(argString);
            if (taskIndex >= taskCount) {
                System.out.println("Task not found");
                return;
            }
            boolean isMark = action.equals("mark");
            tasks.get(taskIndex).setDone(isMark);
            System.out.printf("I have %sed the following task for you:\n", action);
            tasks.get(taskIndex).printTask();
        } catch (SuBotException e) {
            String msg = e.getMessage() + "\n" + UsageString.MARK_UNMARK_USAGE;
            throw new SuBotException(msg);
        }
    }

    /**
     * Adds an <code>Event</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param argString Format: <code> &lt;name&gt;
     *                       /from &lt;time&gt; /to &lt;time&gt;</code>
     * @throws SuBotException If invalid format or any empty argument
     */
    private static void addEvent(String argString) throws SuBotException {
        try {
            String[] argv = Parser.parseEvent(argString);
            String eventName = argv[0];
            String eventFrom = argv[1];
            String eventTo = argv[2];
            tasks.add(new Event(eventName, eventFrom, eventTo));
            System.out.println("Event task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (SuBotException e) {
            String msg = e.getMessage() + "\n" + UsageString.EVENT_USAGE;
            throw new SuBotException(msg);
        }
    }

    /**
     * Adds a <code>Todo</code> to list of tasks
     * @param description Task description
     * @throws SuBotException If description is blank
     */
    private static void addTodo(String description) throws SuBotException{
        try {
            Parser.validateToDo(description); //Doesn't need a parser
            tasks.add(new ToDo(description));
            System.out.println("Todo task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (SuBotException e) {
            String msg = e.getMessage() + "\n" + UsageString.TODO_USAGE;
            throw new SuBotException(msg);
        }
    }

    /**
     * Adds a <code>Deadline</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param argString Format: <code> &lt;name&gt; /by &lt;time&gt;</code>
     * @throws SuBotException If invalid format or any empty argument
     */
    private static void addDeadline(String argString) throws SuBotException {
        try {
            String[] argv = Parser.parseDeadline(argString);
            String dlName  = argv[0];
            String dlTime  = argv[1];
            tasks.add(new Deadline(dlName, dlTime));
            System.out.println("Deadline task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (SuBotException e) {
            String msg = e.getMessage() + "\n" + UsageString.DEADLINE_USAGE;
            throw new SuBotException(msg);
        }
    }

    public static void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(savePath.toFile())
            );
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Failed to save tasks to file, retry? (y/n)");
            Scanner sc = new Scanner(System.in);
            char choice = sc.next().charAt(0);
            if (choice == 'y' | choice == 'Y') save();
        } finally {
            System.out.println("Tasks saved to \"" + savePath.toFile() + "\"");
        }
    }

    public static void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(savePath.toFile())
            );
            ArrayList<?> savedTasks = (ArrayList<?>) ois.readObject();
            savedTasks.stream().filter(Objects::nonNull).forEach(
                    task -> { tasks.add((Task) task); taskCount++; }
            );
            ois.close();
            if (taskCount == 0) {
                throw new Exception();
            } else {
                System.out.println("Loaded " + taskCount + " tasks!");
            }
        } catch (Exception e) {
            System.out.println("No save file detected!");
        }
    }

    /**
     * Delete a Task
     * @param argString Task index as <code>String</code>
     * @throws SuBotException If argString cannot be parsed to <code>int</code>
     */
    private static void deleteTask(String argString) throws SuBotException {
        try {
            int taskIndex = Parser.parseTaskIndex(argString); //Index starts from 0
            if (taskIndex >= taskCount) {
                System.out.println("Task not found");
                return;
            }
            Task removed = tasks.remove(taskIndex);
            taskCount--;
            System.out.println("I have deleted the following task for you:\n");
            removed.printTask();
        } catch (SuBotException e) {
            String msg = e.getMessage() + "\n" + UsageString.DELETE_USAGE;
            throw new SuBotException(msg);
        }
    }


    /**
     * Processes user's input command.
     * This method takes the first word in <code>command</code>
     * to determine the action needed.
     * The remaining (if available) will be passed to seperated methods
     * for further processing.
     * @param command Input command
     */
    public static void process(String command) {
        //Split `command` into `action` and `argString`
        String[] argv = command.split(" ", 2);
        //argv will have at least length of 1 because that's how split() works
        String action = argv[0];

        String argString;
        int argc = argv.length;
        if (argc == 2) {
            argString = argv[1]; //For commands with 1 or more options
        } else {
            argString = ""; //For 0-option commands (E.g. list)
        }
        try {
            switch (action) {
            case "bye":
                isRunning = false; //Halt signal for parent class
                break;
            case "list":
                listAllTask();
                break;
            case "mark":
            case "unmark":
                markUnmarkTask(action, argString);
                break;
            case "deadline":
                addDeadline(argString);
                break;
            case "todo":
                addTodo(argString);
                break;
            case "event":
                addEvent(argString);
                break;
            case "delete":
                deleteTask(argString);
                break;
            default:
                throw new SuBotException(action + "\n" + UsageString.DEFAULT);
            }
        } catch (SuBotException e) {
            System.out.println("Invalid argument(s): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
            e.printStackTrace();
        }
    }
}