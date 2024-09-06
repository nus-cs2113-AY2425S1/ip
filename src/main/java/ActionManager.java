public final class ActionManager {
    private static final int MAX_CAPACITY = 100;
    private static final Task[] tasks = new Task[MAX_CAPACITY];
    private static int taskCount = 0;

    /**
     * List all tasks in the format: <code>[T/D/E][isDone?] &lt;name&gt;
     * (/from &lt;time&gt; /to &lt;time&gt;) (/by &lt;time&gt;) </code>
     */
    private static void listAllTask() {
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf("%d.", i + 1);
            tasks[i].printTask();
        }
    }

    /**
     * Marks/unmarks a task as done
     * @param action <code>mark</code> or <code>unmark</code>
     * @param commandOptions Task index as <code>String</code>
     */
    private static void markUnmarkTask(String action, String commandOptions) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(commandOptions) - 1; //Index starts from 0
            boolean isMark = action.equals("mark");
            tasks[taskIndex].setDone(isMark);
            System.out.printf("I have %sed the following task for you:\n", action);
            tasks[taskIndex].printTask();
        } catch (Exception e) {
            System.out.println("Invalid argument(s): " + e);
            System.out.println("Usage: mark/unmark <taskNumber>");
        }
    }

    /**
     * Adds an <code>Event</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param commandOptions Format: <code> &lt;name&gt;
     *                       /from &lt;time&gt; /to &lt;time&gt;</code>
     */
    private static void addEvent(String commandOptions) {
        try {
            int fromSlashIndex = commandOptions.indexOf("/from");
            int toSlashIndex = commandOptions.indexOf("/to");
            String eventName = commandOptions.substring(0, fromSlashIndex).strip();
            //5 spaces from `fromSlashIndex` since length of "/from" is 5
            String eventFrom = commandOptions.substring(fromSlashIndex + 5, toSlashIndex).strip();
            //3 spaces from `toSlashIndex` since length of "/to" is 3
            String eventTo = commandOptions.substring(toSlashIndex + 3).strip();
            tasks[taskCount] = new Event(eventName, eventFrom, eventTo);
            System.out.println("Deadline task added!\n");
            tasks[taskCount++].printTask();
        } catch (Exception e) {
            System.out.println("Invalid argument(s): " + e);
            System.out.println("Usage: event <description> /from <from> /to <to>");
        }
    }

    /**
     * Adds a <code>Todo</code> in list of tasks
     * @param description Task description
     */
    private static void addTodo(String description) {
        try {
            tasks[taskCount] = new ToDo(description);
            System.out.println("Todo task added!\n");
            tasks[taskCount++].printTask();
        } catch (Exception e) {
            System.out.println("Invalid argument(s): " + e);
            System.out.println("Usage: todo <description>");
        }
    }

    /**
     * Adds a <code>Deadline</code> to list of tasks.
     * Note that <code>&lt;time&gt;</code> is treated as a <code>String</code>
     * @param commandOptions Format: <code> &lt;name&gt; /by &lt;time&gt;</code>
     */
    private static void addDeadline(String commandOptions) {
        try {
            int slashIndex = commandOptions.indexOf("/by");
            String dlName = commandOptions.substring(0, slashIndex - 1);
            //Not take the space before slash
            String dlTime = commandOptions.substring(slashIndex + 4);
            tasks[taskCount] = new Deadline(dlName, dlTime);
            System.out.println("Deadline task added!\n");
            tasks[taskCount++].printTask();
        } catch (Exception e) {
            System.out.println("Invalid argument(s): " + e);
            System.out.println("Usage: deadline <description> /by <date>");
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
     static void process(String command) {
        //Split `command` into `action` and `commandOptions`
        String[] argv = command.split(" ", 2);
        //argv will have at least length of 1 because that's how split() works
        String action = argv[0];

        String commandOptions;
        int argc = argv.length;
        if (argc == 2) {
            commandOptions = argv[1]; //For commands with 1 or more options
        } else {
            commandOptions = ""; //For 0-option commands (E.g. list)
        }

        switch (action) {
        case "bye":
            SuBOT.isRunning = false; //Halt signal for parent class
            break;
        case "list":
            listAllTask();
            break;
        case "mark":
        case "unmark":
            markUnmarkTask(action, commandOptions);
            break;
        case "deadline":
            addDeadline(commandOptions);
            break;
        case "todo":
            addTodo(commandOptions);
            break;
        case "event":
            addEvent(commandOptions);
            break;
        default:
            System.out.println("Invalid action: " + action);
            break;
        }
    }
}