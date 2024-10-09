package task;

import exception.InvalidArgumentException;
import util.Parser;

import java.util.ArrayList;


public final class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    public static boolean isRunning = true;
    public static final class UsageString {
        public static final String LIST_USAGE = "Usage: list";
        public static final String MARK_UNMARK_USAGE = "Usage: mark/unmark <taskNumber>";
        public static final String EVENT_USAGE = "Usage: event <description> /from <from> /to <to>";
        public static final String TODO_USAGE = "Usage: todo <description>";
        public static final String DEADLINE_USAGE = "Usage: deadline <name> /by <time>";
        public static final String DELETE_USAGE = "Usage: delete <taskNumber>";
        public static final String FIND_USAGE = "Usage: find <query>";
        public static final String DEFAULT = "All commands: list, mark, unmark, event, todo, deadline, delete, find";
    }

    public TaskList() {
    }

    public TaskList(ArrayList<Task> t) {
        tasks.addAll(t);
        taskCount = t.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(ArrayList<Task> t) {
        tasks.addAll(t);
        taskCount += t.size();
    }

    public void add(Task t) {
        tasks.add(t);
        taskCount += 1;
    }

    /**
     * List all tasks in the format: <code>[T/D/E][isDone?] &lt;name&gt;
     * (/from &lt;time&gt; /to &lt;time&gt;) (/by &lt;time&gt;) </code>
     */
    public void listTasks() {
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
     * @throws InvalidArgumentException If argString cannot be parsed to <code>int</code>
     */
    public void markUnmarkTask(String action, String argString) throws InvalidArgumentException {
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
        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.MARK_UNMARK_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }

    /**
     * Adds an <code>Event</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param argString Format: <code> &lt;name&gt;
     *                       /from &lt;time&gt; /to &lt;time&gt;</code>
     * @throws InvalidArgumentException If invalid format or any empty argument
     */
    public void addEvent(String argString) throws InvalidArgumentException {
        try {
            String[] argv = Parser.parseEvent(argString);
            String eventName = argv[0];
            String eventFrom = argv[1];
            String eventTo = argv[2];
            tasks.add(new Event(eventName, eventFrom, eventTo));
            System.out.println("Event task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.EVENT_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }

    /**
     * Adds a <code>Todo</code> to list of tasks
     * @param description Task description
     * @throws InvalidArgumentException If description is blank
     */
    public void addTodo(String description) throws InvalidArgumentException{
        try {
            Parser.validateToDo(description); //Doesn't need a parser
            tasks.add(new ToDo(description));
            System.out.println("Todo task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.TODO_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }

    /**
     * Adds a <code>Deadline</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param argString Format: <code> &lt;name&gt; /by &lt;time&gt;</code>
     * @throws InvalidArgumentException If invalid format or any empty argument
     */
    public void addDeadline(String argString) throws InvalidArgumentException {
        try {
            String[] argv = Parser.parseDeadline(argString);
            String dlName  = argv[0];
            String dlTime  = argv[1];
            tasks.add(new Deadline(dlName, dlTime));
            System.out.println("Deadline task added!\n");
            tasks.get(taskCount++).printTask();
        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.DEADLINE_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }

    /**
     * Delete a Task
     * @param argString Task index as <code>String</code>
     * @throws InvalidArgumentException If argString cannot be parsed to <code>int</code>
     */
    public void deleteTask(String argString) throws InvalidArgumentException {
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
        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.DELETE_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }

    public void findTask(String argString) throws InvalidArgumentException {
        try {
            argString = argString.strip().toLowerCase();
            Parser.validateToDo(argString);

            System.out.println("Searching for \"" + argString + "\"...");
            TaskList results = new TaskList();

            for (Task task : tasks) {
                if (task.getCommand().toLowerCase().contains(argString)) {
                    results.add(task);
                }
            }

            System.out.println("Here are the results: \n");
            results.listTasks();

        } catch (InvalidArgumentException e) {
            String msg = e.getMessage() + "\n" + UsageString.FIND_USAGE;
            throw new InvalidArgumentException(msg);
        }
    }


}