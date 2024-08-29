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
            if (words[0].equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.print("\t " + (i + 1) + ".[");
                    System.out.println(tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            } else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("\t added: " + userInput);
            }
            printHorizontalRule();
        }

        printHorizontalRule();
        System.out.println("\t Bye! Hope to see you again soon!");
        printHorizontalRule();
    }
}
