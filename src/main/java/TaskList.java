public class TaskList {
    private static final int MAXTASKS = 100;

    public static Task[] tasks = new Task[MAXTASKS];  // Array to store tasks
    public static int taskCounter = 0;  // Counter to keep track of the number of tasks

    public static void listTasks() {
        System.out.println("Here's what you've got in your list:");
        for (int i = 1; i <= taskCounter; i++) {
            System.out.println(i+". " + tasks[i-1].getStatusIcon()
                    + tasks[i-1].getTaskName());
        }
        Terri.printDivider();
    }

    public static void addTask(String newTask) {
        tasks[taskCounter++] = new Task(newTask);
        System.out.println("Just added: " + newTask + "!");
        Terri.printDivider();
    }

    public static void markDone(int taskIndex) {
        tasks[taskIndex].updateisDone(true);
        System.out.println("Just marked that task completed!");
        System.out.println((taskIndex+1)+". "
                + tasks[taskIndex].getStatusIcon()
                + tasks[taskIndex].getTaskName());
        Terri.printDivider();
    }

    public static void markNotDone(int taskIndex) {
        tasks[taskIndex].updateisDone(false);
        System.out.println("Just marked that task as not completed!");
        System.out.println((taskIndex+1)+ ". "
                + tasks[taskIndex].getStatusIcon()
                + tasks[taskIndex].getTaskName());
        Terri.printDivider();
    }

}
