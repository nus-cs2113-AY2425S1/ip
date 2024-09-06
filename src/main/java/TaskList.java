public class TaskList {

    private static final int MAX_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_TASKS];  // Array to store tasks
    private static int tasksCount = 0;  // Counter to keep track of the number of tasks

    public static void listTasks() {
        Tommi.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ". "  + tasks[i]);
        }
        Tommi.printLine();
    }

    public static void addTask(Task task) {
        tasks[tasksCount] = task;
        tasksCount++;
        Tommi.printLine();
        System.out.println("Sure. I've added the task: " + System.lineSeparator()
                + task + System.lineSeparator()
                + "There are now " + tasksCount + " tasks in the list.");
        Tommi.printLine();
    }

    public static void markTask(int index) {
        if (index >= 0 && index < tasksCount) {
            Tommi.printLine();
            System.out.println("Awesomesauce! I've marked this task as done:");
            tasks[index] = tasks[index].updateIsDone(true);
            System.out.println(tasks[index]);
            Tommi.printLine();
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 0 && index < tasksCount) {
            Tommi.printLine();
            System.out.println("OK, I've marked this task as undone:");
            tasks[index] = tasks[index].updateIsDone(false);
            System.out.println(tasks[index]);
            Tommi.printLine();
        }
    }
}
