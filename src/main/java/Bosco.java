import java.util.Scanner;

public class Bosco {
    private static void printHorizontalRule() {
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        String[] words;
        int taskCount = 0;
        Task[] tasks = new Task[100];
        Task selectedTask;

        printHorizontalRule();
        System.out.println("\t Hello! I'm Bosco APD.");
        System.out.println("\t What can I do for you?");
        printHorizontalRule();

        while (true) {
            userInput = in.nextLine();
            words = userInput.split(" ");
            if (words[0].equals("bye")) {
                break;
            }
            printHorizontalRule();
            switch (words[0]) {
            case "list":
                for (int i = 0; i < taskCount; i++) {
                    System.out.print("\t " + (i + 1) + ".[");
                    System.out.println(tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                break;
            case "mark":
                selectedTask = tasks[Integer.parseInt(words[1]) - 1];
                selectedTask.markAsDone();
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.print("\t   [" + selectedTask.getStatusIcon() + "] ");
                System.out.println(selectedTask.getDescription());
                break;
            case "unmark":
                selectedTask = tasks[Integer.parseInt(words[1]) - 1];
                selectedTask.markAsNotDone();
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.print("\t   [" + selectedTask.getStatusIcon() + "] ");
                System.out.println(selectedTask.getDescription());
                break;
            default:
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("\t added: " + userInput);
                break;
            }
            printHorizontalRule();
        }

        printHorizontalRule();
        System.out.println("\t Bye! Hope to see you again soon!");
        printHorizontalRule();
    }
}
