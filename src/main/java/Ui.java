import java.util.Scanner;

public class Ui {
    private Scanner inputScanner;

    public Ui() {
        // Initialize the scanner to read user input
        inputScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        // Display the welcome message to the user
        System.out.println("____________________________________________________________");
        System.out.println(" Welcome aboard AirBorder.");
        System.out.println(" Ready to assist you with your tasks!");
        System.out.println("____________________________________________________________");
    }

    public void showExitMessage() {
        // Display the exit message to the user
        System.out.println("____________________________________________________________");
        System.out.println(" Thank you for flying with AirBorder!");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        // Read and return the user's input command
        return inputScanner.nextLine().trim();
    }

    public void showLine() {
        // Display a divider line for better readability
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        // Display an error message to the user
        showLine();
        System.out.println(" ERROR: " + message);
        showLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        // Display a message when a task is added
        showLine();
        System.out.println(" Task added: " + task);
        System.out.println(" Now you have " + taskCount + " tasks.");
        showLine();
    }

    public void showTaskDeleted(Task task, int taskCount) {
        // Display a message when a task is deleted
        showLine();
        System.out.println(" Task deleted: " + task);
        System.out.println(" Now you have " + taskCount + " tasks.");
        showLine();
    }

    public void showTaskDone(Task task) {
        // Display a message when a task is marked as done
        showLine();
        System.out.println(" Task completed: " + task);
        showLine();
    }

    public void showTaskUndone(Task task) {
        // Display a message when a task is marked as not done
        showLine();
        System.out.println(" Task marked as incomplete: " + task);
        showLine();
    }

    public void showTaskList(TaskList taskList) {
        // Display the list of tasks to the user
        showLine();
        if (taskList.isEmpty()) {
            System.out.println(" No tasks in your list.");
        } else {
            System.out.println(" Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.getTask(i));
            }
        }
        showLine();
    }

    public void showLoadingError() {
        // Display an error message if tasks cannot be loaded
        showLine();
        System.out.println(" Error loading tasks from file.");
        showLine();
    }

    public void close() {
        // Close the input scanner resource
        inputScanner.close();
    }
}
