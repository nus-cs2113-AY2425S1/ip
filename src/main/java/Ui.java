import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("I'm Shrek");
        System.out.println("What are you doing in my swamp!??");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("No previous task data found. Starting with an empty list.");
    }

    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println("I've added this task to your pile of nonsense:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount + " tasks in my swamp.");
        showLine();
    }

    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println("Here is your list of nonsense, now get out of my swamp:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));  // Uses Task's toString() method
        }
        showLine();
    }

    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("It’s done. Now, get lost before I toss you out of my swamp!");
        System.out.println("     [" + task.getStatusIcon() + "] " + task.getDescription());
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("It’s unmarked. Gonna change it again? Make up your mind, or get out of my swamp!");
        System.out.println("     [" + task.getStatusIcon() + "] " + task.getDescription());
        showLine();
    }

    public void showTaskDeleted(Task task) {
        showLine();
        System.out.println("Fine I’ll remove this task donkey:");
        System.out.println("   " + task);
        showLine();
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty())
        {
            showLine();
            System.out.println("No such tasks in my swamp!");
            showLine();
        }
        else
        {
            showLine();
            System.out.println("Here's what i dug out from my swamp.");
            for (int i = 0; i < tasks.size(); i++)
            {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
            showLine();
        }
    }

    public void showFormatError() {
        showLine();
        System.out.println("Wrong format, enter a valid task.");
        showLine();
    }

    public void showIndexError() {
        showLine();
        System.out.println("That task is not even in my swamp donkey! Try another task.");
        showLine();
    }

    public void showInputError() {
        showLine();
        System.out.println("What are you typing donkey? Specify the time/task right or get out of my swamp.");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Finally, you’re leaving. Now I can have some peace in my swamp.");
        showLine();
    }

    public void showSaveError(){
        showLine();
        System.out.println("Failed to save file.");
        showLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}

