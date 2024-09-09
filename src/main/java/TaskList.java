import java.util.Arrays;

public class TaskList {
    private Task[] allTasks;
    private int taskCount;
    private final int MAX_TASKS = 100;
    public static final String SEPARATOR = "\t____________________________________________________________";

//    public TaskList(String task){
//        allTasks = new Task[MAX_TASKS];
//        allTasks[0] = new Task(1, task);
//        taskCount = 1;
//    }

    public TaskList() {
        allTasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static String joinStringArray(String[] array, int from, int to, String delimiter) {
        String[] arrayCopy = Arrays.copyOfRange(array, from, to);
        return String.join(delimiter, arrayCopy);
    }

//    public void addToTaskList(String input) {
//        allTasks[taskCount] = new Task(taskCount + 1, input);
//        taskCount++;
//        System.out.println("\tadded: " + input);
//        printSeparator();
//    }

    public void printTaskList() {
        if (taskCount == 0) {
            System.out.println("\tNo tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) +".");
            System.out.println(allTasks[i]);
        }
    }

    public void printAddedTask() {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + allTasks[taskCount - 1]);
        if (taskCount > 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    public void markTaskAsDone(int id) {
        try {
            if (allTasks[id - 1].isMarkAsDone()) {
                System.out.println("\tGreat! But... the task is already done?");
                System.out.println("\t  " + allTasks[id - 1]);
                return;
            }
            allTasks[id - 1].markTaskAsDone();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\tWell... there is no such task number as " + id);
            return;
        }

        System.out.println("\tSuperb! I've marked this task as done:");
        System.out.println("\t  " + allTasks[id - 1]);
    }

    public void unmarkTaskAsDone(int id) {
        try {
            if (!allTasks[id - 1].isMarkAsDone()) {
                System.out.println("\tOK! But... the task was not marked in the first place.");
                System.out.println("\t  " + allTasks[id - 1]);
                return;
            }
            allTasks[id - 1].unmarkTaskAsDone();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\tWell... there is no such task number as " + id);
            return;
        }

        System.out.println("\tOK, I've unmarked this task as done:");
        System.out.println("\t  " + allTasks[id - 1]);
    }

    public void addTodo(String[] tokens) {
        String description = String.join(" ", tokens);

        allTasks[taskCount] = new Todo(taskCount + 1, description);
        taskCount++;

        printAddedTask();
    }

    public void addDeadline(String[] tokens) {
        int byIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byIndex = i;
                break;
            }
        }

        String description = null;
        String by = null;
        try {
            description = joinStringArray(tokens, 0, byIndex, " ");
            by = joinStringArray(tokens, byIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            System.out.println("\tI can't process this! Hmmm... you seem to have missed out the \"/by\" keyword!");
            System.out.println("\tExample: deadline coding assignment /by 12pm");
            return;
        }

        allTasks[taskCount] = new Deadline(taskCount + 1, description, by);
        taskCount++;

        printAddedTask();
    }

    public void addEvent(String[] tokens) {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromIndex = i;
            } else if (tokens[i].equalsIgnoreCase("/to")) {
                toIndex = i;
                break;
            }
        }

        String description = null;
        String from = null;
        String to = null;
        try {
            description = joinStringArray(tokens, 0, fromIndex, " ");
            from = joinStringArray(tokens, fromIndex + 1, toIndex, " ");
            to = joinStringArray(tokens, toIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            if (fromIndex == -1 && toIndex == -1) {
                System.out.println("\tI can't process this! Hmmm... you seem to have missed out the \"/from\" and \"/to\" keywords!");
            } else if (fromIndex == -1) {
                System.out.println("\tI can't process this! Hmmm... you seem to have missed out the \"/from\" keyword!");
            } else {
                System.out.println("\tI can't process this! Hmmm... you seem to have missed out the \"/to\" keyword!");
            }
            System.out.println("\tExample: event coding lecture /from 2pm /to 4pm");
            return;
        }

        allTasks[taskCount] = new Event(taskCount + 1, description, from, to);
        taskCount++;

        printAddedTask();
    }
}
