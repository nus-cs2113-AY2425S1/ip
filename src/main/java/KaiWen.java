import java.util.Scanner;

public class KaiWen {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm KaiWen");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            else if (input.equals("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println(" No tasks to display!");
                    printLine();
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    printLine();
                }
            }
            else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    printLine();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[taskNumber]);
                    printLine();
                }
            }
            else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsNotDone();
                    printLine();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskNumber]);
                    printLine();
                }
            }
            else {
                if (taskCount < MAX_TASKS) {
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println(" added: " + input);
                    printLine();
                } else {
                    System.out.println(" Task limit reached. Cannot add more tasks.");
                    printLine();
                }
            }
        }

        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
