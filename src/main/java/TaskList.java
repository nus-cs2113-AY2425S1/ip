public class TaskList {
    private Task[] allTasks;
    private int noOfTasks;

    public TaskList(String task){
        allTasks = new Task[100];
        allTasks[0] = new Task(1, task);
        noOfTasks = 1;
    }

    public TaskList() {
        allTasks = new Task[100];
        noOfTasks = 0;
    }

    public void addToTaskList(String input) {
        allTasks[noOfTasks] = new Task(noOfTasks + 1, input);
        noOfTasks++;
        System.out.println("\tadded: " + input);
        printHorizontalLine();
    }

    public void printTaskList() {
        System.out.println("\tSure! Here are the tasks on your list:");
        for (int i = 0; i < noOfTasks; i++) {
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
