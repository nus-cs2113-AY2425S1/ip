import java.util.Scanner;

public class Apsea {
    public static void printLine() {
        System.out.println("    ________________________________________________________");
    }
    public static void printHello() {
        printLine();
        System.out.println("    Hello! I'm Apsea!\n"
                + "    What can I do for you?");
        printLine();
    }
    public static void printBye() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon");
        printLine();
    }
    public static void addTask(String line, Task[] tasks, int count) {
        Task newTask = new Task(line);
        tasks[count] = newTask;

        printLine();
        System.out.println("    added task: " + line);
        printLine();
    }
    public static void listTasks(Task[] tasks, int count) {
        printLine();
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i+1) + ". "
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());

        }
        printLine();
    }

    public static void markTask(Task[] tasks, String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        tasks[taskIndex].markAsDone();

        printLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + "[" + tasks[taskIndex].getStatusIcon() + "] "
                + tasks[taskIndex].getDescription());
        printLine();
    }

    public static void unmarkTask(Task[] tasks, String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        tasks[taskIndex].markAsUndone();

        printLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + "[" + tasks[taskIndex].getStatusIcon() + "] "
                + tasks[taskIndex].getDescription());
        printLine();
    }

    public static void main(String[] args) {
        printHello();

        String line;
        boolean isExit = false;
        Task[] tasks = new Task[100];
        int count = 0;

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                isExit = true;
                printBye();
                break;
            case "list":
                listTasks(tasks, count);
                break;
            case "mark":
                markTask(tasks, words[1]);
                break;
            case "unmark":
                unmarkTask(tasks, words[1]);
                break;
            default:
                addTask(line, tasks, count);
                count++;
                break;
            }
        } while (!isExit);
    }
}
