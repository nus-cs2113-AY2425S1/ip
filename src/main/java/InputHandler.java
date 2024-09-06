public class InputHandler {
    private static final int MAX_TASKS = 100;
    private static final String[] tasks = new String[MAX_TASKS];  // Array to store tasks
    private static final boolean[] isCompleted = new boolean[MAX_TASKS];  // Array to store task completion status
    private static int taskCount = 0;  // Counter to keep track of the number of tasks

    public static void listTasks() {
        Tommi.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            String status = isCompleted[i] ? "[X]" : "[ ]";
            System.out.println((i + 1) + "." + status + " " + tasks[i]);
        }
        Tommi.printLine();
    }

    public static void addTask(String task) {
        tasks[taskCount] = task;
        isCompleted[taskCount] = false;
        taskCount++;
        Tommi.printLine();
        System.out.println("added: " + task);
        Tommi.printLine();
    }

    public static void markTask(int index) {
        if (index >= 0 && index < taskCount) {
            isCompleted[index] = true;
            Tommi.printLine();
            System.out.println("Awesomesauce! I've marked this task as done:");
            System.out.println("  [X] " + tasks[index]);
            Tommi.printLine();
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 0 && index < taskCount) {
            isCompleted[index] = false;
            Tommi.printLine();
            System.out.println("OK, I've marked this task as undone:");
            System.out.println("  [ ] " + tasks[index]);
            Tommi.printLine();
        }
    }
}
