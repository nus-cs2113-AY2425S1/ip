public class TaskList {
    private Task[] allTasks;
    private int noOfTasks;

    public TaskList(String task){
        allTasks = new Task[100];
        allTasks[0] = new Task(noOfTasks, task);
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
        for (int i = 0; i < noOfTasks; i++) {
            allTasks[i].printTask();
        }
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
}
