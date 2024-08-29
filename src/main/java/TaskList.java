public class TaskList {
    private String[] allTasks;
    private int noOfTasks;

    public TaskList(String task){
        allTasks = new String[100];
        allTasks[0] = task;
        noOfTasks = 1;
    }

    public TaskList() {
        allTasks = new String[100];
        noOfTasks = 0;
    }

    public void addToTaskList(String input) {
        allTasks[noOfTasks] = input;
        noOfTasks++;
        System.out.println("\tadded: " + input);
        printHorizontalLine();
    }

    public void printTaskList() {
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println("\t" + (i + 1) + ". " + allTasks[i]);
        }
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
}
