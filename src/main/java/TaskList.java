import java.util.Arrays;

public class TaskList {
    private Task[] allTasks;
    private int taskCount;
    private final int MAX_TASKS = 100;

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
        printHorizontalLine();
    }

    public void printTaskList() {
        System.out.println("\tSure! Here are the tasks on your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) +".");
            System.out.println(allTasks[i]);
        }
        printHorizontalLine();
    }

    public void printAddedTask() {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + allTasks[taskCount - 1]);
        if (taskCount > 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
        printHorizontalLine();
    }

    public void markTaskAsDone(int id) {
        allTasks[id - 1].markTaskAsDone();
        System.out.println("\tGreat! I've marked this task as done:");
        System.out.print("\t");
        allTasks[id - 1].printTask();
        printHorizontalLine();
    }

    public void unmarkTaskAsDone(int id) {
        allTasks[id - 1].unmarkTaskAsDone();
        System.out.println("\tOK, I've marked this task as undone:");
        System.out.print("\t");
        allTasks[id - 1].printTask();
        printHorizontalLine();
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
        String[] byArray = Arrays.copyOfRange(tokens, byIndex, tokens.length);
        String by = String.join(" ", byArray);
    }

    public void addEvent(String[] tokens) {

    }

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
}
