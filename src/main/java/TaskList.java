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
            allTasks[i].printTask();
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

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
}
