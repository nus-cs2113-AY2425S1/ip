import java.util.Scanner;

public class Grok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Array to store Task objects
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Grok");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon! Keep Grokking :)");
                printLine();
                break;
            } else if (input.equals("list")) {
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber]);
                printLine();
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber]);
                printLine();
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }

        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
// Follows A-CodingStandard