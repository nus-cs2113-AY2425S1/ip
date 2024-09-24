import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in = new Scanner(System.in);
    public void showWelcome() {
        System.out.println("CodyChen Welcomes You");
    }

    public String readCommand() {
        String line = in.nextLine();
        return line;
    }

    public void showEnd() {
        System.out.println("Thank You for using CodyChen. Have a nice day.");
    }

    public void listPopulated(TaskList taskList) {
        System.out.println("____________________\n" + "Let's review! Your List as follows: ");
        int loop = 1;
        ArrayList<Task> tasks = taskList.getTask();
        for (Task task : tasks) {
            System.out.print(loop + "."); // Prints object Array
            System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
            switch (task.getType()) {
            case 'T':
                System.out.println();
                break;
            case 'D':
                System.out.println("(by: " + task.formattedDeadline() + ")");
                break;
            case 'E':
                System.out.println("(by: " + task.formattedDeadline() + task.formattedEvent());
                break;
            }
            loop += 1;
        }
        System.out.println("____________________");
    }

    public void toFind(TaskList taskList, String wordtoFind) {
        System.out.println("____________________\n" + "Showing relevant searches ");
        int loop = 1;
        ArrayList<Task> tasks = taskList.getTask();
        for (Task task : tasks) {
            if (task.getDescription().contains(wordtoFind)) {
                System.out.print(loop + "."); // Prints object Array
                System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
                switch (task.getType()) {
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    System.out.println("(by: " + task.formattedDeadline() + ")");
                    break;
                case 'E':
                    System.out.println("(by: " + task.formattedDeadline() + task.formattedEvent());
                    break;
                }
            }
            loop += 1;
        }
        System.out.println("____________________");
    }

    public void listEmpty() {
        System.out.println("Empty. Time to add some tasks!");
    }

    public void showTodoAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        System.out.println("____________________");
    }

    public void showDeadlineAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).formattedDeadline() + ")");
        System.out.println("____________________");
    }

    public void showEventAdded(TaskList tasks) {
        int index = tasks.size() - 1;
        System.out.println("____________________\n" + "Got it. I've added this task: ");
        System.out.println("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription() +
                "(by: " + tasks.getTask(index).formattedDeadline() +
                tasks.getTask(index).formattedEvent());
        System.out.println("____________________");
    }

    public void showTaskDeleted(TaskList tasks, int index) {
        System.out.println("____________________\n" + "Got it. I've removed this task: \n");
        System.out.print((index + 1) + ".");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println();
            break;
        case 'D':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println("____________________");
    }

    public void showTaskMarked(TaskList tasks, int index) {
        System.out.println("____________________\n" + "Got it. I've marked this task: \n");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println();
            break;
        case 'D':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println("____________________");
    }

    public void showTaskUnmarked(TaskList tasks, int index) {
        System.out.println("____________________\n" + "Got it. I've unmarked this task: \n");
        System.out.print("[" + tasks.getTask(index).getType() + "]" +
                tasks.getTask(index).getStatusIcon() +
                tasks.getTask(index).getDescription());
        switch (tasks.getTask(index).getType()) {
        case 'T':
            System.out.println();
            break;
        case 'D':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() + ")");
            break;
        case 'E':
            System.out.println("(by: " + tasks.getTask(index).formattedDeadline() +
                    tasks.getTask(index).formattedEvent());
            break;
        }
        System.out.println("____________________");

    }
}
