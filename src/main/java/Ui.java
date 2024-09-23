import task.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void horizontalLine() {
        System.out.println("______________________" +
                "______________________________________");
    }

    public void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Legin, your best online companion!");
        System.out.println("What can I do for you today my friend :D");
        horizontalLine();
    }

    public void bye() throws IOException {
        horizontalLine();
        System.out.println("Bye " +
                Character.toString(0x1F44B) +
                ". Hope to see you again really soon! " +
                Character.toString(0x1F608));
        horizontalLine();
    }

    public String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printTaskList(int taskCount, ArrayList<Task> tasks) {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i));
        }
    }

    public void printAllMatchingTask(String matchingWords, int taskCount, ArrayList<Task> tasks) {
        int indexToPrint = 1;
        for (int i = 0; i < taskCount; i++) {
           if (tasks.get(i).getTask().contains(matchingWords)) {
               System.out.print(indexToPrint + ". ");
               System.out.println(tasks.get(i));
               indexToPrint++;
           }
        }
        if (indexToPrint == 1) {
            System.out.println("Hmmmm seems like there is no matching tasks :<");
        }
    }

    public void printAddedTaskMessage(ArrayList<Task> tasks, int taskCount) {
        horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskCount));
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        horizontalLine();
    }

    public void printDeleteTaskMessage(Task toRemove, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(toRemove);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printMarkTaskMessage(int index, Task taskToMark) {
        System.out.println("Good Job Buddy! I've marked this task as done:");
        System.out.println(taskToMark);
    }

    public void printUnmarkTaskMessage(int index, Task taskToUnmark) {
        System.out.println("Alright! I've marked this task as not done yet:");
        System.out.println(taskToUnmark);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println("Exception Occurred: " + e);
        horizontalLine();
    }
}
