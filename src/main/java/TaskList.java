import java.util.Arrays;

public class TaskList {
    private Task[] allTasks;
    private int taskCount;
    private final int MAX_TASKS = 100;
    public static final String SEPARATOR = "\t____________________________________________________________";

    public TaskList(String task){
        allTasks = new Task[MAX_TASKS];
        allTasks[0] = new Task(1, task);
        taskCount = 1;
    }

    public TaskList() {
        allTasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void addToTaskList(String input) {
        allTasks[taskCount] = new Task(taskCount + 1, input);
        taskCount++;
        System.out.println("\tadded: " + input);
        printSeparator();
    }

    public void printTaskList() {
        if (taskCount == 0) {
            System.out.println("No tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) +".");
            System.out.println(allTasks[i]);
        }
        printSeparator();
    }

    public void printAddedTask() {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + allTasks[taskCount - 1]);
        if (taskCount > 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
        printSeparator();
    }

    public void markTaskAsDone(int id) {
        allTasks[id - 1].markTaskAsDone();
        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t  " + allTasks[id - 1]);
        printSeparator();
    }

    public void unmarkTaskAsDone(int id) {
        allTasks[id - 1].unmarkTaskAsDone();
        System.out.println("\tOK, I've marked this task as undone:");
        System.out.println("\t  " + allTasks[id - 1]);
        printSeparator();
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
        if (byIndex == -1) {
            //handleIncompleteInput();
        }
        String[] descriptionArray = Arrays.copyOfRange(tokens, 0, byIndex);
        String description = String.join(" ", descriptionArray);
        String[] byArray = Arrays.copyOfRange(tokens, byIndex + 1, tokens.length);
        String by = String.join(" ", byArray);
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
        if (fromIndex == -1 || toIndex == -1) {
            //handleIncompleteInput();
        }
        String[] descriptionArray = Arrays.copyOfRange(tokens, 0, fromIndex);
        String description = String.join(" ", descriptionArray);
        String[] fromArray = Arrays.copyOfRange(tokens, fromIndex + 1, toIndex);
        String from = String.join(" ", fromArray);
        String[] toArray = Arrays.copyOfRange(tokens, toIndex + 1, tokens.length);
        String to = String.join(" ", toArray);
        allTasks[taskCount] = new Event(taskCount + 1, description, from, to);
        taskCount++;
        printAddedTask();
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }
}
