import java.util.Scanner;

public class CodeCatalyst {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("        ________________________________________________________");
        System.out.println("         Hello, I'm CodeCatalyst.");
        System.out.println("         What can I do for you?");
        System.out.println("        ________________________________________________________\n");

        while (true) {
            String input = in.nextLine();
            System.out.println("        ________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("         Bye. Hope to see you again soon!");
                System.out.println("        ________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("         Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("         " + (i + 1) + ". " + tasks[i]);
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                if (taskNumber >= 1 && (taskNumber - 1) < taskCount) {
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println("         Nice! I've marked this task as done: ");
                    System.out.println("         " + tasks[taskNumber - 1]);
                } else {
                    System.out.println("         Invalid task number.");
                }
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7));
                if (taskNumber >= 1 && (taskNumber - 1) < taskCount) {
                    tasks[taskNumber - 1].markAsNotDone();
                    System.out.println("         Ok, I've marked this task as not done yet: ");
                    System.out.println("         " + tasks[taskNumber - 1] );
                } else {
                    System.out.println("         Invalid task number.");
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount += 1;
                System.out.println("         added: " + input);
            }
            System.out.println("        ________________________________________________________\n");
        }
    }
}
